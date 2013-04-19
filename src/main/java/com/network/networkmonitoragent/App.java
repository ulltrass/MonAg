package com.network.networkmonitoragent;

import com.network.networkmonitoragent.service.InfoSenderService;
import com.network.monitor.domain.MainServer;
import com.network.networkmonitoragent.service.MainServerConnectionService;
import com.network.networkmonitoragent.scnner.ScannerService;
import com.network.monitor.domain.Server;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

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
    private static final Logger LOGGER = Logger.getLogger(App.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        MainServerConnectionService mainServerConnectionService = new MainServerConnectionService();
        MainServer mainServer = mainServerConnectionService.findServer();

        InfoSenderService infoSenderService = null;

        if (mainServer != null) {
            ScannerService scannerService = new ScannerService();
            Server server = null;

            while (true) {
                LOGGER.info("Getting info");
                server = scannerService.getServerInfo();

                infoSenderService = new InfoSenderService(mainServer);
                infoSenderService.sendInfo(server);
                Thread.sleep(3000);
            }

        } else {
            LOGGER.info("Unable to connect to main server");
        }

    }

  
}
