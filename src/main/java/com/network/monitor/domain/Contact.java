/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.domain;

/**
 *
 * @author opetridean
 */
public class Contact {

    String name;
    
    String email;
    
    String smsNumber;

    public Contact(String name, String email, String smsNumber) {
        this.name = name;
        this.email = email;
        this.smsNumber = smsNumber;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(String smsNumber) {
        this.smsNumber = smsNumber;
    }

    @Override
    public String toString() {
        return "Contact{" + "name=" + name + ", email=" + email + ", smsNumber=" + smsNumber + '}';
    }
    
    
    
}
