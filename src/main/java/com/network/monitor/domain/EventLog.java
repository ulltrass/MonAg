/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.domain;

import java.sql.Timestamp;

/**
 *
 * @author opetridean
 */
public class EventLog {

    Timestamp eventTime;
    
    String serverName;
    
    String message;
    
    EventType eventType;
    
    NotificationType notificationType;

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
    
    
    
}
