/*
 * This file implements the Decoder Config class for the CSCI 6461 project.
 */
package com.csci6461;

import java.util.*;

/**
 * This class defines the configuration for the instruction decoder of the
 * CSCI 6461 computer simulation. It loads basic information about instructions
 * like the Opcode and the class used to implement it.
 *
 * @author imanuelportalatin
 */
public class DecoderConfig {
    /**
     * This map enables us to look up instructions by their Opcode
     */
    private Map<Integer, Instruction> instructions = new HashMap<Integer, Instruction>();

    /**
     * Decoder config constructor that initializes the map of valid instructions.
     * When implementing a new instruction, add a call to the 'instruction' map's put method and
     * pass the Opcode as the key and new object of a class implementing the Instruction abstract
     * class and pass the instruction's name (I.e. HLT, LDR, ADD, etc.) to the constructor.
     * The class implementing the abstract Instruction class must implement a method to decode
     * and return parameters for that type of instruction.
     */
    DecoderConfig() {
        instructions.put(000, new MiscInstruction("HLT"));
        instructions.put(036, new MiscInstruction("TRAP"));
    }

    /**
     * Method to get an instruction from its Opcode
     *
     * @param opCode Instruction object implementing instruction of this type
     *
     * @return Instruction object or null if instruction is not configured
     */
    public Instruction getInstruction(int opCode){
        Instruction instruction = instructions.get(opCode);

        if (instruction != null) {
            return instruction;
        } else {
            return null;
        }
    }

}
