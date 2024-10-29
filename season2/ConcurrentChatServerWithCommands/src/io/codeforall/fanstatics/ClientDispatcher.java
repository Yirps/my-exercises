package io.codeforall.fanstatics;

import java.io.*;
import java.net.Socket;

public class ClientDispatcher implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatServer chatServer;
    private String name;

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

            // Prompt for a unique name
            out.println("Enter your name:");
            name = in.readLine().trim();
            while (chatServer.clients.containsKey(name) || name.isEmpty()) {
                out.println("Name already in use or invalid, please choose another:");
                name = in.readLine().trim();
            }

            chatServer.addClient(name, this);
            chatServer.broadcast(name + " has joined the chat.", "Server");

            // Listen for client messages
            String message;
            while ((message = in.readLine()) != null) {
                if (message.startsWith("/")) {
                    processCommand(message);
                } else {
                    chatServer.broadcast(message, name);
                }
            }
        } catch (IOException e) {
            System.err.println("Error in client handler: " + e.getMessage());
        } finally {
            chatServer.removeClient(name);
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
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
            chatServer.removeClient(name);
            sendMessage("You have left the chat.");
        } else {
            sendMessage("Unknown command: " + message);
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
