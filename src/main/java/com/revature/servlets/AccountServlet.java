package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.models.AccountDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;

public class AccountServlet extends HttpServlet {
	private AccountServices aService = new AccountServices();
	private UserServices uService = new UserServices();
	private ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Gets all users from the database via UserServices
		PrintWriter out = resp.getWriter();

		HttpSession ses = req.getSession(false);
		if (ses != null) {
			String username = (String) ses.getAttribute("username");
			User loggedIn = uService.findByUsername(username);

			if (req.getParameter("accountId") == null && req.getParameter("owner") == null && req.getParameter("status") == null) {
				if (loggedIn.getRole().equals("Admin") || loggedIn.getRole().equals("Employee")) {
					List<Account> list = aService.findAll();

					// Convert Java Object list into a JSON string
					String json = om.writeValueAsString(list);

					out.print(json);
					resp.setStatus(200); // Success!
					resp.setContentType("application/json");
				} else {
					out.print("You do not have permission to view this information! >:(");
					resp.setStatus(401);
				}
			} else if (req.getParameter("accountId") != null && req.getParameter("owner") == null && req.getParameter("status") == null) {
				int targetID = Integer.parseInt(req.getParameter("accountId"));

				if (loggedIn.getRole().equals("Admin") || loggedIn.getRole().equals("Employee")
						|| loggedIn.getUserID() == aService.findByID(targetID).getOwner()) {
					Account targetAccount = aService.findByID(targetID);
					if (targetAccount == null) {
						out.print("Account #" + targetID + " does not exist. Please try again!");
						resp.setStatus(404);
					} else {
						String json = om.writeValueAsString(targetAccount);

						out.print(json);
						resp.setStatus(200);
						resp.setContentType("application/json");
					}

				} else {
					out.print("You do not have permission to view this information! >:(");
					resp.setStatus(401);
				}
			} else if (req.getParameter("accountId") == null && req.getParameter("owner") != null && req.getParameter("status") == null) {
				int targetOwner = Integer.parseInt(req.getParameter("owner"));

				if (loggedIn.getRole().equals("Admin") || loggedIn.getRole().equals("Employee")
						|| loggedIn.getUserID() == targetOwner) {
					User myOwner = uService.findById(targetOwner);
					if (myOwner == null) {
						out.print("User #" + targetOwner + " does not exist!");
					} else {
						List<Account> targetAccounts = aService.findByUser(myOwner);
						if (targetAccounts == null) {
							out.print("User #" + targetOwner + " does not own any accounts!");
							resp.setStatus(404);
						} else {
							String json = om.writeValueAsString(targetAccounts);

							out.print(json);
							resp.setStatus(200);
							resp.setContentType("application/json");
						}
					}
				} else {
					out.print("You do not have permission to view this information! >:(");
					resp.setStatus(401);
				}
			} else if (req.getParameter("accountId") == null && req.getParameter("owner") == null && req.getParameter("status") != null) {
				String status = req.getParameter("status");
				
				if (loggedIn.getRole().equals("Admin") || loggedIn.getRole().equals("Employee")) {
					if (!status.equals("Pending") && !status.equals("Open") && !status.equals("Closed") && !status.equals("Denied")) {
						resp.setStatus(400);
						out.print("Invalid status. Please try again!");
					}else {
						List<Account> targetAccounts = aService.findByStatus(status);
						if (targetAccounts.isEmpty()) {
							out.print("There are no accounts with this status!");
							resp.setStatus(404);
						}else {
							String json = om.writeValueAsString(targetAccounts);
							out.print(json);
							resp.setStatus(200);
							resp.setContentType("application/json");
						}
					}
				}else {
					out.print("You do not have permission to view this information! >:(");
					resp.setStatus(401);
				}
			}else {
				resp.setStatus(400);
				out.print("Invalid fields. Please try again!");
			}
		} else {
			resp.setStatus(401);
			out.print("Please log in first!");
		}
	}

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
			// Takes in the user inputed Post request and turns it into something readable
			// for the ObjectMapper

			Account a = om.readValue(body, Account.class);
			if (loggedIn.getRole().equals("Admin") || loggedIn.getRole().equals("Employee") || loggedIn.getUserID() == a.getOwner()) {
				if (aService.addAccount(a, a.getOwner())) {
					List<Account> accounts = aService.findByUser(uService.findById(a.getOwner()));
					resp.setStatus(201); // Created!
					out.print("The account has been created~! User #" + a.getOwner() + " now owns the following accounts: ");
					for(Account acc : accounts) {
						out.print("\nAccount #" + acc.getAccountID());
					}
				} else {
					resp.setStatus(400); // Generic bad request
					out.print("Invalid fields, please try again.");
				}
			}else {
				resp.setStatus(401);
				out.print("You can only create an account for yourself (unless you are an admin or an employee, which you are not)");
			}

		} else {
			PrintWriter out = resp.getWriter();
			out.print("Please log in first, then try again!");
			resp.setStatus(400);
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		HttpSession ses = req.getSession(false);
		if (ses != null) {
			String username = (String) ses.getAttribute("username");
			User loggedIn = uService.findByUsername(username);

			StringBuilder sb = new StringBuilder();

			BufferedReader reader = req.getReader();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = new String(sb);

			Account a = om.readValue(body, Account.class);

			if (loggedIn.getRole().equals("Admin")
					|| aService.findByID(a.getAccountID()).getOwner() == loggedIn.getUserID()) {
				if (aService.findByID(a.getAccountID()) != null) {
					// Account exists in the database
					if (aService.updateAccount(a, a.getAccountID())) {
						out.print("Account #" + a.getAccountID() + " has successfully been updated!");
						resp.setStatus(200);
					} else {
						out.print("Something went wrong >.> Please try again!");
						resp.setStatus(400);
					}

				} else {
					// Account does not exist in the database
					out.print("Account #" + a.getAccountID() + " does not exist in the database.");
					resp.setStatus(404);
				}

			} else {
				out.print("You may only update your own account (unless you are an admin, which you are not)");
				resp.setStatus(401);
			}
		} else {
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
			
			AccountDTO a = om.readValue(body, AccountDTO.class);
			Account targetAccount = aService.findByID(a.accountID);

			if(targetAccount != null) {
				if(loggedIn.getUserID() == targetAccount.getOwner() || loggedIn.getRole().equals("Admin")) {

					if(aService.deleteAccount(targetAccount)) {
						if(loggedIn.getUserID() == targetAccount.getOwner()) {
							out.print("Your account, Account #" + a.accountID + ", has been deleted.");
							resp.setStatus(200);
						}else {
							out.print("Account #" + a.accountID + " has been deleted. RIP");
							resp.setStatus(200);
						}
					}else {
						out.print("Something went wrong, please try again!");
						resp.setStatus(400);
					}
					
				}else {
					out.print("You can only delete your own account! (unless you are an admin, which you are not)");
					resp.setStatus(401);
				}
			}else {
				out.print("Account #" + a.accountID + " does not exist in the database.");
				resp.setStatus(404);
			}
			

		}else {
			out.print("Please log in first!");
			resp.setStatus(401);
		}
	}
	
}
