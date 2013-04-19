package com.network.networkmonitoragent.scnner;

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
 * @author
 */
public class InfoSenderService {

    MainServer mainServer;
    
    public InfoSenderService(MainServer mainServer) {
        this.mainServer = mainServer;
    }
    
     public void sendInfo(Server server) {
        try {
            System.out.println("Send info");
            Socket s = new Socket(mainServer.getIp(), mainServer.getPort());
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
