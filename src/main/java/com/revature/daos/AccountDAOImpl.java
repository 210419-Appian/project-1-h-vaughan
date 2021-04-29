package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO{

	private static UserDAOImpl uDao = new UserDAOImpl();
	
	@Override
	public List<Account> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM accounts;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Account> list = new ArrayList<>();
			
			while(result.next()) {
				Account account = new Account();
				
				account.setAccountID(result.getInt("account_id"));
				account.setBalance(result.getFloat("balance"));
				account.setStatus(result.getString("account_status"));
				account.setAccountType(result.getString("account_type"));
				account.setOwner(uDao.findByUsername(result.getString("account_owner")));
				
				list.add(account);
			}			
			return list;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findByID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM accounts WHERE account_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			
			while(result.next()) {
				Account account = new Account();
				
				account.setAccountID(result.getInt("account_id"));
				account.setBalance(result.getFloat("balance"));
				account.setStatus(result.getString("account_status"));
				account.setAccountType(result.getString("account_type"));
				account.setOwner(uDao.findByUsername(result.getString("account_owner")));
				
				return account;
			}			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addAccount(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO accounts (balance, account_status, account_type, account_owner)" 
					+ "VALUES(?,?,?,?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setDouble(++index, a.getBalance());
			statement.setString(++index, a.getStatus());
			statement.setString(++index, a.getAccountType());
			statement.setString(++index, a.getOwner().getUsername());
			
			statement.execute();
			
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> findByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
