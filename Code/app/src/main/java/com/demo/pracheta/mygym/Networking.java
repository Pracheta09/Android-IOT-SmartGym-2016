package com.demo.pracheta.mygym;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Pracheta on 11/23/2016.
 */
public class Networking extends Thread { //UDP_SERVER
    static String receivedstring="Nodata";
    static char receivedstring1 ='0',abc='0';
    static String xyz="d";
    static void startNetwork() {
        new Thread(new Runnable() {
            public void run() {

                byte[] receivedata = new byte[5];
                DatagramPacket recv_packet = new DatagramPacket(receivedata, receivedata.length);
                try {
                    //Thread.sleep(10000,2);
                    DatagramSocket clientsocket = new DatagramSocket(5005);



                        System.out.println("recieving");                        Log.d("UDP", "S: Receiving...");
                        clientsocket.receive(recv_packet);

                        receivedstring = new String(recv_packet.getData());
                        receivedstring1 = receivedstring.charAt(0);
                        abc  = receivedstring.charAt(1);
                    xyz=receivedstring1+abc+"";
                        Log.d("UDP", " Received String: " + receivedstring);
                        InetAddress ipaddress = recv_packet.getAddress();
                        int port = recv_packet.getPort();
                        clientsocket.setReuseAddress(true);
                        //clientsocket.bind(recv_packet.getSocketAddress());
                        Log.d("UDP", "IPAddress : " + ipaddress.toString());
                        Log.d("UDP", "Port : " + Integer.toString(port));




                } catch (SocketException e) {
                    Log.e("UDP", "Socket Error", e);
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("UDP", "IO Error", e);
                } //catch (InterruptedException e) {
                    //e.printStackTrace();
                //}
            }
        }).start();

    }

}
