package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.LoginServices;
import com.revature.services.UserServices;

public class LoginServlet extends HttpServlet{
	
	LoginServices lService = new LoginServices();
	UserServices uService = new UserServices();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDTO u = new UserDTO();
		
		u.username = req.getParameter("username");
		u.password = req.getParameter("password");
		
		//The userDTO should then be passed to the Service layer to check if credentials are accurate.
		

		PrintWriter out = resp.getWriter();
		
		if(lService.login(u.username, u.password)) {
			//First thing: Create a session so we remember our user/client in the future
			HttpSession ses = req.getSession(); //cookie created and put in the response
			ses.setAttribute("username", u.username); //save username in session for use later

			User myUser = uService.findByUsername(u.username);
			ses.setAttribute("userRole", myUser.getRole());
			
			out.print("Welcome, " + myUser.getFirstName() + " " + myUser.getLastName() +"! You have successfully logged in.");
			resp.setStatus(200);
		}else {
			out.print("Login failed. Please try again!");
			resp.setStatus(400);
		}
		
		//resp.setStatus(200); //Tomcat will default to Status Code 200 if it find a servlet method to handle the request
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		PrintWriter out = resp.getWriter();
		
		if (ses != null) {
			out.print(ses.getAttribute("username").toString() + " is currently logged in!");
		}else {
			out.print("No one is logged in!");
		}

	}
	
}
