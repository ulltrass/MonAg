/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.service;

import com.network.monitor.domain.Server;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ovi
 */
class MonitoringInfoServer implements Runnable {

    ServerSocket socket;

    public MonitoringInfoServer(ServerSocket ds) {
        socket = ds;

    }

    @Override
    public void run() {


        while (true) {
            try {
                System.out.println("InfoServer astept conexiuni...");

                Socket s = socket.accept();
                InputStream is = s.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                Server server = (Server) ois.readObject();

                is.close();
                s.close();
                
                System.out.println("Received object: " + server.getServerIp());

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }
    }
}
