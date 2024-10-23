package io.codeforall.fanstatics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 8080);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String userInput;
        while ((userInput = stdIn.readLine()) != null) {  // Read input from the console
            out.println(userInput);  // Send the input to the server
            // Optionally print server response (if the server is programmed to respond)
            // System.out.println("Server: " + in.readLine());
        }

        socket.close();  // Close the socket when done
    }
}
