package com.example.alertsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChannelCreateController {
        @FXML
        private CheckBox smsCheckBox;
        @FXML
        private CheckBox appNotificationCheckBox;
        @FXML
        private CheckBox emailCheckBox;
        @FXML
        private CheckBox appInboxCheckBox;
        @FXML
        private ComboBox<String> listComboBox;
        @FXML
        private Button createListButton;
        @FXML
        private Button editListButton;

        @FXML
        private void initialize() {
            // Initialize your logic here
            listComboBox.getSelectionModel().selectFirst();
        }

        @FXML
        private void handleCreateList() {
            // Logic for creating a list
            System.out.println("Create List button clicked");
        }

        @FXML
        private void handleEditList() {
            // Logic for editing a list
            System.out.println("Edit List button clicked");
        }
        @FXML
        private void returnToMainScreen(ActionEvent event) {
                try {
                        // Load the main screen FXML file
                        Parent mainScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/alertsystem/main-screen.fxml")));
                        Scene mainScene = new Scene(mainScreen);

                        // Get the current stage from the event source
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        // Set the main scene on the current stage
                        currentStage.setScene(mainScene);
                        currentStage.show();
                } catch (IOException e) {
                        e.printStackTrace();
                        // You could show an error message here
                }
        }
    }

