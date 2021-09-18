package com.csci6461;

public class MemOp extends Instruction{
    /**
     * Constructor for MiscInstruction class that calls abstract class constructor
     *
     * @param name String containing instruction's name
     */
    MemOp(String name){
        super(name);
    }

    @Override
    int[] getArguments() {
        String bits = String.format("%16s", Integer.toBinaryString(super.getInstruction())).replace(' ', '0');
        System.out.printf("[MiscInstruction::getArguments] Full instruction %s\n",
                bits);

        String gpr = bits.substring(6,8);
        String ixr = bits.substring(8,10);
        String i = bits.substring(10,11);
        String address = bits.substring(11,16);

        System.out.println(gpr);
        System.out.println(ixr);
        System.out.println(i);
        System.out.println(address);


        /* Save trap code to args array */
        int[] args = new int[4];

        args[0] = Integer.parseInt(gpr);
        args[1] = Integer.parseInt(ixr);
        args[2] = Integer.parseInt(i);
        args[3] = Integer.parseInt(address);

        /* Return args */
        return args;
    }
}
