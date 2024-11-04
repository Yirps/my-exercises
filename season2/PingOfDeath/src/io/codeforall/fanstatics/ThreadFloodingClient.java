package io.codeforall.fanstatics;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

public class ThreadFloodingClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8090;
    private static final int NUM_CONNECTIONS = 10000; // Number of simultaneous connections

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_CONNECTIONS);

        for (int i = 0; i < NUM_CONNECTIONS; i++) {
            executorService.submit(() -> {
                try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
                    System.out.println("Connected to server on port " + SERVER_PORT);
                } catch (IOException e) {
                    System.err.println("Connection error: " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }
}

