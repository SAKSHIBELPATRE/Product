package com.view.app;

import com.controller.app.BankController;

import java.sql.SQLException;
import java.util.Scanner;

public class BankView {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        BankController controller = new BankController();

        while (true) {
            System.out.println("____Welcome to Bank Management System____");
            System.out.println("1. Add Bank Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Update Bank Account");
            System.out.println("4. Delete Bank Account");
            System.out.println("5. Exit");
            System.out.println("_________________________________________");

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    controller.addAccount();
                    break;

                case 2:
                    controller.viewAccounts();
                    break;

                case 3:
                    controller.updateAccount();
                    break;

                case 4:
                    controller.deleteAccount();
                    break;

                case 5:
                    System.out.println("Thank you for using the Bank Management System. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
