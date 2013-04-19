/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.networkmonitoragent.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Ovi
 */
public class EchoService implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(EchoService.class);
    DatagramSocket checkSocket;
    private static final int LISTEN_PORT = 5555;

    public EchoService() {
        try {
            this.checkSocket = new DatagramSocket(LISTEN_PORT);
        } catch (SocketException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    public void run() {
        while (true) {
            try {
                
                byte[] bufferOut = new byte[1024];
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                LOGGER.info("BEFORE RECEIVE on port: " + checkSocket.getPort());
                checkSocket.receive(packet);

                InetAddress client = packet.getAddress();
                String message = new String(buffer).trim();
                
                LOGGER.info("*********************************************");
                LOGGER.info("***Echo Received package from: " + client.toString() + " on port: " + packet.getPort());
                LOGGER.info("Message is: " + message);
                LOGGER.info("*********************************************");
                int client_port = packet.getPort();

                if (message.startsWith("Check")) {

                    String u = message;
                    message = "Ok";
                    bufferOut = message.getBytes();
                    packet = new DatagramPacket(bufferOut, bufferOut.length, client, 4448);
                    LOGGER.info("Send OK confirmation to  " + message + " -- " + client + " using port " + client_port + "\n");

                    try {
                        checkSocket.send(packet);

                    } catch (IOException e) {
                        LOGGER.error("ERROR sending OK  PACKAGE TO " + client);
                        LOGGER.error(e.getMessage(), e);
                    }
                }

            } catch (UnknownHostException ex) {
                LOGGER.error(ex.getMessage(), ex);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(EchoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
