package io.codeforall.fanstatics;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.nio.file.Files;

public class SimpleWebServer {
    public static void main(String[] args) throws IOException {

        int port = 8080;

        //creating server
        System.out.println("Binding to port " + port);
        ServerSocket serverSocket = new ServerSocket(port);

        //server listening and waiting for a client
        System.out.println("Waiting for a client connection");
        Socket clientSocket = serverSocket.accept();

        // handle client connection
        System.out.println("Client accepted: " + clientSocket);

        //read and write stream
        //output
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        //input
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        String resourceRequested = in.readLine().split(" ")[1];
        System.out.println(resourceRequested);
        if (resourceRequested.equals("/") || resourceRequested.equals("/index.html")) {
            File file = new File("simple-web-server/www/index.html");
            out.println("HTTP/1.0 200 Document Follows\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Content-Length: 1000 \r\n" +
                    "\r\n" + file);

        } else if (resourceRequested.equals("/logo.png")) {
            File file = new File("simple-web-server/www/logo.png");
            out.println("HTTP/1.0 200 Document Follows\r\n" +
                    "Content-Type: image/<image_file_extension> \r\n" +
                    "Content-Length: <file_byte_size> \r\n" +
                    "\r\n" + file);

        } else {
            File file = new File("simple-web-server/www/404.html");
            out.println("HTTP/1.0 404 Not Found" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Content-Length: <file_byte_size> \r\n" +
                    "\r\n" + file);
        }
    }
}
