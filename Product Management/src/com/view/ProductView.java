package com.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.ProductController;
import com.model.Product;
//import com.model.Product;

public class ProductView {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Scanner sc = new Scanner(System.in);
		Product pd = new Product();
		ProductController pc = new ProductController();
		ProductController.connection();

		while (true) {
			System.out.println("____Welcome to Product Management Project____");
			System.out.println("\t 1: add Product\n" + "\t 2: View All Product\n" + "\t 3: Update Product\n"
					+ "\t 4: Delete Product\n" + "\t 5:Exit\n");
			System.out.println("___________________");

			System.out.println("Enter Your Choice:");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				pc.addProduct();
				break;

			case 2:
				pc.viewProduct();
				break;

			case 3:
				pc.updateProduct();

				break;
			case 4:
				pc.deleteProduct();
				break;

			case 5:
				System.out.println("Exits..! Thank You!");
				System.exit(0);

			default:
				System.out.println("invalid choice");
				break;

			}

		}

	}
}