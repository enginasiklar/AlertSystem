package com.example.alertsystem.controller;
import com.example.alertsystem.model.Condition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class AlertCreateController {


    public Text alertText;
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

    private AlertLogicComponentController alertLogicComponentController;

    public void setAlertLogicComponentController(AlertLogicComponentController controller) {
        this.alertLogicComponentController = controller;
    }
    // Additional fields to track the rules and logic
    private final StringBuilder ruleBuilder = new StringBuilder();

    // Method to set the alert text
    private void setAlertText(String text) {
        alertText.setText(text);
    }

    @FXML
    private void initialize() {

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

    private final Random random = new Random();

    @FXML
    private void handleAddConditionAction() {
        // Create a combination of the ComboBox values
        String conditionText = comboBoxCriteria.getValue() + " " +
                comboBoxSymbols.getValue() + " " +
                comboBoxCondition.getValue() + " " +
                textFieldValue.getText() + " " +
                comboBoxPercentageDollar.getValue();
        setAlertText("Added Condition: " + conditionText);

        System.out.println("Added Condition Text: " + conditionText);

        // Generate a random boolean value for the new rule
        boolean randomBooleanValue = random.nextBoolean();

        // Add a new rule to the AlertLogicComponentController
        String ruleName = "Rule " + (alertLogicComponentController.getRuleMap().size() + 1);
        alertLogicComponentController.addRule(ruleName, randomBooleanValue);

        // Append the condition to the rule builder
        if (!ruleBuilder.isEmpty()) {
            ruleBuilder.append(" AND ");
        }
        ruleBuilder.append(conditionText);
    }

    @FXML
    private void createAlert(ActionEvent event) {
        String booleanExpression = alertLogicComponentController.buildBooleanExpression();

        System.out.println("The booleanExpression is: " + booleanExpression);
        // Evaluate the boolean expression
        Boolean result = evaluateBooleanExpression(booleanExpression);
        System.out.println("The result of the alert logic is: " + result);
    }


    public boolean evaluateBooleanExpression(String expression) {
        // Replace logical operators with spaced versions for correct splitting
        expression = expression.replaceAll("&&", " && ").replaceAll("\\|\\|", " || ")
                .replaceAll("1", "true").replaceAll("0", "false");
        String[] tokens = expression.split(" ");

        boolean result = Boolean.parseBoolean(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            boolean nextValue = Boolean.parseBoolean(tokens[i + 1]);
            if ("&&".equals(tokens[i])) {
                result = result && nextValue;
            } else if ("||".equals(tokens[i])) {
                result = result || nextValue;
            }
        }

        return result;
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
