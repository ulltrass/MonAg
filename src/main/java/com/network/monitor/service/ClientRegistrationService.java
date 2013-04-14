/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.service;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ovi
 */
public class ClientRegistrationService implements Runnable {

    RegisterServer registerServer;
    DatagramSocket registerServSocket;
    ServerSocket monitoringServSocket;
    MonitoringInfoServer monitoringInfoServer;

    @Override
    public void run() {
        try {
            registerServSocket = new DatagramSocket(4445);
            monitoringServSocket = new ServerSocket(4446);
        } catch (SocketException ex) {
            Logger.getLogger(ClientRegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientRegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        registerServer = new RegisterServer(registerServSocket, 4446);
        Thread registerThread = new Thread(registerServer);
        registerThread.start();

        monitoringInfoServer = new MonitoringInfoServer(monitoringServSocket);
        Thread monitoringThread = new Thread(monitoringInfoServer);
        monitoringThread.start();

    }
}
