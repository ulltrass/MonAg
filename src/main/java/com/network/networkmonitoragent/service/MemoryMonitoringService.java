package com.network.networkmonitoragent.service;

import com.network.monitor.domain.Server;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author
 */
public class MemoryMonitoringService {
    Sigar sigar = new Sigar();

    public void setInfo(Server server) {
        try {
            server.getServerInfo().setMemoryUsage((int)sigar.getMem().getUsedPercent());
        } catch (SigarException ex) {
            Logger.getLogger(MemoryMonitoringService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
