package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.UserServices;

public class UserServlet extends HttpServlet {

	private UserServices uService = new UserServices();
	private ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Gets all users from the database via UserServices
		PrintWriter out = resp.getWriter();
		
		HttpSession ses = req.getSession(false);
		if(ses != null) {
			String username = (String) ses.getAttribute("username");
			User loggedIn = uService.findByUsername(username);
			
			System.out.println(req.getParameter("id"));
			
			if (req.getParameter("id") == null) {
				if(loggedIn.getRole().equals("Admin") || loggedIn.getRole().equals("Employee")) {
					List<User> list = uService.findAll(); 
					
					//Convert Java Object list into a JSON string
					String json = om.writeValueAsString(list);
				
					out.print(json);
					resp.setStatus(200); //Success!				
					resp.setContentType("application/json");
				}else {
					out.print("You do not have permission to view this information! >:(");
					resp.setStatus(401);
				}	
			}else {
				int targetID = Integer.parseInt(req.getParameter("id"));
				
				if(loggedIn.getRole().equals("Admin") || loggedIn.getRole().equals("Employee") || loggedIn.getUserID() == targetID) {
					User targetUser = uService.findById(targetID);
					if (targetUser == null) {
						out.print("User #" + targetID + " does not exist. Please try again!");
						resp.setStatus(404);
					}else {
						String json = om.writeValueAsString(targetUser);
						
						out.print(json);
						resp.setStatus(200);
						resp.setContentType("application/json");
					}

				}else {
					out.print("You do not have permission to view this information! >:(");
					resp.setStatus(401);
				}
			}					
		}else {
			resp.setStatus(401);
			out.print("Please log in first!");
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		HttpSession ses = req.getSession(false);
		if(ses != null) {
			String username = (String) ses.getAttribute("username");
			User loggedIn = uService.findByUsername(username);
			
			StringBuilder sb = new StringBuilder();
			
			BufferedReader reader = req.getReader();
			
			String line = reader.readLine();
			
			while(line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = new String(sb); 
			
			User u = om.readValue(body, User.class);
			
			if(loggedIn.getRole().equals("Admin") || u.getUserID() == loggedIn.getUserID()) {	
				if(uService.findById(u.getUserID()) != null) {
					//User exists in the database
					if (uService.updateUser(u, u.getUserID())) {
						out.print(u.getUsername() + " has successfully been updated!");
						resp.setStatus(200);	
					} else {
						out.print("That username is taken. Please try again!");
						resp.setStatus(400);
					}

				}else {
					//User does not exist in the database
					out.print("User #" + u.getUserID() + " does not exist in the database.");
					resp.setStatus(404);
				}
				
			}else {
				out.print("You may only update your own user account (unless you are an admin, which you are not)");
				resp.setStatus(401);
			}
		}else {
			out.print("Please log in first!");
			resp.setStatus(401);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out = resp.getWriter();
		
		HttpSession ses = req.getSession(false);
		if(ses != null) {
			String username = (String) ses.getAttribute("username");
			User loggedIn = uService.findByUsername(username);
			
			StringBuilder sb = new StringBuilder();
			
			BufferedReader reader = req.getReader();
			
			String line = reader.readLine();
			
			while(line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = new String(sb); 
			
			UserDTO u = om.readValue(body, UserDTO.class);

			
			if(loggedIn.getUsername().equals(u.username) || loggedIn.getRole().equals("Admin")) {
				User targetUser = uService.findByUsername(u.username);
				if(targetUser != null) {
					if(uService.deleteUser(targetUser)) {
						if(loggedIn.getUsername().equals(u.username)) {
							out.print("Your account has been deleted. You have automatically been logged out of the application.");
							resp.setStatus(200);
							ses.invalidate();
						}else {
							out.print(u.username + " has been deleted. RIP");
							resp.setStatus(200);
						}
					}else {
						out.print("Something went wrong, please try again!");
						resp.setStatus(400);
					}
				}else {
					out.print("That is not a valid user. Try again!");
					resp.setStatus(400);
				}
				
			}else {
				out.print("You can only delete your own account! (unless you are an admin, which you are not)");
				resp.setStatus(401);
			}
		}else {
			out.print("Please log in first!");
			resp.setStatus(401);
		}
	}
}
