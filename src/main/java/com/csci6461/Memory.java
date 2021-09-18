/**
 * This file implements the main memory for the CSCI 6461 simple computer
 * simulation.
 */
package com.csci6461;

import java.io.IOException;

/**
 * The Memory class is a container for an array of 16-bit words (E.g. shorts) 
 * of size equal to the required memory size (2048 or 4096 words according to 
 * the CSCI 6461 project spec. It also implements methods to simulate the various
 * memory functions such as read and write and a simple addressing scheme where 
 * the address corresponds to the slot in the data array of a single word.
 * 
 * @author imanuelportalatin
 */
public class Memory {
   /**
    * Member variable to hold data array, which will be initialized by the 
    * constructor
    */ 
    private short[] data;
    /**
     * Total size of the memory array in 16-bit words
     */
    private int size;
    
    /**
     * The memory class constructor creates a new data storage array of 
     * the specified size and assigns it to the data private variable. The 
     * size if also saved for reference.
     * 
     * @param s Size of memory array in number of 16-bit words
     * 
     * @throws IOException If an invalid memory size is specified
     */
    public Memory(int s) throws IOException {
        if (s <= 0) {
            String error = String.format(
               "Invalid memory size: %d; Memory size must greater than zero.",
                s);
            throw new IOException(error);
        }
        
        /* Save size and initalize data to new array of specified size */
        size = s;
        data = new short[size];
    }
    
    /**
     * This method writes a word into main memory at the specified address
     * 
     * @param address An int with the address where data will be stored
     * @param word A short with the 16-bit word of data to write into memory
     * 
     * @throws IOException If an invalid address is specified
     */
    public void write(int address, short word) throws IOException {
        if (address < 0 || address >= size) {
            String error = String.format(
               "Invalid memory address: %d; Addresses must be between 0 and %d.",
                address, size);
            throw new IOException(error);
        }
        data[address] = word;
        /* Save input word into slot corresponding to the specified address */
        System.out.printf("[Memory::write] Writing value %d to memory address %d\n", 
                word, address);
    }
    
    /**
     * This method reads the specified location in memory 
     * 
     * @param address An int with the address in memory to read
     * 
     * @return A 16-bit word with the data at the specified location in memory
     * 
     * @throws IOException If an invalid address is received
     */
    public short read(int address) throws IOException {
        if (address < 0 || address >= size) {
            String error = String.format(
               "Invalid memory address: %d; Addresses must be between 0 and %d.",
                address, size);
            throw new IOException(error);
        }
        /* Return data at specified location */
        return(data[address]);
    }

    /**
     * A method for printing the populated memory dump to the console
     */
    public void printMemory() {
        // |-------------|-------------|
        // |   ADDRESS   |    VALUE    |
        // |-------------|-------------|
        System.out.println("\n\n         MEMORY DUMP         ");
        System.out.println("|-------------|-------------|");
        System.out.println("|   ADDRESS   |    VALUE    |");
        System.out.println("|-------------|-------------|");

        for (int i=0; i<data.length;i++){
            if (data[i]!=0){
                String pos = "0x"+formatHex(Integer.toHexString(i & 0xffff)).toUpperCase();
                String val = "0x"+formatHex(Integer.toHexString(data[i] & 0xffff)).toUpperCase();
                System.out.println("|   "+pos+"    |   "+val+"    |");
            }
        }
        System.out.println("|-------------|-------------|");

    }

    /**
     * Formats the string to look like a standard 2 byte hex
     * @param s The unformatted string
     * @return Returns the formatted string
     */
    private String formatHex(String s) {
        String newS = s;
        if (s.length() < 4) {
            for (int i = 0; i < 4 - s.length(); i++){
                newS = "0"+newS;
            }
        }
        return  newS;
    }

}
