package com.example.alertsystem.controller;

import com.example.alertsystem.model.SharedResource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainScreenController {

    @FXML
    private void createAlert(ActionEvent event) {
        try {
            SharedResource.getComponentOrder().clear();
            FXMLLoader alertCreationLoader = new FXMLLoader(getClass().getResource("/com/example/alertsystem/alertcreate-screen.fxml"));
            Parent alertCreationScreen = alertCreationLoader.load();

            AlertCreateController alertCreateController = alertCreationLoader.getController();
            AlertLogicComponentController alertLogicComponentController = new AlertLogicComponentController();
            alertCreateController.setAlertLogicComponentController(alertLogicComponentController);

            // Create a larger Scene by setting width and height
            Scene alertCreationScene = new Scene(alertCreationScreen, 1000, 300); // Adjust the dimensions as needed

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(alertCreationScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void createList(ActionEvent event) {
        try {
            // Load the List creation screen FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/alertsystem/listcreate-screen.fxml"));
            Parent listCreationScreen = loader.load();
            Scene listCreationScene = new Scene(listCreationScreen);

            // Cast the source of the event to Node to get the stage
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the stage and show it
            currentStage.setScene(listCreationScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Consider showing an error message to the user
        }
    }

    @FXML
    private void createChannel(ActionEvent event) {
        try {
            // Load the Notification form screen FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/alertsystem/channelcreate-screen.fxml"));
            Parent notificationFormScreen = loader.load();
            Scene notificationFormScene = new Scene(notificationFormScreen);

            // Cast the source of the event to Node to get the stage
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the stage and show it
            currentStage.setScene(notificationFormScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Consider showing an error message to the user
        }
    }


    // These methods will be called when the view buttons are clicked
    @FXML
    private void viewAlerts() {
        System.out.println("View Alerts button clicked");
        // Implement your logic here
    }

    @FXML
    private void viewLists() {
        System.out.println("View Lists button clicked");
        // Implement your logic here
    }

    @FXML
    private void viewChannels() {
        System.out.println("View Channels button clicked");
        // Implement your logic here
    }
}
