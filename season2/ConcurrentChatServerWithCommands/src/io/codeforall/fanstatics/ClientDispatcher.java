package io.codeforall.fanstatics;

import java.io.*;
import java.net.Socket;

public class ClientDispatcher implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatServer chatServer;
    private String name;
    private boolean active = true;

    public ClientDispatcher(Socket clientSocket, ChatServer chatServer) {
        this.clientSocket = clientSocket;
        this.chatServer = chatServer;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            promptForName();

            // Main loop for receiving messages
            while (active) {
                String message = in.readLine();
                if (message != null) {
                    if (message.startsWith("/")) {
                        processCommand(message);
                    } else {
                        chatServer.broadcast(message, name);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error in client handler: " + e.getMessage());
        } finally {
            if (name != null) {
                chatServer.removeClient(name);
            }
            closeResources();
        }
    }

    private void promptForName() throws IOException {
        out.println("Enter your name:");
        name = in.readLine().trim();

        while (!chatServer.isNameAvailable(name) || name.isEmpty()) {
            out.println("Name already in use or invalid, please choose another:");
            name = in.readLine().trim();
        }

        chatServer.addClient(name, this);
        chatServer.broadcast(name + " has joined the chat.", "Server");
    }

    private void processCommand(String message) {
        if (message.equalsIgnoreCase("/list")) {
            chatServer.listClients(name);
        } else if (message.toLowerCase().startsWith("/whisper")) {
            String[] parts = message.split(" ", 3);
            if (parts.length == 3) {
                String recipient = parts[1];
                String privateMessage = parts[2];
                chatServer.whisper(privateMessage, name, recipient);
            } else {
                sendMessage("Usage: /whisper <name> <message>");
            }
        } else if (message.equalsIgnoreCase("/quit")) {
            active = false;
            sendMessage("You have left the chat.");
        } else if (message.equalsIgnoreCase("/help")) {
            sendHelp();
        } else {
            sendMessage("Unknown command: " + message);
        }
    }

    private void sendHelp() {
        sendMessage("Available commands:");
        sendMessage("/list - List all connected clients");
        sendMessage("/whisper <name> <message> - Send a private message to a specific user");
        sendMessage("/quit - Leave the chat");
        sendMessage("/help - Show this help message");
    }

    public void sendMessage(String message) {
        if (active) {
            out.println(message);
        }
    }

    private void closeResources() {
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing client socket: " + e.getMessage());
        }
    }
}
