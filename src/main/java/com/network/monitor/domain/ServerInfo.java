package com.network.monitor.domain;

import java.io.Serializable;

/**
 *
 * @author
 */
public class ServerInfo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    String status;
    
    Double ping;
    
    Integer cpuUsage;
    
    Integer memoryUsage;
    
    DiskUsage diskUsage;
    
    GeneralInfo generalInfo;

    
    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }
    
    

    public DiskUsage getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(DiskUsage diskUsage) {
        this.diskUsage = diskUsage;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPing() {
        return ping;
    }

    public void setPing(Double ping) {
        this.ping = ping;
    }

    public Integer getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Integer cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Integer getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Integer memoryUsage) {
        this.memoryUsage = memoryUsage;
    }
    
    
    
    
}
