/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.controller;

import com.network.monitor.domain.Contact;
import com.network.monitor.domain.EmailSettings;
import com.network.monitor.domain.Server;
import com.network.monitor.domain.Setting;
import com.network.monitor.service.ContactService;
import com.network.monitor.service.DiskMonitorService;
import com.network.monitor.service.EmailNotificationService;
import com.network.monitor.service.SMSNotificationService;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Ovi
 */
public class ContactController {

    ContactService contactService = new ContactService();
    SMSNotificationService smsNotificationService = new SMSNotificationService();
    EmailNotificationService emailNotificationService = new EmailNotificationService();
    DiskMonitorService diskMonitorService = new DiskMonitorService();

    public void saveContacts(List<Contact> contacts) {
        contactService.saveContacts(contacts);
    }

    public List<Contact> getContacts() {
        return contactService.getContacts();
    }

    public List<Contact> getContactsWithSMSNumberSet() {
        return contactService.getContactsWithSMSNumberSet();
    }

    public List<Contact> getContactsWithEmailAddressSet() {
        return contactService.getContactsWithEmailAddressSet();
    }

    public void sendSmsToContacts(Setting setting, List<Contact> contacts) {
        List<Server> servers = diskMonitorService.getDiskUsageForAllServers();
        smsNotificationService.sendSMSToContacts(setting, contacts);
    }

    public void sendEmailToContacts(List<EmailSettings> emailSettings, List<Contact> contacts) {
        List<Server> servers = diskMonitorService.getDiskUsageForAllServers();
        boolean success = false;
        for (EmailSettings emailSetting : emailSettings) {
            if (emailSetting.isDefaultEmail()) {
                String subject = "Test Subject";
                String message = "Test alert message";
                try {
                    emailNotificationService.sendEmail(emailSetting.getServerHost(),
                            emailSetting.getServerPort(), emailSetting.getUserName(),
                            emailSetting.getPassword(), subject, message, contacts);
                    success = true;
                    break;
                } catch (Exception e) {
                    System.out.println("Error sending email");
                    success = false;
                }
            }
        }

        if (!success) {
            for (EmailSettings emailSetting : emailSettings) {
                if (!success) {
                    String subject = "Test Subject";
                    String message = "Test alert message";
                    try {
                        emailNotificationService.sendEmail(emailSetting.getServerHost(),
                                emailSetting.getServerPort(), emailSetting.getUserName(),
                                emailSetting.getPassword(), subject, message, contacts);
                        success = true;
                        break;
                    } catch (Exception e) {
                        System.out.println("Error sending email");
                        success = false;
                    }
                }
            }
        }

    }
}
