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
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.models.WithdrawRequest;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;
import com.revature.throwables.BalanceBelowZero;

public class WithdrawServlet extends HttpServlet{
	
	private UserServices uService = new UserServices();
	private AccountServices aService = new AccountServices();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		if (ses != null) {
			User loggedIn = uService.findByUsername(ses.getAttribute("username").toString());

			PrintWriter out = resp.getWriter();

			StringBuilder sb = new StringBuilder();			
			BufferedReader reader = req.getReader();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = new String(sb);


			WithdrawRequest w = om.readValue(body, WithdrawRequest.class);

			Account sourceAccount = aService.findByID(w.getAccountId());
			if(loggedIn.getRole().equals("Admin") || loggedIn.getUserID() == sourceAccount.getOwner()) {
				try {
					if(aService.withdraw(sourceAccount, w.getAmount())) {
						sourceAccount = aService.findByID(w.getAccountId());
						out.print(w.getAmount() + " has been withdrawn from Account #" + w.getAccountId() + "\nCurrent Balance: $" + sourceAccount.getBalance());
						resp.setStatus(200);
					}else {
						throw new BalanceBelowZero();
					}
				}catch(BalanceBelowZero e) {
					sourceAccount = aService.findByID(w.getAccountId());
					e.printStackTrace();
					resp.setStatus(451);
					out.print("Account #" + w.getAccountId() + " does not have enough balance for this transaction. \nCurrent Balance: $" + sourceAccount.getBalance());
				}
			}else {
				resp.setStatus(401);
				out.print("You do not have permission for this!");
			}
		}else {
			PrintWriter out = resp.getWriter();
			resp.setStatus(401);
			out.print("Please log in first!");
		}
	}

}
