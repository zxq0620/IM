package com.im.net.server;

public class ServerStart {

    public static void main(String[] args) {
        try {
            new Server(12345).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
