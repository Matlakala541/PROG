/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LoginNGTest {
    private Login login;

    @BeforeMethod
    public void setUp() {
        login = new Login("Kyle", "Smith");
    }

    // ---------- Username Tests ----------
    @Test
    public void testValidUsername() {
        assertTrue(login.checkUserName("kyl_1"), 
            "Username should be valid when it contains an underscore and is ≤ 5 chars.");
    }

    @Test
    public void testInvalidUsername() {
        assertFalse(login.checkUserName("kyle!!!!!!!"), 
            "Username should be invalid without underscore and exceeding length.");
    }

    // ---------- Password Tests ----------
    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"), 
            "Password should be valid with uppercase, number, special character, and ≥ 8 chars.");
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(login.checkPasswordComplexity("password"), 
            "Password should be invalid if it does not meet complexity rules.");
    }

    // ---------- Cell Number Tests ----------
    @Test
    public void testValidCellNumber() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"),
            "Cell number should be valid with +27 followed by 9 digits.");
    }

    @Test
    public void testInvalidCellNumber() {
        assertFalse(login.checkCellPhoneNumber("08966553"),
            "Cell number should be invalid if it does not start with +27 and 9 digits.");
    }

    // ---------- Registration Tests ----------
    @Test
    public void testSuccessfulRegistration() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(result, "User registered successfully.");
    }

    @Test
    public void testFailedRegistration() {
        String result = login.registerUser("kyle!!!!!!!", "password", "08966553");
        assertTrue(result.contains("Username is not correctly formatted")
                || result.contains("Password is not correctly formatted")
                || result.contains("Cell phone number incorrectly formatted"));
    }

    // ---------- Login Tests ----------
    @Test
    public void testSuccessfulLogin() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testFailedLogin() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("wrong", "wrongPass"));
    }

    // ---------- Login Status Tests ----------
    @Test
    public void testReturnLoginStatusSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"),
                     "Welcome Kyle Smith, it is great to see you again.");
    }

    @Test
    public void testReturnLoginStatusFailure() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(login.returnLoginStatus("wrong", "password"),
                     "Username or password is incorrect, please try again.");
    }
}