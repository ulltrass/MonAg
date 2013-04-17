/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.service;

import com.network.monitor.domain.Contact;
import com.network.monitor.util.FileUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ovi
 */
public class ContactService {

    public void saveContacts(List<Contact> contacts) {
        List<String> contactsAsCSV = new ArrayList<String>();

        for (Contact contact : contacts) {
            contactsAsCSV.add(contact.getName() + ", " + contact.getEmail() + ", "
                    + contact.getSmsNumber());
        }

        FileUtil.saveContactsFile(contactsAsCSV);
    }

    public List<Contact> getContacts() {
        List<String> contactsAsCSV = FileUtil.readContactsFile();
        List<Contact> contacts = new ArrayList<Contact>();

        for (String contact : contactsAsCSV) {
            String[] contactDetails = contact.split(",");
            contacts.add(new Contact(contactDetails[0].trim(), contactDetails[1].trim(),
                    contactDetails[2].trim()));
        }
        return contacts;
    }
    
    public List<Contact> getContactsWithSMSNumberSet() {
        List<String> contactsAsCSV = FileUtil.readContactsFile();
        List<Contact> contacts = new ArrayList<Contact>();

        for (String contact : contactsAsCSV) {
            String[] contactDetails = contact.split(",");
            if (contactDetails[2] != null && !"".equals(contactDetails[2]))
            contacts.add(new Contact(contactDetails[0].trim(), contactDetails[1].trim(),
                    contactDetails[2].trim()));
        }
        return contacts;
    }
    
     public List<Contact> getContactsWithEmailAddressSet() {
        List<String> contactsAsCSV = FileUtil.readContactsFile();
        List<Contact> contacts = new ArrayList<Contact>();

        for (String contact : contactsAsCSV) {
            String[] contactDetails = contact.split(",");
            if (contactDetails[1] != null && !"".equals(contactDetails[1]))
            contacts.add(new Contact(contactDetails[0].trim(), contactDetails[1].trim(),
                    contactDetails[2].trim()));
        }
        return contacts;
    }

    
}
