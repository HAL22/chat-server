package thethelafaltein;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer implements Runnable {
    private final int PORT;
    private Set<ClientHandler> clientHandlers = new HashSet<>();

    public ChatServer(int port) {
        this.PORT = port;
    }

    public void run() {
        try (ServerSocket listener = new ServerSocket(PORT)) {
            System.out.printf("Chat Server started on %d%n", PORT);

            while (true) {
                Socket socket = listener.accept();
                System.out.printf("%s joined the chat session%n", socket.getInetAddress().toString());

                ClientHandler handler = new ClientHandler(socket,this::broadcastMessage,this.clientHandlers);
                clientHandlers.add(handler);
                handler.start();
            }
        } catch (IOException e) {
            System.err.println("Error Starting Chat Server: " + e.getMessage());
        }
    }

    private void broadcastMessage(String message) {
        for (ClientHandler handler : clientHandlers) {
            handler.sendMessage(message);
        }
    }
}