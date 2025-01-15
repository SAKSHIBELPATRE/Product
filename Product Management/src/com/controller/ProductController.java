package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.model.Product;
//import com.mysql.cj.protocol.Resultset;

public class ProductController {

	Scanner sc = new Scanner(System.in);

	public static Connection connection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_management", "root", "root");
		return con;

	}

	public void addProduct() throws ClassNotFoundException, SQLException {
		Product p = new Product();
		System.out.println("Enter ID of product:");
		int id = sc.nextInt();
		p.setId(id);

		// Establishing connection
		Connection con = connection();
		Statement smt = con.createStatement();

		// Check if the product ID already exists
		String check = "select * from product where id = " + id;
		ResultSet rs = smt.executeQuery(check);

		if (rs.next()) {
			// If the ID exists
			System.out.println("Product ID already exists. Cannot add a new product with the same ID.");
		} else {
			// If the ID does not exist, add the product
			System.out.println("Enter Name of the Product:");
			String name = sc.next();
			p.setName(name);

			System.out.println("Enter Price of the Product:");
			double price = sc.nextDouble();
			p.setPrice(price);

			System.out.println("Enter Quantity of the Product:");
			int quantity = sc.nextInt();
			p.setQuantity(quantity);

			System.out.println("Enter Category of the Product:");
			String category = sc.next();
			p.setCategory(category);

			String sql = "INSERT INTO product VALUES (" + id + ", '" + name + "', " + price + ", " + quantity + ", '"
					+ category + "')";
			smt.executeUpdate(sql);

			System.out.println("Product added successfully!");
		}

	}

//		System.out.println("Enter Name of the Product:");
//		p.setName(sc.next());
//		//String name=sc.next();
//		System.out.println("Enter Price of the Product:");
//		p.setPrice(sc.nextDouble());
//		//Double price=sc.nextDouble();
//		System.out.println("Enter Quantity of the Product:");
//		p.setQuantity(sc.nextInt());
//		//int Quantity=sc.nextInt();
//		System.out.println("Enter Category of the Product:");
//		p.setCategory(sc.next());
//		//String Category=sc.next();

	public void viewProduct() throws ClassNotFoundException, SQLException {
		Statement smt = connection().createStatement();
		String sql = "select * from product";
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			System.out.println("Product ID:" + rs.getInt(1));
			System.out.println("Product Name:" + rs.getString(2));
			System.out.println("Product Price:" + rs.getDouble(3));
			System.out.println("Product Quantity:" + rs.getInt(4));
			System.out.println("Product Category:" + rs.getNString(5));
			System.out.println("View data sucessfully:");
			System.out.println("__________________");

		}
	}

	public void updateProduct() throws ClassNotFoundException, SQLException {
		Product p = new Product();
		System.out.println("Enter ID to update Product:");
		// int id=sc.nextInt();
		int id = sc.nextInt();
		p.setId(id);

		// Check if the product ID already exists
		Connection con = connection();
		Statement smt = con.createStatement();
		String checkIdQuery = "select * from product where id = " + id;
		ResultSet rs = smt.executeQuery(checkIdQuery);

		if (rs.next()) {
			// If the ID exists, update the product details
			System.out.println("Product ID already exists. Updating product details...");

			System.out.println("1. Update Name");
			System.out.println("2. Update Price");
			System.out.println("3. Update Quantity");
			System.out.println("4. Update Category");
			System.out.println("5. Update All Fields");

			System.out.println("Enter your choice:");
			int updateChoice = sc.nextInt();

			String updateQuery = "";

			switch (updateChoice) {
			case 1:
				// Update Name
				System.out.println("Enter New Name of the Product:");
				String name = sc.next();
				updateQuery = "Update product set name = '" + name + "' Where id = " + id;
				break;

			case 2:
				// Update Price
				System.out.println("Enter New Price of the Product:");
				double price = sc.nextDouble();
				updateQuery = "Update product set price = " + price + " Where id = " + id;
				break;

			case 3:
				// Update Quantity
				System.out.println("Enter New Quantity of the Product:");
				int quantity = sc.nextInt();
				updateQuery = "Update product set quantity = " + quantity + "Where id = " + id;
				break;

			case 4:
				// Update Category
				System.out.println("Enter New Category of the Product:");
				String category = sc.next();
				updateQuery = "Update product set category = '" + category + "'Where id = " + id;
				break;

			case 5:
				// Update All Fields
				System.out.println("Enter New Name of the Product:");
				name = sc.next();
				System.out.println("Enter New Price of the Product:");
				price = sc.nextDouble();
				System.out.println("Enter New Quantity of the Product:");
				quantity = sc.nextInt();
				System.out.println("Enter New Category of the Product:");
				category = sc.next();
				updateQuery = "Update product set name = '" + name + "', price = " + price + ", quantity = " + quantity
						+ ", category = '" + category + "' Where id = " + id;
				break;

			default:
				System.out.println("Invalid choice! No update performed.");
				return;
			}
			// Execute the update query
			smt.executeUpdate(updateQuery);
			System.out.println("Product details updated successfully!");
		}

		else {
			// If the ID does not exist, insert a new product
			System.out.println("Product ID not found. Adding new product...");

			System.out.println("Enter Name of the Product:");
			p.setName(sc.next());

			System.out.println("Enter Price of the Product:");
			p.setPrice(sc.nextDouble());

			System.out.println("Enter Quantity of the Product:");
			p.setQuantity(sc.nextInt());

			System.out.println("Enter Category of the Product:");
			p.setCategory(sc.next());

			id = p.getId();
			String name = p.getName();
			Double price = p.getPrice();
			int Quantity = p.getQuantity();
			String Category = p.getCategory();

			String sql = "insert into product values('" + id + "' , '" + name + "', '" + price + "' , '" + Quantity
					+ "', '" + Category + "')";
			smt.execute(sql);
			System.out.println("Add Data Sucessfully:");
			System.out.println("___________________");

		}

	}

//		System.out.println("Enter  New Product Name to Update ");
//		String name =sc.next();
//		System.out.println("Enter new product price to update:");
//		Double price=sc.nextDouble();
//		System.out.println("Enter new Product Quantity to update:");
//		int Quantity=sc.nextInt();
//		System.out.println("Enter new product category to update:");
//		String Category =sc.next();
//	
//			
//		Statement smt=connection().createStatement();
//		//String sql="select * from product";
//		String sql="update product set name='"+ name+"' , price='"+price+"' , Quantity='"+Quantity+"',Category='"+Category+"'  Where id ="+ id; 
//		int executeUpdate = smt.executeUpdate(sql);
//		System.out.println("Update data sucessfully:");
//		System.out.println("___________________");
//		

	public void deleteProduct() throws ClassNotFoundException, SQLException {
		System.out.println("Enter ID of the Product to delete:");
		int id = sc.nextInt();

		Connection con = connection();
		Statement smt = con.createStatement();

		String sql = "delete from product Where id = " + id;
		int rowsDeleted = smt.executeUpdate(sql);
		System.out.println("Delete data from product table sucessfully:");
		System.out.println("___________________");
		con.close();
		smt.close();

	}

}