/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Student
 */
public class LoginNGTest {
    
    public LoginNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName_String() {
        System.out.println("checkUserName");
        String username = "";
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity_String() {
        System.out.println("checkPasswordComplexity");
        String password = "";
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber_String() {
        System.out.println("checkCellPhoneNumber");
        String cellNumber = "";
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellNumber);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser_3args() {
        System.out.println("registerUser");
        String username = "";
        String password = "";
        String cellNumber = "";
        Login instance = null;
        String expResult = "";
        String result = instance.registerUser(username, password, cellNumber);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String username = "";
        String password = "";
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.loginUser(username, password);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus_String_String() {
        System.out.println("returnLoginStatus");
        String username = "";
        String password = "";
        Login instance = null;
        String expResult = "";
        String result = instance.returnLoginStatus(username, password);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser_0args() {
        System.out.println("registerUser");
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.registerUser();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName_0args() {
        System.out.println("checkUserName");
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkUserName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity_0args() {
        System.out.println("checkPasswordComplexity");
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus_4args() {
        System.out.println("returnLoginStatus");
        String fname = "";
        String lname = "";
        String inUser = "";
        String inPass = "";
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.returnLoginStatus(fname, lname, inUser, inPass);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber_0args() {
        System.out.println("checkCellPhoneNumber");
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of firstNameSafe method, of class Login.
     */
    @Test
    public void testFirstNameSafe() {
        System.out.println("firstNameSafe");
        Login instance = null;
        Object[] expResult = null;
        Object[] result = instance.firstNameSafe();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
