/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.network.monitor.service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ovi
 */
public class ScriptExecutorService {
    
    public int executeScript(String path){
        int exitValue = 1;
        try {
            Process process = Runtime.getRuntime().exec("wscript  " +path);
            process.waitFor();
            exitValue = process.exitValue();
        } catch (IOException ex) {
            Logger.getLogger("Unable to execute script <" + path + ">");
            Logger.getLogger("Exitvalue for executing script <" + exitValue + ">");
            Logger.getLogger(ScriptExecutorService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ScriptExecutorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exitValue;
    }
}
