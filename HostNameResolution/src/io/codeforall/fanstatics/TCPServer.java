package io.codeforall.fanstatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {

        int localPort = 8080;
        ServerSocket serverSocket = new ServerSocket(localPort);
        System.out.println("Server is running and waiting for clients...");

        while (true) {
            Socket clientSocket = serverSocket.accept();  // Accept client connection
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {  // Read messages from the client
                System.out.println(clientMessage);
                // Optionally send a response to the client
                // out.println("Message received");
            }

            clientSocket.close();  // Close the connection when done with this client
        }
    }
}
