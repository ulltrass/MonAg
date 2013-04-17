/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.domain;

import java.util.List;

/**
 *
 * @author opetridean
 */
public class Setting {

    String smsUrlWebStar;
    String smsUrlSingTel;
    String smsTestWebStarSenderName;
    String smsTestWebStarPhoneNumber;
    String smsTestWebStarTestMessage;
    String smsTestSingTelSenderName;
    String smsTestSingTelPhoneNumber;
    String smsTestSingTelTestMessage;
    String emailUser;
    String emailPassword;
    String monitoringType;
    List<EmailSettings> emailSettings;

    public Setting() {
    }

    public String getSmsTestWebStarSenderName() {
        return smsTestWebStarSenderName;
    }

    public void setSmsTestWebStarSenderName(String smsTestWebStarSenderName) {
        this.smsTestWebStarSenderName = smsTestWebStarSenderName;
    }

    public String getSmsTestWebStarPhoneNumber() {
        return smsTestWebStarPhoneNumber;
    }

    public void setSmsTestWebStarPhoneNumber(String smsTestWebStarPhoneNumber) {
        this.smsTestWebStarPhoneNumber = smsTestWebStarPhoneNumber;
    }

    public String getSmsTestWebStarTestMessage() {
        return smsTestWebStarTestMessage;
    }

    public void setSmsTestWebStarTestMessage(String smsTestWebStarTestMessage) {
        this.smsTestWebStarTestMessage = smsTestWebStarTestMessage;
    }

    public String getSmsTestSingTelSenderName() {
        return smsTestSingTelSenderName;
    }

    public void setSmsTestSingTelSenderName(String smsTestSingTelSenderName) {
        this.smsTestSingTelSenderName = smsTestSingTelSenderName;
    }

    public String getSmsTestSingTelPhoneNumber() {
        return smsTestSingTelPhoneNumber;
    }

    public void setSmsTestSingTelPhoneNumber(String smsTestSingTelPhoneNumber) {
        this.smsTestSingTelPhoneNumber = smsTestSingTelPhoneNumber;
    }

    public String getSmsTestSingTelTestMessage() {
        return smsTestSingTelTestMessage;
    }

    public void setSmsTestSingTelTestMessage(String smsTestSingTelTestMessage) {
        this.smsTestSingTelTestMessage = smsTestSingTelTestMessage;
    }

    public String getSmsUrlSingTel() {
        return smsUrlSingTel;
    }

    public void setSmsUrlSingTel(String smsUrlSingTel) {
        this.smsUrlSingTel = smsUrlSingTel;
    }
    

    public String getSmsUrlWebStar() {
        return smsUrlWebStar;
    }

    public void setSmsUrlWebStar(String smsUrlWebStar) {
        this.smsUrlWebStar = smsUrlWebStar;
    }

    public List<EmailSettings> getEmailSettings() {
        return emailSettings;
    }

    public void setEmailSettings(List<EmailSettings> emailSettings) {
        this.emailSettings = emailSettings;
    }

    public String getMonitoringType() {
        return monitoringType;
    }

    public void setMonitoringType(String monitoringType) {
        this.monitoringType = monitoringType;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }
}
