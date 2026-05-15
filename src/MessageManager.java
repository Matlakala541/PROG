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

public class MessageManager {
    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> disregardedMessages = new ArrayList<>();
    private List<Message> storedMessages = new ArrayList<>();
    private List<String> messageHashes = new ArrayList<>();
    private List<String> messageIDs = new ArrayList<>();
    private int messageCounter = 0; // increments for each message entered (used in Message constructor)

    private Login login;

    public MessageManager(Login login) {
        this.login = login;
    }

    // Show main menu and handle user interaction
    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to QuickChat.");

        // User must be logged in to proceed
       

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages (Coming Soon)");
            System.out.println("3) Quit");
            System.out.print("Enter option (1-3): ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) {
                sendMessagesFlow(sc);
            } else if (opt.equals("2")) {
                System.out.println("Coming Soon.");
            } else if (opt.equals("3")) {
                System.out.println("Goodbye.");
                break;
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }

    // Flow to send N messages (user defines N)
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

            // validate message length
            String lenError = msg.validateMessageLength();
            if (lenError != null) {
                System.out.println(lenError);
                // Treat as failure for this message; continue to next
                continue;
            }

            // validate recipient
            if (!msg.checkRecipientCell()) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                continue;
            }

            // Show the user the send/store/discard choice
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

            // If store chosen, persist and add to storedMessages
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

            // update helper lists
            messageHashes.add(msg.getMessageHash());
            messageIDs.add(msg.getMessageID());

            // After action, display full details per POE using JOptionPane (or console fallback)
            msg.printMessageDetails();
        }

        // After all messages, display total
        System.out.println("Total messages processed in this session: " + returnTotalMessages());
    }

    // Print all sent messages (strings)
    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append(String.format("ID:%s Hash:%s Recipient:%s Message:%s\n",
                    m.getMessageID(), m.getMessageHash(), m.getRecipient(), m.getMessageText()));
        }
        return sb.toString();
    }

    // Returns total number of messages sent (sentMessages list size)
    public int returnTotalMessages() {
        return sentMessages.size() + storedMessages.size() + disregardedMessages.size();
    }
    
    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
    public List<String> getMessageHashes() { return messageHashes; }
    public List<String> getMessageIDs() { return messageIDs; }
}
