package com.example.alertsystem.controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AlertCreateController {

    // UI components connected via fx:id
    @FXML
    private ComboBox<String> comboBoxCriteria;
    @FXML
    private ComboBox<String> comboBoxSymbols;
    @FXML
    private ComboBox<String> comboBoxCondition;
    @FXML
    private TextField textFieldValue;
    @FXML
    private ComboBox<String> comboBoxPercentageDollar;
    @FXML
    private ComboBox<String> comboBoxWhen;
    @FXML
    private ComboBox<String> comboBoxLogic;
    @FXML
    private ComboBox<String> comboBoxList;
    @FXML
    private ComboBox<String> comboBoxChannel;

    // Additional fields to track the rules and logic
    private final StringBuilder ruleBuilder = new StringBuilder();

    @FXML
    private void initialize() {
        // ... existing initialization ...

        // Hide or show the comboBoxSymbols based on comboBoxCriteria selection
        comboBoxCriteria.valueProperty().addListener((observable, oldValue, newValue) -> {
            boolean isGeneralMarket = "General Market".equals(newValue);
            comboBoxSymbols.setVisible(!isGeneralMarket);
            comboBoxSymbols.setManaged(!isGeneralMarket);
            textFieldValue.setVisible(!isGeneralMarket);
            textFieldValue.setManaged(!isGeneralMarket);
            comboBoxPercentageDollar.setVisible(!isGeneralMarket);
            comboBoxPercentageDollar.setManaged(!isGeneralMarket);
        });
    }

    @FXML
    private void handleAddConditionAction() {
        // For demonstration, we just print out the condition
        String condition = comboBoxCriteria.getValue() +
                (comboBoxSymbols.isVisible() ? " " + comboBoxSymbols.getValue() : "") +
                (comboBoxCondition.isVisible() ? " " + comboBoxCondition.getValue() : "") +
                (textFieldValue.isVisible() ? " " + textFieldValue.getText() : "") +
                (comboBoxPercentageDollar.isVisible() ? " " + comboBoxPercentageDollar.getValue() : "");

        System.out.println("Added Condition: " + condition);
        // Append the condition to the rule builder
        if (!ruleBuilder.isEmpty()) {
            ruleBuilder.append(" AND ");
        }
        ruleBuilder.append(condition);
    }

    @FXML
    private void createAlert(ActionEvent event) {
        // When creating the alert, use the ruleBuilder's content to define the logic
        System.out.println("Creating alert with logic: " + ruleBuilder.toString());
        // Here you would pass the logic to the backend system
    }

    // Other methods for handling actions like creating or viewing alerts, lists, and channels
    // Depending on your requirements, you would add those here
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
