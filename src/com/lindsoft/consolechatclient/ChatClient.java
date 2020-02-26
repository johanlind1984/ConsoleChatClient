package com.lindsoft.consolechatclient;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {

    private String userName;

    public ChatClient() {

    }

    public void startClient() {
        try  {
            Socket socket = new Socket("localhost", 8888);
            System.out.println("Connected...");
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
