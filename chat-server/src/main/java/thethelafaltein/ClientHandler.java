package thethelafaltein;

import java.io.*;
import java.net.Socket;
import java.util.*;


public class ClientHandler extends Thread {
    private final Socket socket;
    private  PrintWriter out;
    private final Broadcaster broadcaster;
    private Set<ClientHandler> clientHandlers = new HashSet<>();

    public ClientHandler(Socket socket, Broadcaster broadcaster, Set<ClientHandler> clientHandlers) {
        this.socket = socket;
        this.broadcaster = broadcaster;
        this.clientHandlers = clientHandlers;
   

        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Failed to initialize output stream for client: " + e.getMessage());
            return;
        }
    }

    @Override
    public void run() {
        boolean hasSentEndMarker = false;

        try {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.printf("Message received from %s: %s%n", socket.getInetAddress().toString(), line);
                    broadcaster.broadcastMessage(line);
                    hasSentEndMarker = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading message from client: " + e.getMessage());
        } finally {
            if (!hasSentEndMarker) {
                System.out.printf("%s left the chat session%n", socket.getInetAddress().toString());
                clientHandlers.remove(this);
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println("Couldn't close socket cleanly");
                }
            }
        }
    }

    public void sendMessage(String message) {
        out.print(message);
        out.flush();
        System.out.printf("Message sent to %s: %s%n", socket.getInetAddress().toString(), message);
    }
}