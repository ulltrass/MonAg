package com.network.monitor.domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author
 */
public class DiskUsage implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    List<DriveUsage> driveUsages;

    public List<DriveUsage> getDriveUsages() {
        return driveUsages;
    }

    public void setDriveUsages(List<DriveUsage> driveUsages) {
        this.driveUsages = driveUsages;
    }
    
    
    
}
