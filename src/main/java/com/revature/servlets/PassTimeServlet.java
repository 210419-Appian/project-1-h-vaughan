package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;

public class PassTimeServlet extends HttpServlet{

	private AccountServices aServices = new AccountServices();
	private UserServices uServices = new UserServices();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		HttpSession ses = req.getSession(false);
		if(ses != null) {
			String username = (String) ses.getAttribute("username");
			User loggedIn = uServices.findByUsername(username);
			
			if(loggedIn.getRole().equals("Admin")) {
				int months = Integer.parseInt(req.getParameter("months"));
				
				aServices.accrueInterest(months);
				
				out.print(months + " months have passed. Interest has accrued for all Open Savings accounts");
				resp.setStatus(200);
			}else {
				out.print("Only admins have domain over time itself");
				resp.setStatus(401);
			}			
		}else {
			out.print("Please log in first!");
			resp.setStatus(401);
		}
		
	}
	
}
