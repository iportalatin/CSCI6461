/**
 * The project module.
 */
module com.csci6461 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.csci6461 to javafx.fxml;
    exports com.csci6461;
}