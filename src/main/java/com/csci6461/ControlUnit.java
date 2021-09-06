/*
 * This file implements the Control Unit (CU) class for the CSCI 6461 project.
 */
package com.csci6461;

import java.io.IOException;

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
     * Initialize static variable with size of main memory
     * NOTE: This should probably eventually come from some config file 
     *       that is loaded at execution
     */
    private static final int MEMORY_SIZE = 2048;
    
    /** Initialize timeout period in ms
     * NOTE: This should probably eventually come from some config file 
     *       that is loaded at execution
     */
    private static final long CLOCK_TIMEOUT = 1000;
    /**
     * Parameter to hold the Program Counter (PC) register
     */
    public Register pc;
    /**
     * Parameter to hold the computer's main memory
     * NOTE: Setting to private for now since I don't think memory needs to 
     *       be read directly outside the CPU but is always loaded to register 
     *       first. We may have to change this as we build out the sim.
     */
    private Memory mainMemory;
    /**
     * Parameter to hold system clock
     */
    private Clock systemClock;
    /**
     * Parameter to start/stop program execution
     */
    private boolean Continue = false;
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
        
        /**
         * Create main memory of appropriate size
         */
        try {
            mainMemory = new Memory(MEMORY_SIZE);
        } catch(IOException ioe) {
            System.out.println("Execption while creating computer memory...");
            ioe.printStackTrace();
        }
        
        /**
         * Create system clock and initialize to configured timeout
         */
        systemClock = new Clock(CLOCK_TIMEOUT);
    }
    /**
     * Method to implement LOAD function of simulated computer
     * NOTE: Right now this just saves a word into memory but we will probably 
     *       have to implement more logic to decode addresses/indexing later on
     * 
     * @param address Int with memory address into which data will be loaded
     * @param data Short with data word to be saved to memory
     */
    public void load(int address, short data) {
        try {
            mainMemory.write(address, data);
        } catch(IOException ioe) {
            System.out.println("Execption while writing to memory...");
            ioe.printStackTrace();
        }
    }
    
    /**
     * Method to trigger program execution
     * NOTE: This currently waits for clock cycles. We still need 
     *       to implement program load and execute logic.
     */
    public void run() throws InterruptedException {
        Continue = true;
        int ticks = 0;
        while (Continue) {
            
            systemClock.waitForNextTick();
            
            ticks++;
            if (ticks == 5) {
                Continue = false;
            }
        }
    }
}
