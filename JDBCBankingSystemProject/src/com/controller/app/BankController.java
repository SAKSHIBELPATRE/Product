package com.controller.app;

import com.model.app.BankAccount;

import java.sql.*;
import java.util.Scanner;

public class BankController {

	private Scanner sc = new Scanner(System.in);

	public static Connection connection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management", "root", "root");
	}

	public void addAccount() throws SQLException, ClassNotFoundException {
		BankAccount account = new BankAccount();
		System.out.println("Enter Bank ID:");
		int bankid = sc.nextInt();
		account.setAccountNumber(bankid);

		System.out.println("Enter Account Number:");
		int accountNumber = sc.nextInt();
		account.setAccountNumber(accountNumber);
		try (Connection con = connection()) {
			Statement smt = con.createStatement();
			String sql = "SELECT * FROM accounts WHERE bankid = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, bankid);
			ResultSet rs = stmt.executeQuery();
			boolean found = false;

			if (rs.next()) {
				System.out.println("Account already exists with this number.");
			} else {
				sc.nextLine();

				System.out.println("Enter Customer Name:");
				String customerName = sc.nextLine();
				account.setCustomerName(customerName);

				System.out.println("Enter Initial Deposit Amount:");
				double balance = sc.nextDouble();
				account.setBalance(balance);

				String query = "INSERT INTO accounts (bankid, accountNumber, customerName, balance) VALUES (?, ?, ?, ?)";
				PreparedStatement insertStmt = con.prepareStatement(query);
				insertStmt.setInt(1, bankid);
				insertStmt.setInt(2, accountNumber);
				insertStmt.setString(3, customerName);
				insertStmt.setDouble(4, balance);
				insertStmt.executeUpdate();

				System.out.println("Bank Account created successfully.");
			}
		}
	}

	public void viewAccounts() throws SQLException, ClassNotFoundException {
		try (Connection con = connection()) {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT * FROM accounts");

			while (rs.next()) {
				System.out.println("Account Number: " + rs.getInt("accountNumber"));
				System.out.println("Customer Name: " + rs.getString("customerName"));
				System.out.println("Balance: " + rs.getDouble("balance"));
				System.out.println("------------");
			}
		}
	}

	public void updateAccount() throws SQLException, ClassNotFoundException {
		System.out.println("Enter Account Number to Update:");
		int accountNumber = sc.nextInt();

		try (Connection con = connection()) {
			Statement smt = con.createStatement();
			String sql = "SELECT * FROM accounts WHERE accountNumber = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, accountNumber);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				System.out.println("Account found. Select option to update:");
				System.out.println("1. Update Name");
				System.out.println("2. Update Balance");
				System.out.println("3. Update Both");

				int choice = sc.nextInt();
				String query = "";

				switch (choice) {
				case 1:
					System.out.println("Enter new customer name:");
					sc.nextLine(); // Consume newline
					String name = sc.nextLine();
					query = "UPDATE accounts SET customerName = ? WHERE accountNumber = ?";
					PreparedStatement stmt1 = con.prepareStatement(query);
					stmt1.setString(1, name);
					stmt1.setInt(2, accountNumber);
					stmt1.executeUpdate();
					break;

				case 2:
					System.out.println("Enter new balance:");
					double balance = sc.nextDouble();
					query = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
					PreparedStatement stmt2 = con.prepareStatement(query);
					stmt2.setDouble(1, balance);
					stmt2.setInt(2, accountNumber);
					stmt2.executeUpdate();
					break;

				case 3:
					System.out.println("Enter new customer name:");
					sc.nextLine(); // Consume newline
					String newName = sc.nextLine();
					System.out.println("Enter new balance:");
					double newBalance = sc.nextDouble();
					query = "UPDATE accounts SET customerName = ?, balance = ? WHERE accountNumber = ?";
					PreparedStatement stmt3 = con.prepareStatement(query);
					stmt3.setString(1, newName);
					stmt3.setDouble(2, newBalance);
					stmt3.setInt(3, accountNumber);
					stmt3.executeUpdate();
					break;

				default:
					System.out.println("Invalid choice.");
					break;
				}
				System.out.println("Account updated successfully.");
			} else {
				System.out.println("Account not found.");
			}
		}
	}

	public void deleteAccount() throws SQLException, ClassNotFoundException {
		System.out.println("Enter Account Number to delete:");
		int accountNumber = sc.nextInt();

		try (Connection con = connection()) {
			Statement smt = con.createStatement();
			String query = "DELETE FROM accounts WHERE accountNumber = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, accountNumber);
			int rows = stmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Account deleted successfully.");
			} else {
				System.out.println("Account not found.");
			}
		}
	}
}
