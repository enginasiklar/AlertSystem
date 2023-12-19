module com.example.alertsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires lombok;
    //requires eu.hansolo.tilesfx;

    opens com.example.alertsystem to javafx.fxml;
    exports com.example.alertsystem;
    exports com.example.alertsystem.controller;
    opens com.example.alertsystem.controller to javafx.fxml;
}