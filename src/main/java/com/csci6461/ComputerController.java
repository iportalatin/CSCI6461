package com.csci6461;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

/**
 * Class for controlling elements of the UI
 */
public class ComputerController {

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

    ToggleButton[] bitController;

    CheckBox[] pcController;
    CheckBox[] marController;
    CheckBox[] mbrController;
    CheckBox[] gpr0Controller;
    CheckBox[] gpr1Controller;
    CheckBox[] gpr2Controller;
    CheckBox[] gpr3Controller;

    /**
     * Handles loading of bits for the program counter.
     */
    @FXML
    protected void onPCLoadClick() {
        if (pcController == null){
            pcController = new CheckBox[]{pc1,pc2,pc4,pc8,pc16,pc32,pc64,pc128,pc256,pc512,pc1024,pc2048};
        }

        translateBits(pcController);
    }

    /**
     * Handles loading of bits for the MAR.
     */
    @FXML
    protected void onMARLoadClick() {
        if (marController == null){
            marController = new CheckBox[]{mar1,mar2,mar4,mar8,mar16,mar32,mar64,mar128,mar256,mar512,mar1024,mar2048};
        }

        translateBits(marController);
    }

    /**
     * Handles loading of bits for the MBR.
     */
    @FXML
    protected void onMBRLoadClick() {
        if (mbrController == null){
            mbrController = new CheckBox[]{mbr1,mbr2,mbr4,mbr8,mbr16,mbr32,mbr64,mbr128,mbr256,mbr512,mbr1024,mbr2048,
                    mbr4096,mbr8192,mbr16384,mbr32768};
        }

        translateBits(mbrController);
    }

    /**
     * Handles loading of bits for the GPR0.
     */
    @FXML
    protected void onGPR0LoadClick() {
        if (gpr0Controller == null){
            gpr0Controller = new CheckBox[]{gpr10,gpr20,gpr40,gpr80,gpr160,gpr320,gpr640,gpr1280,gpr2560,gpr5120,
                    gpr10240,gpr20480,gpr40960,gpr81920,gpr163840,gpr327680};
        }

        translateBits(gpr0Controller);
    }

    /**
     * Handles loading of bits for the GPR0.
     */
    @FXML
    protected void onGPR1LoadClick() {
        if (gpr1Controller == null){
            gpr1Controller = new CheckBox[]{gpr11,gpr21,gpr41,gpr81,gpr161,gpr321,gpr641,gpr1281,gpr2561,
                    gpr5121,gpr10241,gpr20481,gpr40961, gpr81921,gpr163841,gpr327681};
        }

        translateBits(gpr1Controller);
    }

    /**
     * Handles loading of bits for the GPR0.
     */
    @FXML
    protected void onGPR2LoadClick() {
        if (gpr2Controller == null){
            gpr2Controller = new CheckBox[]{gpr12,gpr22,gpr42,gpr82,gpr162,gpr322,gpr642,gpr1282,gpr2562,gpr5122,
                    gpr10242,gpr20482,gpr40962, gpr81922,gpr163842,gpr327682};
        }

        translateBits(gpr2Controller);
    }

    /**
     * Handles loading of bits for the GPR3.
     */
    @FXML
    protected void onGPR3LoadClick() {
        if (gpr3Controller == null){
            gpr3Controller = new CheckBox[]{gpr13,gpr23,gpr43,gpr83,gpr163,gpr323,gpr643,gpr1283,gpr2563,
                    gpr5123,gpr10243,gpr20483,gpr40963, gpr81923,gpr163843,gpr327683};
        }

        translateBits(gpr3Controller);
    }


    /**
     * Gets the 16-bit array values and flips the bits before resetting the user selected bits.
        @param controller The checkbox controller
     */
    private void translateBits(CheckBox[] controller) {
       if(bitController == null){
           bitController = new ToggleButton[]{adr0, adr1, adr2, adr3, adr4, i5, ixr6, ixr7, gpr8, gpr9, ctlA, ctlB,
                   ctlC, ctlD, ctlE, ctlF};
       }

       for(int i=0; i<controller.length; i++){
           controller[i].setSelected(bitController[i].isSelected());
           bitController[i].setSelected(false);
       }

    }

}