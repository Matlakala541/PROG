/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
// MessageTest.java
// TestNG tests for Message class and basic manager behaviors
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MessageNGTest {

    @Test
    public void testMessageLengthSuccess() {
        Message m = new Message(0, "+27830000000", "Short message");
        assertNull(m.validateMessageLength());
    }

    @Test
    public void testMessageLengthFail() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) sb.append("A");
        String longMsg = sb.toString();
        Message m = new Message(0, "+27830000000", longMsg);
        assertTrue(m.validateMessageLength().contains("Message exceeds 250 characters"));
    }

    @Test
    public void testRecipientCorrect() {
        Message m = new Message(0, "+27830000000", "Hello");
        assertTrue(m.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrect() {
        Message m = new Message(0, "08575975889", "Hello");
        assertFalse(m.checkRecipientCell());
    }

    @Test
    public void testHash() {
        Message m = new Message(0, "+27830000000", "Hi tonight");
        String hash = m.createMessageHash();
        assertTrue(hash.contains(":0:HI"));
    }

    @Test
    public void testSendMessageOptions() {
        Message m = new Message(0, "+27830000000", "Hello");

        assertEquals(m.SentMessage(1), "Message successfully sent.");
        assertEquals(m.SentMessage(2), "Press 0 to delete message.");
        assertEquals(m.SentMessage(3), "Message successfully stored.");
    }
}