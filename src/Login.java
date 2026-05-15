/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;
/**
 *
 * @author RC_Student_lab
 */public class Login {
// This class handles registration and login functionality for a simple chat app.
// It validates usernames, passwords, and South African phone numbers.
    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;

    // Constructor to store the user's first and last name for personalized greetings
    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Validates the username.
     * Conditions: must contain an underscore (_) and be 5 characters or fewer.
     */
    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            this.username = username;
            return true;
        }
        return false;
    }

    /**
     // Validates the password complexity using regex.
     //Conditions:
     // - At least 8 characters long
     *  - Contains at least one uppercase letter
     *  - Contains at least one digit
     *  - Contains at least one special character
     *
     // Regex explanation:
     * ^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*(),.?":{}|<>]).{8,}$
     *  (?=.*[A-Z]) → at least one uppercase
     *  (?=.*\d) → at least one digit
     *  (?=.*[!@#$%^&*(),.?":{}|<>]) → at least one special character
     *  .{8,} → at least 8 characters long
     */
    //Regex source generated with the assistance of chatGPT (openAI ,2025).
    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        if (password.matches(regex)) {
            this.password = password;
            return true;
        }
        return false;
    }

    /**
     // Validates the South African cell phone number.
     // Conditions: must start with +27 and be followed by 9 digits.
     //Regex source generated with assistance of ChatGPT (OpenAI, 2025).
     *
     */
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber.matches("^\\+27\\d{9}$")) {
            this.cellNumber = cellNumber;
            return true;
        }
        return false;
    }

    /**
     * Registers a user by validating username, password, and cell number.
     * Returns appropriate error or success messages.
     */
    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number is incorrectly formatted or does not contain international code.";
        }
        return "User registered successfully.";
    }

    /**
     * Checks login details against stored registration details.
     */
    public boolean loginUser(String username, String password) {
        return this.username != null && this.password != null
                && this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Returns a login status message based on login success or failure.
     */
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        }
        return "Username or password is incorrect, please try again.";
    }
}