/*
 * This file implements the Control Unit (CU) class for the CSCI 6461 project.
 */
package com.csci6461;

/**
 * Acts as simulated Control Unit (CU) for simple CSCI 6461 simulated computer.
 * It implements the main interface between the GUI and the computer for 
 * setting and getting registers, etc. but will also coordinate all the 
 * simulated function of the computer.
 * 
 * @author imanuelportalatin
 */
public class ControlUnit {
    /**
     * Parameter to hold the Program Counter (PC) register
     */
    public Register pc;
    /**
     * Control Unit constructor will instantiate all registers and load 
     * the ROM program
     */
    public ControlUnit() {
        System.out.println("Initializing control unit...");
        
        /**
         * Create Program Counter (PC) register
         */
        pc = new Register("PC",12);
    }
}
