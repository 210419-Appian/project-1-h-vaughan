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

public class AccountDAOImpl implements AccountDAO {

	private static UserDAOImpl uDao = new UserDAOImpl();

	@Override
	public List<Account> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM accounts;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<Account> list = new ArrayList<>();

			while (result.next()) {
				Account account = new Account();

				account.setAccountID(result.getInt("account_id"));
				account.setBalance(result.getFloat("balance"));
				account.setStatus(result.getString("account_status"));
				account.setAccountType(result.getString("account_type"));
				account.setInterestRate(result.getDouble("interest_rate"));
				account.setOwner(result.getInt("account_owner"));

				list.add(account);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findByID(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM accounts WHERE account_id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Account account = new Account();

				account.setAccountID(result.getInt("account_id"));
				account.setBalance(result.getFloat("balance"));
				account.setStatus(result.getString("account_status"));
				account.setAccountType(result.getString("account_type"));
				account.setInterestRate(result.getDouble("interest_rate"));
				account.setOwner(result.getInt("account_owner"));

				return account;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addAccount(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO accounts (balance, account_status, account_type, interest_rate, account_owner)"
					+ "VALUES(?,?,?,?,?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setDouble(++index, a.getBalance());
			statement.setString(++index, a.getStatus());
			statement.setString(++index, a.getAccountType());
			statement.setDouble(++index, a.getInterestRate());
			statement.setInt(++index, a.getOwner());

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> findByUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM accounts WHERE account_owner = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, u.getUserID());

			ResultSet result = statement.executeQuery();

			List<Account> list = new ArrayList<>();

			while (result.next()) {
				Account account = new Account();

				account.setAccountID(result.getInt("account_id"));
				account.setBalance(result.getFloat("balance"));
				account.setStatus(result.getString("account_status"));
				account.setAccountType(result.getString("account_type"));
				account.setInterestRate(result.getDouble("interest_rate"));
				account.setOwner(result.getInt("account_owner"));

				list.add(account);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean withdraw(Account a, double amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			int accountID = a.getAccountID();
			double newBalance = a.getBalance() - amount;

			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setDouble(1, newBalance);
			statement.setInt(2, accountID);

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deposit(Account a, double amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			int accountID = a.getAccountID();
			double newBalance = a.getBalance() + amount;

			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setDouble(1, newBalance);
			statement.setInt(2, accountID);

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean transfer(Account sourceAccount, Account targetAccount, double amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			int sourceAccountID = sourceAccount.getAccountID();
			double sourceNewBalance = sourceAccount.getBalance() - amount;
			int targetAccountID = targetAccount.getAccountID();
			double targetNewBalance = targetAccount.getBalance() + amount;

			String sql = "BEGIN;" + "UPDATE accounts SET balance = ? WHERE account_id = ?;"
					+ "UPDATE accounts SET balance = ? WHERE account_id = ?;" + "COMMIT;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setDouble(1, sourceNewBalance);
			statement.setInt(2, sourceAccountID);
			statement.setDouble(3, targetNewBalance);
			statement.setInt(4, targetAccountID);

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAccount(Account a, int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE accounts SET " + "balance = ?, " + "account_status = ?, " + "account_type = ?, "
					+ "interest_rate = ?, " + "account_owner = ? " + "WHERE account_id = " + id + ";";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setDouble(++index, a.getBalance());
			statement.setString(++index, a.getStatus());
			statement.setString(++index, a.getAccountType());
			statement.setDouble(++index, a.getInterestRate());
			statement.setInt(++index, a.getOwner());

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> findByStatus(String status) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM accounts WHERE account_status = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, status);

			ResultSet result = statement.executeQuery();

			List<Account> list = new ArrayList<>();

			while (result.next()) {
				Account account = new Account();

				account.setAccountID(result.getInt("account_id"));
				account.setBalance(result.getFloat("balance"));
				account.setStatus(result.getString("account_status"));
				account.setAccountType(result.getString("account_type"));
				account.setInterestRate(result.getDouble("interest_rate"));
				account.setOwner(result.getInt("account_owner"));

				list.add(account);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteAccount(Account a) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "DELETE FROM accounts WHERE account_id = " + a.getAccountID() + ";";
			
			Statement statement = conn.createStatement();
			
			statement.execute(sql);
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
