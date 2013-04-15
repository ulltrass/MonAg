/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.networkmonitoragent.service;

import com.network.monitor.domain.Server;

/**
 *
 * @author opetridean
 */
public class CPUMonitoringService {

    public void setInfo(Server server) {
         /* Method to get Informations about the CPU(s): */
        System.out.println("************************************");
        System.out.println("*** Informations about the CPUs: ***");
        System.out.println("************************************\n");

        CpuInfo[] cpuinfo = null;
        try {
            cpuinfo = sigar.getCpuInfoList();
        } catch (SigarException se) {
            se.printStackTrace();
        }



        System.out.println("---------------------");
        System.out.println("Sigar found " + cpuinfo.length + " CPU(s)!");
        System.out.println("---------------------");

        for (int i = 0; i < cpuinfo.length; i++) {
            Map<String, String> map = cpuinfo[i].toMap();
            System.out.println("CPU " + i + ": " + map);
            System.out.println("My: " + map.get("Model"));
        }


        System.out.println("\n************************************\n");
    }
    
}
