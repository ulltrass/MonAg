/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.controller;

import com.network.monitor.domain.Server;
import com.network.monitor.service.DiskMonitorService;
import java.util.List;

/**
 *
 * @author Ovi
 */
public class DiskMonitorController {
    
    DiskMonitorService diskMonitorService = new DiskMonitorService();
    
    
    public List<Server> getDiskUsageForAllServers(){
        return diskMonitorService.getDiskUsageForAllServers();
    }
    
}
