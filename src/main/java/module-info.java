/**
 * The project module.
 */
module com.csci6461 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    //requires com.dlsc.formsfx;
    //requires org.kordamp.bootstrapfx.core;
    //requires java.desktop;
//    requires org.testng;

    opens com.csci6461 to javafx.fxml;
    exports com.csci6461;
}