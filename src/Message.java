/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_Lab
 */
//JSON storing helper method was created with assistance from ChatGPT
// (OpenAI, 2025).
// OpenAI. (2025) ChatGPT [Large language model]. Available at: https://chat.openai.com (Accessed: 2 September 2025).

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Message {
    private String messageID;      // 10-digit random ID (stored as String)
    private int messageNumber;     // auto-incremented per run/session (starts at 0)
    private String recipient;      // recipient phone (must be +27#########)
    private String messageText;    // message content
    private String messageHash;    // generated hash per spec
    private boolean sent;          // true if sent
    private boolean stored;        // true if stored to JSON
    private boolean disregarded;   // true if discarded

    // Constructor for new message
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        this.sent = false;
        this.stored = false;
        this.disregarded = false;
    }

    // Generate a random 10-digit ID as String
    private String generateMessageID() {
        Random rnd = new Random();
        // Ensure leading zeros allowed -> build as String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rnd.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * checkMessageID()
     * Ensures that the message ID is not more than 10 characters.
     */
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    /**
     * checkRecipientCell()
     * Ensures recipient phone is no more than 10 characters after country code
     * and starts with +27. We'll enforce: must match ^\+27\d{9}$
     * Returns 1 for valid, 0 for invalid (but tests use boolean variants).
     */
    public boolean checkRecipientCell() {
        if (recipient == null) return false;
        return recipient.matches("^\\+27\\d{9}$");
    }

    /**
     * createMessageHash()
     * Format per POE:
     * first two numbers of the message ID + ":" + messageNumber + ":" + FIRSTANDLASTWORD (all caps, no spaces)
     * Example: MessageID = "0012345678", messageNumber=0, messageText="Hi thanks"
     * -> "00:0:HITHANKS"
     */
    public String createMessageHash() {
        String firstTwo = (messageID.length() >= 2) ? messageID.substring(0,2) : messageID;
        String firstWord = "";
        String lastWord = "";
        if (messageText != null && !messageText.trim().isEmpty()) {
            String[] words = messageText.trim().split("\\s+");
            firstWord = words[0];
            lastWord = words[words.length - 1];
        }
        String combined = (firstWord + lastWord).toUpperCase().replaceAll("[^A-Z0-9]", "");
        return String.format("%s:%d:%s", firstTwo, messageNumber, combined);
    }

    /**
     * validateMessageLength()
     * Message must be <= 250 characters.
     * Returns null if OK, otherwise returns error message per POE:
     * "Message exceeds 250 characters by X, please reduce size."
     */
    public String validateMessageLength() {
        if (messageText == null) messageText = "";
        int length = messageText.length();
        if (length <= 250) return null;
        int over = length - 250;
        return String.format("Message exceeds 250 characters by %d, please reduce size.", over);
    }

    /**
     * SentMessage()
     * Presents options to the user (Send / Disregard / Store) and returns the action taken.
     * For console-based runs, use a simple inputChoice param in MessageManager; here we provide a helper
     * that takes a choice int: 1=Send, 2=Disregard, 3=Store.
     */
    public String SentMessage(int choice) {
        // 1) Send Message
        if (choice == 1) {
            this.sent = true;
            this.disregarded = false;
            this.stored = false;
            return "Message successfully sent.";
        }
        // 2) Disregard Message
        else if (choice == 2) {
            this.sent = false;
            this.disregarded = true;
            this.stored = false;
            return "Press 0 to delete message.";
        }
        // 3) Store Message
        else if (choice == 3) {
            this.sent = false;
            this.disregarded = false;
            this.stored = true;
            // storeMessage will be triggered by MessageManager typically
            return "Message successfully stored.";
        }
        return "Invalid choice.";
    }

    /**
     * storeMessage()
     * Appends the message as a JSON object to a local file "stored_messages.json".
     *
     *
     * The JSON written is an array in the file. If the file does not exist, it is created.
     *
     * This method was written with assistance from ChatGPT (OpenAI, 2025) to construct JSON safely
     * and handle simple append behavior.
     */
    public boolean storeMessage() {
        File file = new File("stored_messages.json");
        try {
            String jsonObject = String.format(
                "{\"messageID\":\"%s\",\"messageNumber\":%d,\"recipient\":\"%s\",\"message\":\"%s\",\"messageHash\":\"%s\"}",
                escapeJson(messageID), messageNumber, escapeJson(recipient), escapeJson(messageText), escapeJson(messageHash)
            );

            if (!file.exists()) {
                // create new array file
                FileWriter fw = new FileWriter(file);
                fw.write("[" + jsonObject + "]");
                fw.close();
            } else {
                // append to existing array 
                // read the whole file, remove the trailing 
                java.nio.file.Path p = file.toPath();
                String content = new String(java.nio.file.Files.readAllBytes(p), java.nio.charset.StandardCharsets.UTF_8).trim();
                if (content.equals("")) {
                    content = "[" + jsonObject + "]";
                } else if (content.endsWith("]")) {
                    content = content.substring(0, content.length() - 1) + "," + jsonObject + "]";
                } else {
                    // fallback: overwrite
                    content = "[" + jsonObject + "]";
                }
                FileWriter fw = new FileWriter(file, false);
                fw.write(content);
                fw.close();
            }
            this.stored = true;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // helper to escape JSON special chars
    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "");
    }

    /**
     * printMessageDetails()
     * Display full details (MessageID, MessageHash, Recipient, Message) per POE using JOptionPane.
     * Also prints to console (so tests can capture output if needed).
     */
    public void printMessageDetails() {
        String details = String.format("MessageID: %s\nMessage Hash: %s\nRecipient: %s\nMessage: %s",
                messageID, messageHash, recipient, messageText);
        try {
            JOptionPane.showMessageDialog(null, details, "Message Details", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            // If running headless, print to console
            System.out.println(details);
        }
    }

    // Getters (used by MessageManager and tests)
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public boolean isSent() { return sent; }
    public boolean isStored() { return stored; }
    public boolean isDisregarded() { return disregarded; }
}
