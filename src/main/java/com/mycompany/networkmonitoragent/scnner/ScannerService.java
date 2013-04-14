/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.networkmonitoragent.scnner;

import com.network.monitor.domain.Server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ovi
 */
public class ScannerService {

    public ScannerService() {
    }

    public void sendInfo() {
        try {
            Server server = new Server();
            server.setServerIp("192");

            Socket s = new Socket("localhost", 4446);
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(server);
            oos.close();
            os.close();
            s.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ScannerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ScannerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
