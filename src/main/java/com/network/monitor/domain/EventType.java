/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.domain;

/**
 *
 * @author opetridean
 */
public enum EventType{

    INFO("Info"),
    WARNING("Warning"),
    CRITICAL("Critical");
    
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
  
     private EventType(String message) {
        this.message = message;
    }
    
}
