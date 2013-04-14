package com.network.monitor.service;

import com.network.monitor.domain.GeneralInfo;
import com.network.monitor.domain.Server;
import com.network.monitor.domain.ServerInfo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

public class RegisterServer implements Runnable {

    private DatagramSocket socket;
    private int infoPort;

    public RegisterServer(DatagramSocket ds, int infoPort) {
        socket = ds;
        this.infoPort = infoPort;

    }

    @Override
    public void run() {


        while (true) {
            System.out.println("RegisterServer astept conexiuni...");
            byte[] buffer = new byte[1024];
            byte[] bufferOut = new byte[1024];
            String remoteIp = "";

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                remoteIp = packet.getAddress().toString().split("/")[1];
            } catch (IOException e) {
                System.out.println("RegisterSerger: Error receiving datagram packet");
                e.printStackTrace();
            }
            InetAddress client = packet.getAddress();
            System.out.println("Register Server Am primit pachet de la: " + client.toString() + "pe portul" + packet.getPort());
            int client_port = packet.getPort();

            String message = new String(buffer).trim();

            if (message.startsWith("RegisterMe")) {

                String u = message;
                message = "RegisteredOK;" + infoPort + ";";
                bufferOut = message.getBytes();
                packet = new DatagramPacket(bufferOut, bufferOut.length, client, client_port);
                System.out.println("Send Hub confirmation to  " + message + " -- " + client + "pe portul " + client_port + "\n");

                try {
                    socket.send(packet);

                } catch (IOException e) {
                    System.out.println("ERROR SENDIG CONFIRMATION PACKAGE TO " + client);
                    e.printStackTrace();
                }

                if (u.startsWith("RegisterMe")) {
                    Server server = new Server();
                    ServerInfo serverInfo = new ServerInfo();
                    GeneralInfo generalInfo = new GeneralInfo();

                    generalInfo.setIpv4Address(client.getHostAddress());

                }
                
            }
            
            


        }

    }
}
