/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.domain;

import java.util.List;

/**
 *
 * @author Ovi
 */
public class DiskUsage {
    
    List<DriveUsage> driveUsages;

    public List<DriveUsage> getDriveUsages() {
        return driveUsages;
    }

    public void setDriveUsages(List<DriveUsage> driveUsages) {
        this.driveUsages = driveUsages;
    }
    
    
    
}
