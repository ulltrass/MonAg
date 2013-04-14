package com.network.monitor;

import com.network.monitor.service.ClientRegistrationService;
import com.network.monitor.service.RegisterServer;
import com.network.monitor.view.MainForm;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ClientRegistrationService clientRegistrationService = new ClientRegistrationService();
        
        Thread t = new Thread(clientRegistrationService);
        t.start();
        
        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
    }
}
