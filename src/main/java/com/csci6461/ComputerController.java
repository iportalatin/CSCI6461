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

    ToggleButton[] bitController;

    CheckBox[] pcController;

    /**
     * Handels loading of bits for the program counter.
     */
    @FXML
    protected void onPCLoadClick() {
        if (pcController == null){
            pcController = new CheckBox[]{pc1,pc2,pc4,pc8,pc16,pc32,pc64,pc128,pc256,pc512,pc1024,pc2048};
        }

        translateBits(pcController);
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