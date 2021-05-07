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
import com.revature.models.TransferRequest;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;

public class TransferServlet extends HttpServlet {

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

			TransferRequest t = om.readValue(body, TransferRequest.class);

			Account sourceAccount = aService.findByID(t.getSourceAccountId());
			Account targetAccount = aService.findByID(t.getTargetAccountId());
			
			if (loggedIn.getRole().equals("Admin") || loggedIn.getUserID() == sourceAccount.getOwner()) {

				if (aService.transfer(sourceAccount, targetAccount, t.getAmount())) {
					sourceAccount = aService.findByID(t.getSourceAccountId());
					targetAccount = aService.findByID(t.getTargetAccountId());
					out.print("$" + t.getAmount() + " has been transferred from Account #" + t.getSourceAccountId() + " to Account #" + t.getTargetAccountId()
							+ "\nCurrent Balance of Account #" + t.getSourceAccountId() +": $" + sourceAccount.getBalance()
							+ "\nCurrent Balance of Account #" + t.getTargetAccountId() +": $" + targetAccount.getBalance());
					resp.setStatus(200);
				} else {
					resp.setStatus(400);
					out.print("Account #" + t.getSourceAccountId() + " does not have enough balance for this transaction. \nCurrent Balance: $" + sourceAccount.getBalance());
				}

			} else {
				resp.setStatus(401);
				out.print("You do not have permission for this!");
			}
		} else {
			PrintWriter out = resp.getWriter();
			resp.setStatus(401);
			out.print("Please log in first!");
		}
	}

}
