package com.lindsoft.consolechatclient;

import java.io.*;
import java.net.Socket;

public class ReadThread extends Thread {

    private Socket socket;
    private ChatClient chatClient;
    private BufferedReader reader;

    public ReadThread(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.chatClient  = chatClient;

        try {
           reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Something went wrong opening a socket to to readthread");
            e.printStackTrace();
            e.getMessage();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = reader.readLine();
                System.out.println("\n" + message);

                if (chatClient.getUserName() != null) {
                    System.out.print("[" + chatClient.getUserName() + "]: ");
                }

            } catch (IOException e) {
                System.out.println("Something went wrong when trying to read a line");
                e.printStackTrace();
                e.getMessage();
                break;
            }
        }
    }
}
