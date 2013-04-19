package com.network.monitor.domain;

import java.io.Serializable;

/**
 *
 * @author
 */
public class Server implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String serverName;
    private String serverIp;
    private ServerInfo serverInfo;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }
    
    
    
    
    
}
