package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserServices;

public class RegisterServlet extends HttpServlet{
	
	private UserServices uService = new UserServices();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		if(ses != null) {
			User loggedIn = uService.findByUsername(ses.getAttribute("username").toString());
			
			PrintWriter out = resp.getWriter();
			
			if(loggedIn.getRole().equals("Admin")) {
				
				StringBuilder sb = new StringBuilder();
				
				BufferedReader reader = req.getReader();
				
				String line = reader.readLine();
				
				while(line != null) {
					sb.append(line);
					line = reader.readLine();
				}

				String body = new String(sb); 
				//Takes in the user inputed Post request and turns it into something readable for the ObjectMapper
				
				User u = om.readValue(body, User.class);
				
				if(uService.addUser(u)) {
					resp.setStatus(201); //Created!
					out.print(u.getUsername() + " has been created~!");
				}else {
					resp.setStatus(400); //Generic bad request
					out.print("Invalid fields, please try again.");
				}
			}else {
				out.print("You do not have permission to do this! >:(");
				resp.setStatus(401);
				
			}
		}else {
			PrintWriter out = resp.getWriter();
			out.print("Please log in first, then try again!");
			resp.setStatus(400);
		}

	}
	
}
