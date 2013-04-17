/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.networkmonitoragent;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author Ovi
 */
public class Test {

    public static void main(String[] args) throws SigarException {
        System.out.println(String.format("Usage is %d%%", 95));
    }
}
