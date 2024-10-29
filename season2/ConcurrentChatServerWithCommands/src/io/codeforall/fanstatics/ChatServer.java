package io.codeforall.fanstatics;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    public static final int LOCAL_PORT = 8085;
    private ExecutorService es;
    private ServerSocket serverSocket;
    final Map<String, ClientDispatcher> clients = new HashMap<>();

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.listen(LOCAL_PORT);
    }

    private void listen(int localPort) {
        try {
            serverSocket = new ServerSocket(localPort);
            es = Executors.newCachedThreadPool();
            System.out.println("Chat server started on port " + localPort);
            dispatchClients();
        } catch (IOException e) {
            throw new RuntimeException("Error starting server: " + e.getMessage());
        }
    }

    private void dispatchClients() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientDispatcher clientDispatcher = new ClientDispatcher(clientSocket, this);
                es.submit(clientDispatcher);
            } catch (IOException e) {
                System.err.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    public synchronized void broadcast(String message, String sender) {
        for (ClientDispatcher client : clients.values()) {
            if (!client.getName().equals(sender)) {
                client.sendMessage(sender + ": " + message);
            }
        }
    }

    public synchronized void whisper(String message, String sender, String recipient) {
        ClientDispatcher client = clients.get(recipient);
        if (client != null) {
            client.sendMessage("Whisper from " + sender + ": " + message);
        } else {
            ClientDispatcher senderClient = clients.get(sender);
            if (senderClient != null) {
                senderClient.sendMessage("User " + recipient + " is not online.");
            }
        }
    }

    public synchronized void listClients(String requester) {
        ClientDispatcher client = clients.get(requester);
        if (client != null) {
            client.sendMessage("Connected clients: " + String.join(", ", clients.keySet()));
        }
    }

    public synchronized void addClient(String name, ClientDispatcher clientDispatcher) {
        clients.put(name, clientDispatcher);
    }

    public synchronized void removeClient(String name) {
        clients.remove(name);
        broadcast(name + " has left the chat.", "Server");
    }
}
