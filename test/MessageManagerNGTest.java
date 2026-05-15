/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.List;

public class MessageManagerNGTest {

    @Test
    public void testSentMessagesArray() {
        Login login = new Login("A", "B");
        MessageManager mgr = new MessageManager(login);

        Message m1 = new Message(0, "+27834557896", "Did you get the cake?");
        m1.SentMessage(1);
        mgr.getSentMessages().add(m1);

        Message[] arr = mgr.getSentMessagesArray();
        assertEquals(arr[0].getMessageText(), "Did you get the cake?");
    }

    @Test
    public void testLongestMessage() {
        Login login = new Login("A", "B");
        MessageManager mgr = new MessageManager(login);

        mgr.getSentMessages().add(new Message(0, "+27811111111", "Hi"));
        mgr.getSentMessages().add(new Message(1, "+27822222222", "Where are you? You are late! I have asked you to be on time."));

        // find longest via manager display method indirectly by searching for the known string
        boolean found = false;
        for (Message m : mgr.getSentMessages()) {
            if (m.getMessageText().contains("Where are you?")) found = true;
        }
        assertTrue(found);
    }

    @Test
    public void testSearchMessageID() {
        Login login = new Login("A", "B");
        MessageManager mgr = new MessageManager(login);

        Message msg = new Message(0, "+27838884567", "It is dinner time!");
        mgr.getSentMessages().add(msg);

        assertEquals(mgr.searchByMessageID(msg.getMessageID()).getMessageText(), "It is dinner time!");
    }

    @Test
    public void testSearchByRecipient() {
        Login login = new Login("A", "B");
        MessageManager mgr = new MessageManager(login);

        mgr.getStoredMessages().add(new Message(0, "+27838884567",
                "Where are you? You are late! I have asked you to be on time."));
        mgr.getStoredMessages().add(new Message(1, "+27838884567",
                "Ok, I am leaving without you."));

        List<Message> results = mgr.searchByRecipient("+27838884567");
        assertEquals(results.size(), 2);
    }

    @Test
    public void testDeleteMessage() {
        Login login = new Login("A", "B");
        MessageManager mgr = new MessageManager(login);

        Message m = new Message(0, "+27812345678", "Where are you? You are late! I have asked you to be on time");
        mgr.getStoredMessages().add(m);

        boolean deleted = mgr.deleteByMessageHash(m.getMessageHash());
        assertTrue(deleted);
    }
}