package com.mycompany.networkmonitoragent;

import com.mycompany.networkmonitoragent.scnner.InfoSenderService;
import com.mycompany.networkmonitoragent.scnner.MainServer;
import com.mycompany.networkmonitoragent.scnner.MainServerConnectionService;
import com.mycompany.networkmonitoragent.scnner.ScannerService;
import com.network.monitor.domain.Server;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * Hello world!
 *
 */
public class App {

    private static Sigar sigar = new Sigar();

    public static void main(String[] args) throws IOException, InterruptedException {

        MainServerConnectionService mainServerConnectionService = new MainServerConnectionService();
        MainServer mainServer = mainServerConnectionService.findServer();

        InfoSenderService infoSenderService = null;

        if (mainServer != null) {
            ScannerService scannerService = new ScannerService();
            Server server = null;

            while (true) {
                System.out.println("Getting info");
                server = scannerService.getServerInfo();

                infoSenderService = new InfoSenderService(mainServer);
                infoSenderService.sendInfo(server);
                Thread.sleep(3000);
            }

        } else {
            System.out.println("Unable to connect to main server");
        }

    }

    /* Method to get Informations about the CPU(s): */
//    public static void getInformationsAboutCPU() {
//        System.out.println("************************************");
//        System.out.println("*** Informations about the CPUs: ***");
//        System.out.println("************************************\n");
//
//        CpuInfo[] cpuinfo = null;
//        try {
//            cpuinfo = sigar.getCpuInfoList();
//        } catch (SigarException se) {
//            se.printStackTrace();
//        }
//
//
//
//        System.out.println("---------------------");
//        System.out.println("Sigar found " + cpuinfo.length + " CPU(s)!");
//        System.out.println("---------------------");
//
//        for (int i = 0; i < cpuinfo.length; i++) {
//            Map<String, String> map = cpuinfo[i].toMap();
//            System.out.println("CPU " + i + ": " + map);
//            System.out.println("My: " + map.get("Model"));
//        }
//
//
//
//
//
//
//        System.out.println("\n************************************\n");
//    }
}
