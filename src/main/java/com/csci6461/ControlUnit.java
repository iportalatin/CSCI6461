/*
 * This file implements the Control Unit (CU) class for the CSCI 6461 project.
 */
package com.csci6461;

import java.io.IOException;
import java.nio.ByteBuffer;

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
     * Initialize static configuration variables
     * NOTE: This should probably eventually come from some config file
     * that is loaded at execution
     */
    private static final int MEMORY_SIZE = 2048;     /* Size of main memory */
    private static final long CLOCK_TIMEOUT = 1000;  /* Clock timeout period */
    private static final int NUMBER_OF_GPR = 4;      /* Number of general purpose registers */
    private static final int NUMBER_OF_IXR = 3;      /* Number of general purpose registers */

    /**
     * Parameter to hold the Program Counter (PC) register
     */
    public Register pc;

    /**
     * Parameter to hold the General Purpose Registers (GPR)
     */
    public Register[] gpr;

    /**
     * Parameter to hold the IX Registers (IXR)
     */
    public Register[] ixr;

    /**
     * Parameter to hold the Memory Address Register (MAR)
     */
    public Register mar;

    /**
     * Parameter to hold the Memory Buffer Register (MBR)
     */
    public Register mbr;

    /**
     * Parameter to hold the computer's main memory
     * NOTE: Setting to private for now since I don't think memory needs to 
     *       be read directly outside the CPU but is always loaded to register 
     *       first. We may have to change this as we build out the sim.
     */
    private Memory mainMemory;

    /**
     * Parameter to hold the computer's instruction decoder
     */
    private InstructionDecoder instructionDecoder;

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
         * Create appropriate number of General Purpose Registers (GPR)
         */
        gpr = new Register[NUMBER_OF_GPR];
        for (int i = 0; i < NUMBER_OF_GPR; i++) {
            String name = String.format("GPR%d", i);
            gpr[i] = new Register(name, 16);
        }

        /**
         * Create appropriate number of IX Registers (IXR)
         */
        ixr = new Register[NUMBER_OF_IXR];
        for (int i = 0; i < NUMBER_OF_IXR; i++) {
            String name = String.format("IXR%d", i);
            ixr[i] = new Register(name, 16);
        }

        /**
         * Create Memory Address Register (MAR)
         */
        mar = new Register("MAR",12);

        /**
         * Create Memory Buffer Register (MBR)
         */
        mbr = new Register("MBR",16);
        
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
         * Create instruction decoder
         */
        instructionDecoder = new InstructionDecoder();

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
            System.out.println("Exception while writing to memory...");
            ioe.printStackTrace();
        }
    }

    /**
     * Method to process a TRAP instruction
     *
     * @param instruction An object implementing the Instruction abstract class for Miscellaneous instructions
     */
    public void processTrap(Instruction instruction) {
        /* Get trap code from instruction */
        int[] args = instruction.getArguments();
        System.out.printf("[ControlUnit::processTrap] Arguments for trap code instruction: %d\n", args[0]);

        /* Per instructions, trap logic for Part 1 only fetches memory location 1 and saves it PC  */
        /* NOTE: Memory location 1 should contain the address of memory location 6, which should have a HALT */
        try {
            short faultAddress = mainMemory.read(1);

            /* Convert word read from memory to byte array */
            ByteBuffer bytes = ByteBuffer.allocate(2).putShort(faultAddress);
            System.out.printf("[ControlUnit::processTrap] Loaded machine fault location: %s %s\n",
                    Integer.toBinaryString(bytes.array()[0]),
                    Integer.toBinaryString(bytes.array()[1]));

            /* Load fault address to PC register so we will go to trap routine on next cycle */
            pc.load(bytes.array());
            System.out.printf("[ControlUnit::processTrap] Loaded fault address to PC: %s %s\n",
                    Integer.toBinaryString(pc.read()[0]),
                    Integer.toBinaryString(pc.read()[1]));

        } catch (IOException ioe) {
            System.out.println("Exception while reading fault address from memory...");
            ioe.printStackTrace();
        }
    }
    
    /**
     * Method to trigger program execution
     * NOTE: This currently waits for clock cycles. We still need 
     *       to implement program load and execute logic.
     * @throws InterruptedException ADD HERE
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

    /**
     * Method to execute single step by getting the next instruction in
     * memory, decoding it and executing it
     */
    public void singleStep() throws IOException {
        /* Get next instruction address from PC and convert to int */
        int iPC = binaryToInt(pc.read());
        System.out.printf("[ControlUnit::singleStep] Next instruction address is %d\n", iPC);

        /* Get instruction at address indicated by PC */
        short instruction = mainMemory.read(iPC);
        System.out.printf("[ControlUnit::singleStep] Have next instruction: %s\n",
                Integer.toBinaryString((int)(instruction & 0xffff)));

        /* Decode the instruction */
        Instruction decodedInstruction = instructionDecoder.decode(instruction);

        /* If decoder return null, something went wrong */
        if (decodedInstruction == null) {
            /* Invalid Instruction; throw exception... */
            String error = String.format("Opcode for instruction %s is invalid!",
                    Integer.toBinaryString((int)(instruction & 0xffff)));
            throw new IOException(error);
        }

        /* Process instruction according to translated Opcode */
        System.out.printf("[ControlUnit::singleStep] Processing instruction: %s\n", decodedInstruction.getName());

        switch (decodedInstruction.getName()) {
            case "HLT":
                System.out.println("[ControlUnit::singleStep] Processing Halt instruction...\n");
                break;
            case "TRAP":
                System.out.println("[ControlUnit::singleStep] Processing Trap instruction...\n");
                processTrap(decodedInstruction);
                break;

        }
    }

    /**
     * Helper method to convert binary numbers stored as byte array to int
     *
     * @param binary Byte array with binary number to convert
     *
     * @return Integer representation of binary number
     */
    private int binaryToInt(byte [] binary) {
        double result = 0;

        for (int i = 0; i < binary.length; i++) {
            int iByte = binary[i] & 0xff;
            String sByte = Integer.toBinaryString(iByte);
            System.out.printf("[ControlUnit::binaryToInt] Processing string for byte %d: %s,\n", i, sByte);
            for (int j = sByte.length() - 1, pow = i * 8; j >= 0; j--, pow++) {
                System.out.printf("[ControlUnit::binaryToInt] Bit at position %d of byte %d is %s\n",
                        j, i, sByte.charAt(j));
                if (sByte.charAt(j) == '1') {
                    System.out.printf("[ControlUnit::binaryToInt] Bit at position %d is set; Power is %d\n", j, pow);
                    result += Math.pow(2, pow);
                    System.out.printf("[ControlUnit::binaryToInt] Current result is: %f\n", result);
                }
            }
        }

        return (int) result;
    }

    public void printMem(){
        mainMemory.printMemory();
    }
}
