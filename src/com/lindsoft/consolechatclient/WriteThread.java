package com.lindsoft.consolechatclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {

    private Socket socket;
    private ChatClient chatClient;
    private PrintWriter writer;
    private Scanner scanner;

    public WriteThread(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.chatClient = chatClient;
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("Enter your Nickname: ");
        String nickName = scanner.nextLine();
        chatClient.setUserName(nickName);

        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(nickName);
            String message;

            do {

                message = scanner.nextLine();
                String messageToServer = "[" + nickName + "]: " + message;
                writer.println(message);

                if(message.equals("!quit")) {
                    break;
                }

            } while (true);

            System.out.println("Leaving chat...");

        } catch (IOException e) {
            System.out.println("Something went wrong opening socket for writing...");
            e.printStackTrace();
            e.getMessage();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }

    }
}
