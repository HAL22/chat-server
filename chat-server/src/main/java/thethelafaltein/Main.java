package thethelafaltein;

import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        ChatServer server = new ChatServer(12345);
        new Thread(server).start();
    }
}