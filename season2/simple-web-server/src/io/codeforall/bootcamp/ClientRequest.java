package io.codeforall.bootcamp;

import java.net.Socket;

public class ClientRequest extends WebServer implements Runnable {
    private Socket clientSocket = null;

    public ClientRequest(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        dispatch(this.clientSocket);
    }
}
