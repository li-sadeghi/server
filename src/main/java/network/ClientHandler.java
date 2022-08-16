package network;

import org.codehaus.jackson.map.ObjectMapper;
import request.Request;
import response.Response;
import util.Jackson;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    private PrintStream printStream;
    private Scanner scanner;

    private ObjectMapper objectMapper;
    private final Server server;

    private final int clientId;

    public ClientHandler(int clientId, Server server, Socket socket) {
        this.clientId = clientId;
        this.server = server;

        try {
            printStream = new PrintStream(socket.getOutputStream());
            scanner = new Scanner(socket.getInputStream());
            objectMapper = Jackson.getNetworkObjectMapper();
            makeListenerThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeListenerThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                String requestString = scanner.nextLine();
                try {
                    Request request = objectMapper.readValue(requestString, Request.class);
                    handleRequest(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void handleRequest(Request request) {
        server.handleRequest(clientId, request);
    }

    public void sendResponse(Response response) {
        try {
            String responseString = objectMapper.writeValueAsString(response);
            printStream.println(responseString);
            printStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return clientId;
    }
}
