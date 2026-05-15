/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author RC_Student_Lab
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MessageManager - handles Part 2 and Part 3 features:
 * - sending/storing/disregarding messages
 * - arrays for sent/stored/disregarded messages
 * - search/delete/report features (Part 3)
 *
 * JSON reading helper was assisted by ChatGPT (OpenAI, 2025).
 */
public class MessageManager {
    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> disregardedMessages = new ArrayList<>();
    private List<Message> storedMessages = new ArrayList<>();
    private List<String> messageHashes = new ArrayList<>();
    private List<String> messageIDs = new ArrayList<>();
    private int messageCounter = 0;

    private Login login;

    public MessageManager(Login login) {
        this.login = login;
        // load any stored messages at startup
        readStoredMessagesFromJson();
        if (!messageIDs.isEmpty()) {
            messageCounter = Math.max(messageCounter, messageIDs.size());
        }
    }

    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to QuickChat.");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages (Coming Soon)");
            System.out.println("3) Quit");
            System.out.println("4) Part 3 - Message Storage & Report Features");
            System.out.print("Enter option (1-4): ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) {
                sendMessagesFlow(sc);
            } else if (opt.equals("2")) {
                System.out.println("Coming Soon.");
            } else if (opt.equals("3")) {
                System.out.println("Goodbye.");
                break;
            } else if (opt.equals("4")) {
                part3Menu(sc);
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }

    private void sendMessagesFlow(Scanner sc) {
        System.out.print("How many messages would you like to enter? ");
        int n = 0;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
            if (n <= 0) {
                System.out.println("Please enter a positive integer.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("\n--- Enter message %d of %d ---\n", i+1, n);
            System.out.print("Enter recipient number (must start with +27 and have 9 digits): ");
            String recipient = sc.nextLine().trim();

            System.out.print("Enter message (max 250 characters): ");
            String msgText = sc.nextLine();

            Message msg = new Message(messageCounter, recipient, msgText);
            messageCounter++;

            String lenError = msg.validateMessageLength();
            if (lenError != null) {
                System.out.println(lenError);
                continue;
            }

            if (!msg.checkRecipientCell()) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                continue;
            }

            System.out.println("Choose option: 1) Send Message   2) Disregard Message   3) Store Message to send later");
            System.out.print("Enter 1, 2 or 3: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception ex) {
                choice = 0;
            }

            String actionResult = msg.SentMessage(choice);
            System.out.println(actionResult);

            if (choice == 3) {
                boolean ok = msg.storeMessage();
                if (ok) {
                    storedMessages.add(msg);
                } else {
                    System.out.println("Storing failed.");
                }
            } else if (choice == 2) {
                disregardedMessages.add(msg);
            } else if (choice == 1) {
                sentMessages.add(msg);
            } else {
                System.out.println("Invalid choice - message was disregarded.");
                disregardedMessages.add(msg);
            }

            messageHashes.add(msg.getMessageHash());
            messageIDs.add(msg.getMessageID());

            msg.printMessageDetails();
        }

        System.out.println("Total messages processed in this session: " + returnTotalMessages());
    }

    private void part3Menu(Scanner sc) {
        while (true) {
            System.out.println("\n--- Part 3 Menu ---");
            System.out.println("1) Display sender and recipient of all sent messages");
            System.out.println("2) Display the longest sent message");
            System.out.println("3) Search for a message by Message ID");
            System.out.println("4) Search for all messages sent to a particular recipient");
            System.out.println("5) Delete a message using message hash");
            System.out.println("6) Display full report of sent messages");
            System.out.println("7) Reload stored messages from JSON file");
            System.out.println("8) Return to main menu");
            System.out.print("Choose option (1-8): ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) {
                displaySendersAndRecipients();
            } else if (opt.equals("2")) {
                displayLongestSentMessage();
            } else if (opt.equals("3")) {
                System.out.print("Enter Message ID to search: ");
                String id = sc.nextLine().trim();
                Message found = searchByMessageID(id);
                if (found != null) {
                    System.out.println("Found message -> Recipient: " + found.getRecipient() + " Message: " + found.getMessageText());
                } else {
                    System.out.println("Message ID not found.");
                }
            } else if (opt.equals("4")) {
                System.out.print("Enter recipient number to search (e.g. +27834557896): ");
                String r = sc.nextLine().trim();
                List<Message> results = searchByRecipient(r);
                if (results.isEmpty()) {
                    System.out.println("No messages found for recipient " + r);
                } else {
                    System.out.println("Messages for " + r + ":");
                    for (Message m : results) {
                        System.out.println("- " + m.getMessageText());
                    }
                }
            } else if (opt.equals("5")) {
                System.out.print("Enter message hash to delete: ");
                String h = sc.nextLine().trim();
                boolean ok = deleteByMessageHash(h);
                if (ok) {
                    System.out.println("Message with hash " + h + " successfully deleted (if it existed).");
                } else {
                    System.out.println("No message with that hash was found.");
                }
            } else if (opt.equals("6")) {
                displayReport();
            } else if (opt.equals("7")) {
                readStoredMessagesFromJson();
                System.out.println("Reload complete. Stored messages count: " + storedMessages.size());
            } else if (opt.equals("8")) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    public void displaySendersAndRecipients() {
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages to display.");
            return;
        }
        for (Message m : sentMessages) {
            System.out.printf("Sender: %s %s, Recipient: %s\n",
                    login != null ? login.firstNameSafe() : "Unknown",
                    m.getRecipient());
        }
    }

    public void displayLongestSentMessage() {
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages.");
            return;
        }
        Message longest = sentMessages.get(0);
        for (Message m : sentMessages) {
            if (m.getMessageText() != null && m.getMessageText().length() > (longest.getMessageText() == null ? 0 : longest.getMessageText().length())) {
                longest = m;
            }
        }
        System.out.println("Longest sent message: " + longest.getMessageText());
    }

    public Message searchByMessageID(String messageID) {
        for (Message m : sentMessages) if (m.getMessageID().equals(messageID)) return m;
        for (Message m : storedMessages) if (m.getMessageID().equals(messageID)) return m;
        for (Message m : disregardedMessages) if (m.getMessageID().equals(messageID)) return m;
        return null;
    }

    public List<Message> searchByRecipient(String recipient) {
        List<Message> results = new ArrayList<>();
        for (Message m : sentMessages) if (recipient.equals(m.getRecipient())) results.add(m);
        for (Message m : storedMessages) if (recipient.equals(m.getRecipient())) results.add(m);
        for (Message m : disregardedMessages) if (recipient.equals(m.getRecipient())) results.add(m);
        return results;
    }

    public boolean deleteByMessageHash(String messageHash) {
        boolean removed = false;
        removed |= removeFromListByHash(sentMessages, messageHash);
        removed |= removeFromListByHash(storedMessages, messageHash);
        removed |= removeFromListByHash(disregardedMessages, messageHash);
        while (messageHashes.remove(messageHash)) { removed = true; }
        return removed;
    }

    private boolean removeFromListByHash(List<Message> list, String hash) {
        boolean removed = false;
        List<Message> toRemove = new ArrayList<>();
        for (Message m : list) {
            if (hash.equals(m.getMessageHash())) {
                toRemove.add(m);
            }
        }
        if (!toRemove.isEmpty()) {
            list.removeAll(toRemove);
            removed = true;
        }
        return removed;
    }

    public void displayReport() {
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages to report.");
            return;
        }
        System.out.println("\n--- Sent Messages Report ---");
        for (Message m : sentMessages) {
            System.out.printf("Message Hash: %s\nRecipient: %s\nMessage: %s\n\n",
                    m.getMessageHash(), m.getRecipient(), m.getMessageText());
        }
    }

    /**
     * Read stored messages from stored_messages.json into storedMessages list and helper arrays.
     * Simple parser (no external libs). Attributed to ChatGPT (OpenAI, 2025).
     */
    public void readStoredMessagesFromJson() {
        File file = new File("stored_messages.json");
        if (!file.exists()) return;
        try {
            String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8).trim();
            if (content.length() == 0) return;
            if (content.startsWith("[")) content = content.substring(1);
            if (content.endsWith("]")) content = content.substring(0, content.length() - 1);
            String[] objects = content.split("\\},\\s*\\{");
            for (int i = 0; i < objects.length; i++) {
                String obj = objects[i].trim();
                if (!obj.startsWith("{")) obj = "{" + obj;
                if (!obj.endsWith("}")) obj = obj + "}";
                String msgId = extractJsonString(obj, "messageID");
                String recipient = extractJsonString(obj, "recipient");
                String message = extractJsonString(obj, "message");
                String messageHash = extractJsonString(obj, "messageHash");
                String msgNumStr = extractJsonString(obj, "messageNumber");
                int msgNum = 0;
                try { msgNum = Integer.parseInt(msgNumStr); } catch (Exception ex) { msgNum = messageCounter++; }

                Message m = new Message(msgNum, recipient, message);
                storedMessages.add(m);

                if (messageHash != null && !messageHash.isEmpty()) messageHashes.add(messageHash);
                else messageHashes.add(m.getMessageHash());

                if (msgId != null && !msgId.isEmpty()) messageIDs.add(msgId);
                else messageIDs.add(m.getMessageID());
            }
        } catch (Exception e) {
            System.out.println("Failed to read stored_messages.json: " + e.getMessage());
        }
    }

    private String extractJsonString(String json, String key) {
        Pattern p = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"(.*?)\"", Pattern.DOTALL);
        Matcher m = p.matcher(json);
        if (m.find()) {
            return unescapeJson(m.group(1));
        }
        Pattern p2 = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(\\d+)");
        Matcher m2 = p2.matcher(json);
        if (m2.find()) {
            return m2.group(1);
        }
        return "";
    }

    private String unescapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\\"", "\"").replace("\\\\", "\\").replace("\\n", "\n").replace("\\r", "\r");
    }

    // Getters for tests
    public Message[] getSentMessagesArray() { return sentMessages.toArray(new Message[0]); }
    public Message[] getStoredMessagesArray() { return storedMessages.toArray(new Message[0]); }
    public Message[] getDisregardedMessagesArray() { return disregardedMessages.toArray(new Message[0]); }
    public String[] getMessageHashesArray() { return messageHashes.toArray(new String[0]); }
    public String[] getMessageIDsArray() { return messageIDs.toArray(new String[0]); }

    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append(String.format("ID:%s Hash:%s Recipient:%s Message:%s\n",
                    m.getMessageID(), m.getMessageHash(), m.getRecipient(), m.getMessageText()));
        }
        return sb.toString();
    }

    public int returnTotalMessages() {
        return sentMessages.size() + storedMessages.size() + disregardedMessages.size();
    }

    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
    public List<String> getMessageHashes() { return messageHashes; }
    public List<String> getMessageIDs() { return messageIDs; }
}