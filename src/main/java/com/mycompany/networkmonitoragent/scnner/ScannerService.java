/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.networkmonitoragent.scnner;

import com.mycompany.networkmonitoragent.service.CPUMonitoringService;
import com.mycompany.networkmonitoragent.service.MemoryMonitoringService;
import com.mycompany.networkmonitoragent.util.CSVParser;
import com.mycompany.networkmonitoragent.service.ScriptExecutorService;
import com.mycompany.networkmonitoragent.util.FileUtil;
import com.mycompany.networkmonitoragent.util.ScriptFinder;
import com.network.monitor.domain.DiskUsage;
import com.network.monitor.domain.DriveUsage;
import com.network.monitor.domain.GeneralInfo;
import com.network.monitor.domain.Server;
import com.network.monitor.domain.ServerInfo;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ovi
 */
public class ScannerService {

    ScriptExecutorService scriptExecutorService = new ScriptExecutorService();
    CSVParser cSVParser = new CSVParser();
    MemoryMonitoringService memoryMonitoringService = new MemoryMonitoringService();
    CPUMonitoringService cpuMonitoringService = new CPUMonitoringService();

    public ScannerService() {
    }

    public Server getServerInfo() {
        Server server = new Server();
        List<Server> servers = new ArrayList<Server>();

        File scriptForDiskUsage = ScriptFinder.getScriptFileForDiskUsage();
        FileUtil.cleanCSVFiles(".");
        int exitValue = scriptExecutorService.executeScript(scriptForDiskUsage.getAbsolutePath());
        List<File> fileList = FileUtil.getFilesFromPathThatStartWith(".", ".csv");

        File file = fileList.get(0);
        List<List<Map<String, String>>> parsedValue;
        parsedValue = cSVParser.parseCSVFile(file.getAbsolutePath());

        server = getServerMainInfo(parsedValue);
        
        memoryMonitoringService.setInfo(server);
        cpuMonitoringService.setInfo(server);
        
        
        
        servers.add(server);
        return server;
    }

    private Server getServerMainInfo(List<List<Map<String, String>>> parsedValue) {
        Server server = new Server();
        DiskUsage diskUsage = getServerDiskUsageInfo(parsedValue);
        GeneralInfo generalInfo = getServerGeneralInfo(parsedValue);

        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setDiskUsage(diskUsage);
        serverInfo.setGeneralInfo(generalInfo);
        if (generalInfo.getComputerName() != null && !generalInfo.getComputerName().equals("")) {
            server.setServerName(generalInfo.getComputerName());
        } else {
            server.setServerName("Unknown");
        }
        server.setServerInfo(serverInfo);

        return server;
    }

    private GeneralInfo getServerGeneralInfo(List<List<Map<String, String>>> parsedValue) {
        GeneralInfo generalInfo = new GeneralInfo();
        for (List<Map<String, String>> partitionList : parsedValue) {

            for (Map<String, String> property : partitionList) {
                if (property.containsKey("User Name")) {
                    generalInfo.setUserName(property.get("User Name"));
                }
                if (property.containsKey("Domain Name")) {
                    generalInfo.setDomainName(property.get("Domain Name"));
                }
                if (property.containsKey("OS Name")) {
                    generalInfo.setOsName(property.get("OS Name"));
                }
                if (property.containsKey("OS Version")) {
                    generalInfo.setOsVersion(property.get("OS Version"));
                }
                if (property.containsKey("Computer Name")) {
                    generalInfo.setComputerName(property.get("Computer Name"));
                }
                if (property.containsKey("Model")) {
                    generalInfo.setModel(property.get("Model"));
                }
                if (property.containsKey("CPU")) {
                    generalInfo.setCpu(property.get("CPU"));
                }
                if (property.containsKey("Serial Number / Service Tag")) {
                    generalInfo.setSerialNumber(property.get("Serial Number / Service Tag"));
                }
                if (property.containsKey("RAM Capacity (MB)")) {
                    generalInfo.setRamCapacity(property.get("RAM Capacity (MB)"));
                }
                if (property.containsKey("CD\\DVD Drive")) {
                    generalInfo.setDvdDrive(property.get("CD\\DVD Drive"));
                }
                if (property.containsKey("IPV4")) {
                    generalInfo.setIpv4Address(property.get("IPV4"));
                }
                if (property.containsKey("MAC Address")) {
                    generalInfo.setMacAddress(property.get("MAC Address"));
                }
            }

        }
        return generalInfo;
    }

    private DiskUsage getServerDiskUsageInfo(List<List<Map<String, String>>> parsedValue) {

        DiskUsage diskUsage = new DiskUsage();
        List<DriveUsage> driverUsages = new ArrayList<DriveUsage>();

        for (List<Map<String, String>> partitionList : parsedValue) {
            DriveUsage driveUsage = new DriveUsage();

            for (Map<String, String> property : partitionList) {
                if (property.containsKey("Drive Letter")) {
                    driveUsage.setDriveLetter(property.get("Drive Letter"));
                }
                if (property.containsKey("Capacity Space (GB)")) {
                    driveUsage.setCapacity(property.get("Capacity Space (GB)"));
                }
                if (property.containsKey("Free Space (GB)")) {
                    driveUsage.setFreeSpace(property.get("Free Space (GB)"));
                }
                if (property.containsKey("File System")) {
                    driveUsage.setFileSystem(property.get("File System"));
                }
            }
            if (driveUsage.getDriveLetter() != null || driveUsage.getCapacity() != null
                    || driveUsage.getFileSystem() != null || driveUsage.getFreeSpace() != null) {
                driverUsages.add(driveUsage);
            }
        }

        diskUsage.setDriveUsages(driverUsages);


        return diskUsage;
    }
}
