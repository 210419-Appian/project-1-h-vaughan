package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO{

	@Override
	public List<User> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM users;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<User> list = new ArrayList<>();
			
			while(result.next()) {
				User user = new User();
				
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("pword"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));				
				user.setEmail(result.getString("email"));
				user.setRole(result.getString("user_role"));
				
				list.add(user);
			}			
			return list;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM users WHERE username = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username); //Replacing the "1"st '?' with the string name
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				User user = new User();
				
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("pword"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));				
				user.setEmail(result.getString("email"));
				user.setRole(result.getString("user_role"));
				
				return user;
			}			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO users (username, pword, first_name, last_name, email, user_role)" 
					+ "VALUES(?,?,?,?,?,?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, u.getUsername());
			statement.setString(++index, u.getPassword());
			statement.setString(++index, u.getFirstName());
			statement.setString(++index, u.getLastName());
			statement.setString(++index, u.getEmail());
			statement.setString(++index, u.getRole());
			
			statement.execute();
			
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
