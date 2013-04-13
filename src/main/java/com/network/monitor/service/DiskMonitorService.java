/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.service;

import com.network.monitor.domain.DiskUsage;
import com.network.monitor.domain.DriveUsage;
import com.network.monitor.domain.GeneralInfo;
import com.network.monitor.domain.Server;
import com.network.monitor.domain.ServerInfo;
import com.network.monitor.util.FileUtil;
import com.network.monitor.util.ScriptFinder;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Ovi
 */
public class DiskMonitorService {

    ScriptExecutorService scriptExecutorService = new ScriptExecutorService();
    CSVParser cSVParser = new CSVParser();

    public DiskMonitorService() {
    }

    public List<Server> getDiskUsageForAllServers() {
        List<Server> servers = new ArrayList<Server>();

        File scriptForDiskUsage = ScriptFinder.getScriptFileForDiskUsage();
       // FileUtil.cleanCSVFiles(".");
        int exitValue = scriptExecutorService.executeScript(scriptForDiskUsage.getAbsolutePath());

        //todo: check for exit value
        List<File> fileList = FileUtil.getFilesFromPathThatStartWith(".", ".csv");

        for (File file : fileList) {
            List<List<Map<String, String>>> parsedValue;
            parsedValue = cSVParser.parseCSVFile(file.getAbsolutePath());

            Server server = getServerInfo(parsedValue);
            servers.add(server);
        }
        return servers;
    }

    private String getServerNameFromFileName(String name) {
        return name.split("disk_usage_")[1].split(".csv")[0];
    }

    private Server getServerInfo(List<List<Map<String, String>>> parsedValue) {
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
}
