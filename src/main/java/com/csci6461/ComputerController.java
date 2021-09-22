package com.csci6461;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.math.BigInteger;

/**
 * Class for controlling elements of the UI.
 * @author Ellis Thompson
 */
public class ComputerController {

    /**
     * Control unit for the system
     */
    private ControlUnit cu = new ControlUnit();

    @FXML
    private ToggleButton adr0, adr1, adr2, adr3, adr4;

    @FXML
    private ToggleButton i5;

    @FXML
    private ToggleButton ixr6, ixr7;

    @FXML
    private ToggleButton gpr8, gpr9;

    @FXML
    private ToggleButton ctlA, ctlB, ctlC, ctlD, ctlE, ctlF;

    @FXML
    private CheckBox pc1,pc2,pc4,pc8,pc16,pc32,pc64,pc128,pc256,pc512,pc1024,pc2048;

    @FXML
    private CheckBox mar1,mar2,mar4,mar8,mar16,mar32,mar64,mar128,mar256,mar512,mar1024,mar2048;

    @FXML
    private CheckBox mbr1,mbr2,mbr4,mbr8,mbr16,mbr32,mbr64,mbr128,mbr256,mbr512,mbr1024,mbr2048,mbr4096,
            mbr8192,mbr16384,mbr32768;

    @FXML
    private CheckBox gpr10,gpr20,gpr40,gpr80,gpr160,gpr320,gpr640,gpr1280,gpr2560,gpr5120,gpr10240,gpr20480,gpr40960,
            gpr81920,gpr163840,gpr327680;
    @FXML
    private CheckBox gpr11,gpr21,gpr41,gpr81,gpr161,gpr321,gpr641,gpr1281,gpr2561,gpr5121,gpr10241,gpr20481,gpr40961,
            gpr81921,gpr163841,gpr327681;
    @FXML
    private CheckBox gpr12,gpr22,gpr42,gpr82,gpr162,gpr322,gpr642,gpr1282,gpr2562,gpr5122,gpr10242,gpr20482,gpr40962,
            gpr81922,gpr163842,gpr327682;
    @FXML
    private CheckBox gpr13,gpr23,gpr43,gpr83,gpr163,gpr323,gpr643,gpr1283,gpr2563,gpr5123,gpr10243,gpr20483,gpr40963,
            gpr81923,gpr163843,gpr327683;

    @FXML
    private CheckBox ixr0_1, ixr0_2, ixr0_3, ixr0_4, ixr0_5,ixr0_6,ixr0_7,ixr0_8,ixr0_9,ixr0_10,ixr0_11,ixr0_12,
            ixr0_13,ixr0_14,ixr0_15,ixr0_16;
    @FXML
    private CheckBox ixr1_1, ixr1_2, ixr1_3, ixr1_4, ixr1_5,ixr1_6,ixr1_7,ixr1_8,ixr1_9,ixr1_10,ixr1_11,ixr1_12,
            ixr1_13,ixr1_14,ixr1_15,ixr1_16;
    @FXML
    private CheckBox ixr2_1, ixr2_2, ixr2_3, ixr2_4, ixr2_5,ixr2_6,ixr2_7,ixr2_8,ixr2_9,ixr2_10,ixr2_11,ixr2_12,
            ixr2_13,ixr2_14,ixr2_15,ixr2_16;

    @FXML
    private CheckBox mfr_1,mfr_2,mfr_4,mfr_8;

    @FXML
    private CheckBox ir_1,ir_2,ir_3,ir_4,ir_5,ir_6,ir_7,ir_8,ir_9,ir_10,ir_11,ir_12,ir_13,ir_14,ir_15,ir_16;

    private ToggleButton[] bitController;

    private CheckBox[] pcController;
    private CheckBox[] marController;
    private CheckBox[] mbrController;
    private CheckBox[] gpr0Controller;
    private CheckBox[] gpr1Controller;
    private CheckBox[] gpr2Controller;
    private CheckBox[] gpr3Controller;
    private CheckBox[] ixr0Controller;
    private CheckBox[] ixr1Controller;
    private CheckBox[] ixr2Controller;
    private CheckBox[] irController;
    private CheckBox[] mfrController;

    private CheckBox[][] gpr;
    private CheckBox[][] ixr;


    @FXML
    private void initialize() {
        bitController = new ToggleButton[]{adr0, adr1, adr2, adr3, adr4, i5, ixr6, ixr7, gpr8, gpr9, ctlA, ctlB,
                ctlC, ctlD, ctlE, ctlF};

        pcController = new CheckBox[]{pc1,pc2,pc4,pc8,pc16,pc32,pc64,pc128,pc256,pc512,pc1024,pc2048};
        marController = new CheckBox[]{mar1,mar2,mar4,mar8,mar16,mar32,mar64,mar128,mar256,mar512,mar1024,mar2048};
        mbrController = new CheckBox[]{mbr1,mbr2,mbr4,mbr8,mbr16,mbr32,mbr64,mbr128,mbr256,mbr512,mbr1024,mbr2048,
                mbr4096,mbr8192,mbr16384,mbr32768};

        gpr0Controller = new CheckBox[]{gpr10,gpr20,gpr40,gpr80,gpr160,gpr320,gpr640,gpr1280,gpr2560,gpr5120,
                gpr10240,gpr20480,gpr40960,gpr81920,gpr163840,gpr327680};
        gpr1Controller = new CheckBox[]{gpr11,gpr21,gpr41,gpr81,gpr161,gpr321,gpr641,gpr1281,gpr2561,
                gpr5121,gpr10241,gpr20481,gpr40961, gpr81921,gpr163841,gpr327681};
        gpr2Controller = new CheckBox[]{gpr12,gpr22,gpr42,gpr82,gpr162,gpr322,gpr642,gpr1282,gpr2562,gpr5122,
                gpr10242,gpr20482,gpr40962, gpr81922,gpr163842,gpr327682};
        gpr3Controller = new CheckBox[]{gpr13,gpr23,gpr43,gpr83,gpr163,gpr323,gpr643,gpr1283,gpr2563,
                gpr5123,gpr10243,gpr20483,gpr40963, gpr81923,gpr163843,gpr327683};

        ixr0Controller = new CheckBox[]{ixr0_1, ixr0_2, ixr0_3, ixr0_4, ixr0_5,ixr0_6,ixr0_7,ixr0_8,ixr0_9,ixr0_10,
                ixr0_11,ixr0_12,
                ixr0_13,ixr0_14,ixr0_15,ixr0_16};
        ixr1Controller = new CheckBox[]{ixr1_1, ixr1_2, ixr1_3, ixr1_4, ixr1_5,ixr1_6,ixr1_7,ixr1_8,ixr1_9,ixr1_10,
                ixr1_11,ixr1_12,
                ixr1_13,ixr1_14,ixr1_15,ixr1_16};
        ixr2Controller = new CheckBox[]{ixr2_1, ixr2_2, ixr2_3, ixr2_4, ixr2_5,ixr2_6,ixr2_7,ixr2_8,ixr2_9,ixr2_10,
                ixr2_11,ixr2_12,
                ixr2_13,ixr2_14,ixr2_15,ixr2_16};

        irController = new CheckBox[]{ir_1,ir_2,ir_3,ir_4,ir_5,ir_6,ir_7,ir_8,ir_9,ir_10,ir_11,ir_12,ir_13,ir_14,
                ir_15,ir_16};
        mfrController = new CheckBox[]{mfr_1,mfr_2,mfr_4,mfr_8};

        gpr = new CheckBox[][]{gpr0Controller,gpr1Controller,gpr2Controller,gpr3Controller};
        ixr = new CheckBox[][]{ixr0Controller,ixr1Controller,ixr2Controller};

    }

    /**
     * Handles loading of bits for the program counter.
     */
    @FXML
    protected void onPCLoadClick() throws IOException {

        // Load the byte array into the controller and set UI bits
        cu.pc.load(translateBits(pcController));
    }

    /**
     * Handles loading of bits for the MAR.
     */
    @FXML
    protected void onMARLoadClick() throws IOException{
        cu.mar.load(translateBits(marController));
    }

    /**
     * Handles loading of bits for the MBR.
     */
    @FXML
    protected void onMBRLoadClick() throws IOException{
        cu.mbr.load(translateBits(mbrController));
    }

    /**
     * Handles loading of bits for the GPR0.
     */
    @FXML
    protected void onGPR0LoadClick() throws IOException{
        cu.gpr[0].load(translateBits(gpr0Controller));
    }

    /**
     * Handles loading of bits for the GPR0.
     */
    @FXML
    protected void onGPR1LoadClick() throws IOException{
        cu.gpr[1].load(translateBits(gpr1Controller));
    }

    /**
     * Handles loading of bits for the GPR0.
     */
    @FXML
    protected void onGPR2LoadClick() throws IOException{

        cu.gpr[2].load(translateBits(gpr2Controller));
    }

    /**
     * Handles loading of bits for the GPR3.
     */
    @FXML
    protected void onGPR3LoadClick() throws IOException{
        cu.gpr[3].load(translateBits(gpr3Controller));
    }

    /**
     * Handles loading of bits for the IXR0.
     */
    @FXML
    protected void onIXR0LoadClick() throws IOException{
        cu.ixr[0].load(translateBits(ixr0Controller));
    }

    /**
     * Handles loading of bits for the IXR0.
     */
    @FXML
    protected void onIXR1LoadClick() throws IOException{
        cu.ixr[1].load(translateBits(ixr1Controller));
    }

    /**
     * Handles loading of bits for the IXR0.
     */
    @FXML
    protected void onIXR2LoadClick() throws IOException{
        cu.ixr[2].load(translateBits(ixr2Controller));
    }



    /**
     * Gets the 16-bit array values and flips the bits before resetting the user selected bits.
        @param controller The checkbox controller
        @return A byte array
     */
    private boolean[] translateBits(CheckBox[] controller) {
        // Create a new bit set to track positions of 'on' bits
        boolean[] bits = new boolean[controller.length];

        // Loop through the controller setting matching bits and adding the correct bit to the controller, reset bit.
        for(int i=0; i<controller.length; i++) {
            boolean val = bitController[i].isSelected();
            controller[i].setSelected(val);
            bitController[i].setSelected(false);

            // If bit is on add to bit set
            bits[controller.length-(1+i)] = val;
        }

        // Return the byte array
        return bits;

    }

    /**
     * Allows the user to select a file and then load that file into memory.
     * @throws IOException
     */
    @FXML
    protected void onLoadFileClick() {
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fc.showOpenDialog(null);
        String file_path = fc.getSelectedFile().getAbsolutePath();

        try {
            File thisFile = new File(file_path);
            Scanner reader = new Scanner(thisFile);

            while (reader.hasNextLine()) {

                String[] data = reader.nextLine().split(" ",2);
                short memory = toByteArray(data[0]);
                short value = toByteArray(data[1]);

                cu.load(memory,value);
            }
        } catch (FileNotFoundException e){
            System.out.println("ERROR: File not found!");
        }
    }

    /**
     * For printing the memory to the stack
     */
    @FXML
    protected void onPrtMemClick(){
        cu.printMem();
    }

    /**
     * Advance the simulation 1 step
     * @throws IOException
     */
    @FXML
    protected void onStepClick() throws IOException{
        cu.singleStep();
        updateUI();
    }

    /**
     * Update the entire UI after a step
     */
    private void updateUI() {
        setUIElems(cu.gpr, gpr);
        setUIElems(cu.ixr, ixr);

        setUIElem(cu.pc,pcController);
        setUIElem(cu.mar,marController);
        setUIElem(cu.ir,irController);

        // Memory Fault register
        // setUIElem(cu.mfr,mfrController);
    }

    /**
     * Function to convert to a 16-bit short
     * @param s The binary string
     * @return A short (byte array)
     */
    private short toByteArray(String s) {
        short it = (short) Integer.parseInt(s, 16);
        System.out.println("Hexadecimal String: " + s);
        return it;
    }

    /**
     * Set multiple UI elements
     * @param registers The array of registers
     * @param controllers The array of UI controllers
     */
    private void setUIElems(Register[] registers, CheckBox[][] controllers){
        for(int i = 0; i< registers.length;i++){
            CheckBox[] controller = controllers[i];
            resetUI(controller);
            int[] set_bits = registers[i].getSetBits();


            if(set_bits == null){
                continue;
            }

            for(int a: set_bits){
                controller[15-a].setSelected(true);
            }
        }
    }

    /**
     * Set a single UI element
     * @param register The register to set
     * @param controller The UI controller
     */
    private void setUIElem(Register register, CheckBox[] controller){
        resetUI(controller);
        int[] set_bits = register.getSetBits();

        if(set_bits == null){
            return;
        }

        int length = register.get_size()-1;

        for(int a: set_bits){
            controller[length-a].setSelected(true);
        }
    }

    /**
     * Resets the UI element.
     * @param controller Takes the check box controller
     */
    private void resetUI(CheckBox[] controller) {
        for(CheckBox x:controller){
            x.setSelected(false);
        }
    }

}