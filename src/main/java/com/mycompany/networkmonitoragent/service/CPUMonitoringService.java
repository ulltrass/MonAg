/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.networkmonitoragent.service;

import com.network.monitor.domain.Server;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author opetridean
 */
public class CPUMonitoringService {
    
    Sigar sigar = new Sigar();
    
    public void setInfo(Server server) {
        CpuPerc perc;
        try {
            perc = sigar.getCpuPerc();
            server.getServerInfo().setCpuUsage((int) (perc.getCombined() * 100));
        } catch (SigarException ex) {
            Logger.getLogger(CPUMonitoringService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
