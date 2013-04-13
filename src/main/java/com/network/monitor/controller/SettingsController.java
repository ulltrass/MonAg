/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.controller;

import com.network.monitor.domain.EmailSettings;
import com.network.monitor.domain.Setting;
import com.network.monitor.service.AppSettingsService;

/**
 *
 * @author opetridean
 */
public class SettingsController {

    AppSettingsService appSettingsService = new AppSettingsService();
    
    public void saveSettings(Setting setting) {
        appSettingsService.saveSettings(setting);
    }
     
    public void addEmailSetting(EmailSettings setting) {
        appSettingsService.addEmailSettings(setting);
    }
      public void updateEmailSetting(EmailSettings setting, String oldConfigName) {
        appSettingsService.updateEmailSettings(setting, oldConfigName);
    }
    
     public Setting getSettings() {
        return appSettingsService.getSettings();
    }

    public void deleteEmailSetting(String emailConfigName) {
        appSettingsService.deleteEmailSettings(emailConfigName);
    }
    
}
