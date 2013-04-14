/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.service;

import com.network.monitor.domain.Server;
import com.network.monitor.view.MainForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ovi
 */
public class UserMonitoringService {

    Map<String, Server> servers = new HashMap<String, Server>();
    MainForm mainForm = null;

    public UserMonitoringService(MainForm mainForm) {
        this.mainForm = mainForm;
    }
    

    public void addNewServer(Server server) {
        String ipAddress = server.getServerInfo().getGeneralInfo().getIpv4Address();
        Server mapServer = servers.get(ipAddress);
        if (mapServer == null){
            servers.put(ipAddress, server);
        }
        mainForm.getDefaultListModel().clear();
        
        List<Server> serverList = (List<Server>) servers.values();
        
        for (Server serv : serverList) {
            mainForm.getDefaultListModel().addElement(serv.getServerName());
        }
        
    }
}
