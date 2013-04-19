package com.network.monitor.domain;

/**
 *
 * @author
 */
public class MainServer {
    
    Integer port;
    
    String ip;

    public MainServer(Integer port, String ip) {
        this.port = port;
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
    
}
