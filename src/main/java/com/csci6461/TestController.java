/**
 * This file implements a simple test controller to that implements a main
 * function to instantiate and test the main controller. I built this as a
 * standalone Java console application in NetBeans to test the backend classes
 * since I could not get the JavaFX GUI to execute on any IDE. Eventually this
 * should be replaced by the GUI controller.
 */
package com.csci6461;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

/**
 *
 * @author imanuelportalatin
 */
public class TestController {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting test controller");

        /* Create new control unit */
        ControlUnit cu = new ControlUnit();

        /* Test getting Program Counter (PC) properties */
        String pcName = cu.pc.getName();
        int pcSize = cu.pc.getSize();

        System.out.printf("PC register properties: name = %s, size = %d\n",
                pcName, pcSize);

        /* Check number of GPRs */
        int nGPR = cu.gpr.length;
        System.out.printf("Found %d GPRs in Control Unit.\n", nGPR);

        /* Get properties for each GPR */
        for (int i = 0; i < nGPR; i++) {
            String gprName = cu.gpr[i].getName();
            int gprSize = cu.gpr[i].getSize();

            System.out.printf("Properties for GPR %d: name = %s, size = %d\n",
                    i, gprName, gprSize);
        }

        /* Test getting Memory Address Register (MAR) properties */
        String marName = cu.mar.getName();
        int marSize = cu.mar.getSize();

        System.out.printf("MAR register properties: name = %s, size = %d\n",
                marName, marSize);

        /* Test getting Memory Buffer Register (MBR) properties */
        String mbrName = cu.mbr.getName();
        int mbrSize = cu.mbr.getSize();

        System.out.printf("MBR register properties: name = %s, size = %d\n",
                mbrName, mbrSize);

        /* Test loading a alternating zeros and ones into the pc register */
        byte[] value = new byte[]{(byte) 0xff, (byte) 0x0A, (byte) 0x00};
        int[] input = new int[3];
        input[0] = value[0] & 0xff;
        input[1] = value[1] & 0xff;
        input[2] = value[2] & 0xff;
        System.out.printf("Writing data to pc: %s %s %s\n",
                Integer.toBinaryString(input[2]),
                Integer.toBinaryString(input[1]),
                Integer.toBinaryString(input[0]));
//        System.out.printf("Loading value into pc: %s\n", Arrays.toString(value));

        try {
            cu.pc.load(value);
        } catch (IOException ioe) {
            System.out.println("Execption while loading test word into pc...");
            ioe.printStackTrace();
        }

        /* Test getting set bits from register */
        int[] bits = cu.pc.getSetBits();
//        System.out.println("Retrieved set bits from pc:");
//        for (int i = 0; i < bits.length; i++) {
//            System.out.println(bits[i]);
//        }

        /* Test reading the pc register after load */
        byte[] outValue = cu.pc.read();

        int[] result = new int[2];
        result[0] = outValue[0] & 0xff;
        result[1] = outValue[1] & 0xff;
        System.out.printf("Read data from pc: %s %s\n",
                Integer.toBinaryString(result[1]),
                Integer.toBinaryString(result[0]));

        /* Test setting bits in register */
        System.out.println("Setting bits on pc...");
        int[] ones = new int[]{1,3,5,7,9,11};
        try {
            cu.pc.setBits(ones);
        } catch (IOException ioe) {
            System.out.println("Exception while setting bits on pc...");
            ioe.printStackTrace();
        }

        /* Read pc after setting bits */
        outValue = cu.pc.read();

        result[0] = outValue[0] & 0xff;
        result[1] = outValue[1] & 0xff;
        System.out.printf("Read data from pc: %s %s\n",
                Integer.toBinaryString(result[1]),
                Integer.toBinaryString(result[0]));

//        /* Test overflow protection on the pc register */
//        value[2] = (byte) 0x01;
//
//        try {
//            cu.pc.load(value);
//        } catch (IOException ioe) {
//            System.out.println("Exception while loading test word into pc...");
//            ioe.printStackTrace();
//        }

        /* Test overflow protection when setting bits */
//        ones = Arrays.copyOf(ones, ones.length + 1);
//        ones[ones.length - 1] = 12;
//
//        System.out.println("Setting overflow bits on pc...");
//        try {
//            cu.pc.setBits(ones);
//        } catch (IOException ioe) {
//            System.out.println("Exception while setting bits on pc...");
//            ioe.printStackTrace();
//        }

        /* Write a bunch of data to random locations in memory */
        short[] testData = new short[]{(short) 0xAAAA, (short) 0xffff};
        int[] addresses = new int[100];
        for (int i = 0; i < 100; i++) {
            int r = (int) (Math.random() * 2048);
            addresses[i] = Math.abs(r);
            short word = testData[i % 2];
//            System.out.printf("Writing word %d to address %d\n", word, addresses[i]);

            cu.load(addresses[i], word);
        }

        /* Put an instruction in memory and save address to PC */
        short testInstruction = (short) 0xAAff;
        byte[] testAddress = new byte[]{ 0x10, 0x04 };
        result[0] = testAddress[1] & 0xff;
        result[1] = testAddress[0] & 0xff;
        System.out.printf("Test instruction set to: %s %s\n",
                Integer.toBinaryString(result[0]),
                Integer.toBinaryString(result[1]));

        cu.load(1040, testInstruction);
        try {
            cu.pc.load(testAddress);
        } catch (IOException ioe) {
            System.out.println("Exception while loading test address into pc...");
            ioe.printStackTrace();
        }

        /* Call singleStep on CU to execute test instruction */
        try {
            cu.singleStep();
        } catch (IOException ioe) {
            System.out.println("Exception during single step execution...");
            ioe.printStackTrace();
        }

        /* Start the controller and display time before and after */
//        LocalTime now = LocalTime.now();
//        System.out.println("Starting CU at time:");
//        System.out.println(now);
//        cu.run();
//        now = LocalTime.now();
//        System.out.println("CU run stopped at time:");
//        System.out.println(now);

        System.out.println("Finishing test controller execution");
    }
}
