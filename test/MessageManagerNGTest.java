/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import java.util.List;
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
public class MessageManagerNGTest {
    
    public MessageManagerNGTest() {
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
     * Test of runMenu method, of class MessageManager.
     */
    @Test
    public void testRunMenu() {
        System.out.println("runMenu");
        MessageManager instance = null;
        instance.runMenu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displaySendersAndRecipients method, of class MessageManager.
     */
    @Test
    public void testDisplaySendersAndRecipients() {
        System.out.println("displaySendersAndRecipients");
        MessageManager instance = null;
        instance.displaySendersAndRecipients();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayLongestSentMessage method, of class MessageManager.
     */
    @Test
    public void testDisplayLongestSentMessage() {
        System.out.println("displayLongestSentMessage");
        MessageManager instance = null;
        instance.displayLongestSentMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchByMessageID method, of class MessageManager.
     */
    @Test
    public void testSearchByMessageID() {
        System.out.println("searchByMessageID");
        String messageID = "";
        MessageManager instance = null;
        Message expResult = null;
        Message result = instance.searchByMessageID(messageID);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchByRecipient method, of class MessageManager.
     */
    @Test
    public void testSearchByRecipient() {
        System.out.println("searchByRecipient");
        String recipient = "";
        MessageManager instance = null;
        List expResult = null;
        List result = instance.searchByRecipient(recipient);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByMessageHash method, of class MessageManager.
     */
    @Test
    public void testDeleteByMessageHash() {
        System.out.println("deleteByMessageHash");
        String messageHash = "";
        MessageManager instance = null;
        boolean expResult = false;
        boolean result = instance.deleteByMessageHash(messageHash);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayReport method, of class MessageManager.
     */
    @Test
    public void testDisplayReport() {
        System.out.println("displayReport");
        MessageManager instance = null;
        instance.displayReport();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readStoredMessagesFromJson method, of class MessageManager.
     */
    @Test
    public void testReadStoredMessagesFromJson() {
        System.out.println("readStoredMessagesFromJson");
        MessageManager instance = null;
        instance.readStoredMessagesFromJson();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentMessagesArray method, of class MessageManager.
     */
    @Test
    public void testGetSentMessagesArray() {
        System.out.println("getSentMessagesArray");
        MessageManager instance = null;
        Message[] expResult = null;
        Message[] result = instance.getSentMessagesArray();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredMessagesArray method, of class MessageManager.
     */
    @Test
    public void testGetStoredMessagesArray() {
        System.out.println("getStoredMessagesArray");
        MessageManager instance = null;
        Message[] expResult = null;
        Message[] result = instance.getStoredMessagesArray();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisregardedMessagesArray method, of class MessageManager.
     */
    @Test
    public void testGetDisregardedMessagesArray() {
        System.out.println("getDisregardedMessagesArray");
        MessageManager instance = null;
        Message[] expResult = null;
        Message[] result = instance.getDisregardedMessagesArray();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageHashesArray method, of class MessageManager.
     */
    @Test
    public void testGetMessageHashesArray() {
        System.out.println("getMessageHashesArray");
        MessageManager instance = null;
        String[] expResult = null;
        String[] result = instance.getMessageHashesArray();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageIDsArray method, of class MessageManager.
     */
    @Test
    public void testGetMessageIDsArray() {
        System.out.println("getMessageIDsArray");
        MessageManager instance = null;
        String[] expResult = null;
        String[] result = instance.getMessageIDsArray();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessages method, of class MessageManager.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        MessageManager instance = null;
        String expResult = "";
        String result = instance.printMessages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTotalMessages method, of class MessageManager.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        MessageManager instance = null;
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentMessages method, of class MessageManager.
     */
    @Test
    public void testGetSentMessages() {
        System.out.println("getSentMessages");
        MessageManager instance = null;
        List expResult = null;
        List result = instance.getSentMessages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredMessages method, of class MessageManager.
     */
    @Test
    public void testGetStoredMessages() {
        System.out.println("getStoredMessages");
        MessageManager instance = null;
        List expResult = null;
        List result = instance.getStoredMessages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisregardedMessages method, of class MessageManager.
     */
    @Test
    public void testGetDisregardedMessages() {
        System.out.println("getDisregardedMessages");
        MessageManager instance = null;
        List expResult = null;
        List result = instance.getDisregardedMessages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageHashes method, of class MessageManager.
     */
    @Test
    public void testGetMessageHashes() {
        System.out.println("getMessageHashes");
        MessageManager instance = null;
        List expResult = null;
        List result = instance.getMessageHashes();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageIDs method, of class MessageManager.
     */
    @Test
    public void testGetMessageIDs() {
        System.out.println("getMessageIDs");
        MessageManager instance = null;
        List expResult = null;
        List result = instance.getMessageIDs();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
