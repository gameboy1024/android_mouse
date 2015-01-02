package com.sunbotu.androidmouse.utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class is an async task to create the socket connection.
 * Created by Botu Sun on 11/5/14.
 */
public class SocketConnector extends AsyncTask<Void, Void, Boolean> {
    private String ip, port;

    private Socket client;
    private PrintWriter printwriter;

    public SocketConnector(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public void sendMessage(String message) {
        printwriter.println(message);
        printwriter.flush();
    }

    /**
     * Try to connect to remote server via socket.
     * @return True if the connection succeeded.
     */
    public boolean connect() {
        try {
            client = new Socket(this.ip, Integer.valueOf(this.port));
            printwriter = new PrintWriter(client.getOutputStream(), true);
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected Boolean doInBackground(Void ... voids) {
        return connect();
    }
}