package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserServices;

public class UserServlet extends HttpServlet {

	private UserServices uService = new UserServices();
	private ObjectMapper om = new ObjectMapper();
	private User fakeAdmin = new User("fakeUsername", "fakePassword", "Not", "Real", "notarealemail@adress.com", "Admin");
	private User fakeStandard = new User("fakeUsername", "fakePassword", "Not", "Real", "notarealemail@adress.com", "Standard");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Gets all users from the database via UserServices
		
		//List<User> list = uService.findAll(fakeStandard);
		List<User> list = uService.findAll(fakeAdmin); //TODO: LOGIN !!!!
		
		//Convert Java Object list into a JSON string
		String json = om.writeValueAsString(list);
		
		PrintWriter pw = resp.getWriter();

		if(list.isEmpty()) {
			pw.print("You do not have permission to view this information!");
			resp.setStatus(401);
		}else {
			pw.print(json);
			resp.setStatus(200); //Success!
		}
		resp.setContentType("application/json");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		}else {
			resp.setStatus(400); //Generic bad request
		}		
	}	
}
