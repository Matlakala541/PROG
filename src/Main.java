/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author RC_Student_Lab
 */
// Main.java
 import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = sc.nextLine();

        Login loginSystem = new Login(firstName, lastName);

        System.out.println("\n--- Registration ---");
        System.out.print("Enter a username (must contain _ and max 5 chars): ");
        String username = sc.nextLine();

        System.out.print("Enter a password (min 8 chars, 1 capital, 1 number, 1 special char): ");
        String password = sc.nextLine();

        System.out.print("Enter a cell number (must start with +27 and 9 digits): ");
        String cellNumber = sc.nextLine();

        String registrationMessage = loginSystem.registerUser(username, password, cellNumber);
        System.out.println(registrationMessage);

        if (!registrationMessage.equals("User registered successfully.")) {
            System.out.println("Registration failed. Please restart the program and try again.");
            return;
        }

        System.out.println("\n--- Login ---");
        System.out.print("Enter username: ");
        String loginUsername = sc.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = sc.nextLine();

        String loginMessage = loginSystem.returnLoginStatus(loginUsername, loginPassword);
        System.out.println(loginMessage);

        if (loginSystem.loginUser(loginUsername, loginPassword)) {
            MessageManager mgr = new MessageManager(loginSystem);
            mgr.runMenu();
        } else {
            System.out.println("Cannot proceed without valid login.");
        }
    }
}