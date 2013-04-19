package com.network.networkmonitoragent.scnner;

import com.network.monitor.domain.Server;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author
 */
public class MainServerConnectionService {

    private int hubPort;
    private InetAddress serverAddres;
    private InetAddress broadcastAddress;
    private DatagramSocket registerSocket;
    private String fileListPort;
    private String searchPort;

    public MainServer findServer() throws IOException {
        MainServer mainServer = null;
        
        try {
            broadcastAddress = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException e) {
            System.out.println("Error parsing broadcast address\n");
            e.printStackTrace();
        }


        byte message[] = "RegisterMe".getBytes();
        DatagramPacket packet = new DatagramPacket(message, message.length, broadcastAddress, 4445);

        try {
            registerSocket = new DatagramSocket();
            registerSocket.send(packet);
            System.out.println("Sending broadcast packet");
        } catch (IOException e) {
            System.out.println("Error sending broadcast packet\n");
            e.printStackTrace();
        }

        // Waiting for the server to confirm

        message = new byte[100];
        DatagramPacket packetReceived = new DatagramPacket(message, message.length);
        System.out.println("Waiting for confirmation packet..." + registerSocket.getPort() + registerSocket.isConnected() + "\n");

        try {
            registerSocket.setSoTimeout(10000); // Timeout from server! 
        } catch (SocketException e1) {
        }

        try {
            registerSocket.receive(packetReceived);

        } catch (IOException e) {
            System.out.println("CLIENT: Error receiving broadcast packet\n");
            return null;
            //e.printStackTrace();
        }

        String messageReceived = "";
        System.out.println("Packet received from : " + packetReceived.getAddress().toString() + " on " + packetReceived.getPort());
        messageReceived = new String(message).trim();
        System.out.println(messageReceived + "\n");


        if (messageReceived.startsWith("RegisteredOK")) {
            serverAddres = packetReceived.getAddress();

            fileListPort = messageReceived.split(";")[1];
            System.out.println("#####Comm PORT: " + fileListPort);
            Integer port = Integer.parseInt(fileListPort);

            mainServer = new MainServer(port, serverAddres.getHostAddress());
            
            return mainServer;

           

        }

        return null;
    }
}
