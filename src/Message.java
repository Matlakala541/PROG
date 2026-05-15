/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
**
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

/**
 * Message class.
 * Contains generation of messageID, messageHash, validation, storing to JSON and display.
 */
public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private boolean sent;
    private boolean stored;
    private boolean disregarded;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText == null ? "" : messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        this.sent = false;
        this.stored = false;
        this.disregarded = false;
    }

    private String generateMessageID() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) sb.append(rnd.nextInt(10));
        return sb.toString();
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        if (recipient == null) return false;
        return recipient.matches("^\\+27\\d{9}$");
    }

    /**
     * Format: first two numbers of messageID + ":" + messageNumber + ":" + FIRSTANDLASTWORD (caps, no spaces)
     */
    public String createMessageHash() {
        String firstTwo = (messageID.length() >= 2) ? messageID.substring(0, 2) : messageID;
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

    public String validateMessageLength() {
        if (messageText == null) messageText = "";
        int length = messageText.length();
        if (length <= 250) return null;
        int over = length - 250;
        return String.format("Message exceeds 250 characters by %d, please reduce size.", over);
    }

    public String SentMessage(int choice) {
        if (choice == 1) {
            this.sent = true;
            this.disregarded = false;
            this.stored = false;
            return "Message successfully sent.";
        } else if (choice == 2) {
            this.sent = false;
            this.disregarded = true;
            this.stored = false;
            return "Press 0 to delete message.";
        } else if (choice == 3) {
            this.sent = false;
            this.disregarded = false;
            this.stored = true;
            return "Message successfully stored.";
        }
        return "Invalid choice.";
    }

    public boolean storeMessage() {
        File file = new File("stored_messages.json");
        try {
            String jsonObject = String.format(
                "{\"messageID\":\"%s\",\"messageNumber\":%d,\"recipient\":\"%s\",\"message\":\"%s\",\"messageHash\":\"%s\"}",
                escapeJson(messageID), messageNumber, escapeJson(recipient), escapeJson(messageText), escapeJson(messageHash)
            );

            if (!file.exists()) {
                FileWriter fw = new FileWriter(file);
                fw.write("[" + jsonObject + "]");
                fw.close();
            } else {
                java.nio.file.Path p = file.toPath();
                String content = new String(java.nio.file.Files.readAllBytes(p), java.nio.charset.StandardCharsets.UTF_8).trim();
                if (content.equals("")) {
                    content = "[" + jsonObject + "]";
                } else if (content.endsWith("]")) {
                    content = content.substring(0, content.length() - 1) + "," + jsonObject + "]";
                } else {
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

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "");
    }

    public void printMessageDetails() {
        String details = String.format("MessageID: %s\nMessage Hash: %s\nRecipient: %s\nMessage: %s",
                messageID, messageHash, recipient, messageText);
        try {
            JOptionPane.showMessageDialog(null, details, "Message Details", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            System.out.println(details);
        }
    }

    // Getters
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public boolean isSent() { return sent; }
    public boolean isStored() { return stored; }
    public boolean isDisregarded() { return disregarded; }
}