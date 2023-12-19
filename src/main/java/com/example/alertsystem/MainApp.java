package com.example.alertsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        navigateToMainScreen(stage);
    }

    public void navigateToMainScreen(Stage stage) {
        try {
            // Load the FXML file for the main screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/alertsystem/main-screen.fxml"));
            Parent root = loader.load();

            // Set the scene to the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Alert System Main Screen");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // The navigateToOtherScreen method can be removed or modified according to your actual navigation logic

    public static void main(String[] args) {
        launch(args);
    }
}
