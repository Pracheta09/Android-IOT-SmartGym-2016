package com.demo.pracheta.mygym;

/**
 * Created by Pracheta on 11/23/2016.
 */

import android.annotation.SuppressLint;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Client extends Thread {
    //private AsyncTask<Void, Void, Void> async_cient;

    public static byte[] sendData = new byte[1024];
    static int flag = 0;
    static int SERVER_PORT = 5454;

    @SuppressLint("NewApi")
    static public void startSending(final String input) {

        new Thread(new Runnable() {
            public void run() {
                try {

                    // send message to Pi
                    InetAddress serverAddr = InetAddress.getByName("172.20.10.9");
                    DatagramSocket clientSocket = new DatagramSocket();
                    byte[] sendData = new byte[1024];


                    sendData = input.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddr, SERVER_PORT);
                    clientSocket.send(sendPacket);
                    flag=1;
                    // get reply back from Pi
//                    byte[] receiveData1 = new byte[1024];
//                    DatagramPacket receivePacket = new DatagramPacket(receiveData1, receiveData1.length);
//                    clientSocket.receive(receivePacket);
                    clientSocket.setReuseAddress(true);
                    clientSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}

