package com.example.alertsystem.controller;

import com.example.alertsystem.model.Condition;
import com.example.alertsystem.model.SharedResource;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlertLogicComponentController {

    @FXML
    public Button btnAddComponent;
    @FXML
    private HBox hboxLogicContainer;
    private HBox movingComponent = null;
    private final Map<String, Condition> ruleMap;
    private List<HBox> componentOrder;

    public AlertLogicComponentController() {
        this.componentOrder = SharedResource.getComponentOrder();
        this.ruleMap = SharedResource.getRuleMap();
        ruleMap.put("Rule 1", new Condition(true, 1));
        ruleMap.put("Rule 2", new Condition(false, 2));
        ruleMap.put("Rule 3", new Condition(true, 3));
    }

    public void addRule(String ruleName, boolean ruleValue) {
        ruleMap.put(ruleName, new Condition(ruleValue, ruleMap.size() + 1));
    }


    public Map<String, Condition> getRuleMap() {
        return ruleMap;
    }

    // Method to set componentOrder
    public void setComponentOrder(List<HBox> componentOrder) {
        this.componentOrder = componentOrder;
    }

    public boolean validateLogicalExpression() {
        if (componentOrder.isEmpty()) {
            return false; // No components mean no valid expression
        }

        int openParentheses = 0;
        boolean expectingRule = true;

        for (HBox container : componentOrder) {
            Node component = container.getChildren().get(0);
            if (component instanceof Label) {
                String value = ((Label) component).getText();
                if ("(".equals(value)) {
                    openParentheses++;
                    continue; // After an open parenthesis, we can still expect a rule
                } else if (")".equals(value)) {
                    if (openParentheses == 0 || expectingRule) {
                        return false; // Unbalanced or misplaced close parenthesis
                    }
                    openParentheses--;
                    expectingRule = false; // After a close parenthesis, we should not expect a rule
                    continue;
                }
            } else if (component instanceof ComboBox) {
                String value = ((ComboBox<String>) component).getValue();
                if (expectingRule && isRule(value) || !expectingRule && isOperator(value)) {
                    expectingRule = !expectingRule;
                } else {
                    return false; // Found consecutive rules or operators, or mismatched types
                }
            }
        }
        return openParentheses == 0 && !expectingRule; // Ensure all parentheses are closed and last component is not a rule
    }

    private boolean isRule(String value) {
        // Implement logic to determine if value is a rule
        // For now, assume values "Rule 1", "Rule 2", "Rule 3" are rules
        return value.startsWith("Rule");
    }

    private boolean isOperator(String value) {
        // Implement logic to determine if value is an operator
        // For now, assume values "AND", "OR" are operators
        return "AND".equals(value) || "OR".equals(value);
    }

    @FXML
    private void onAddComponent(ActionEvent event) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem addRuleItem = new MenuItem("Add Rule");
        addRuleItem.setOnAction(e -> addRule());

        MenuItem addOperatorItem = new MenuItem("Add Operator");
        addOperatorItem.setOnAction(e -> addOperator());

        MenuItem addOpenParenthesisItem = new MenuItem("Open Parenthesis");
        addOpenParenthesisItem.setOnAction(e -> addParenthesis("("));

        MenuItem addCloseParenthesisItem = new MenuItem("Close Parenthesis");
        addCloseParenthesisItem.setOnAction(e -> addParenthesis(")"));

        contextMenu.getItems().addAll(addRuleItem, addOperatorItem, addOpenParenthesisItem, addCloseParenthesisItem);
        contextMenu.show(btnAddComponent, Side.BOTTOM, 0, 0);
    }

    private void addParenthesis(String parenthesis) {
        Button parenthesisButton = new Button(parenthesis);
        parenthesisButton.setOnAction(e -> showOptionsContextMenu(parenthesisButton));
        /*
        HBox container = new HBox();
        container.getChildren().add(parenthesisButton);
        componentOrder.add(container);
        updateHBox();*/
    }

    private void showOptionsContextMenu(Node component) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(e -> deleteComponent((HBox) component));

        MenuItem moveItem = new MenuItem("Move");
        moveItem.setOnAction(e -> moveComponent((HBox) component));

        contextMenu.getItems().addAll(deleteItem, moveItem);
        contextMenu.show(component, Side.BOTTOM, 0, 0);
    }

    private void addRule() {
        ComboBox<String> ruleComboBox = createRuleComboBox();
        addComponentWithOptions(ruleComboBox);
    }

    private void addOperator() {
        ComboBox<String> operatorComboBox = createOperatorComboBox();
        addComponentWithOptions(operatorComboBox);
    }

    private void addComponentWithOptions(Node component) {
        HBox container = new HBox();
        container.getChildren().add(component);
        Button optionsButton = createOptionsButton(container);
        container.getChildren().add(optionsButton);
        componentOrder.add(container);
        updateHBox();
    }

    private ComboBox<String> createRuleComboBox() {
        ComboBox<String> ruleComboBox = new ComboBox<>();

        // Get the rule names from the ruleMap
        List<String> ruleNames = new ArrayList<>(ruleMap.keySet());

        ruleComboBox.setItems(FXCollections.observableArrayList(ruleNames));

        // Set the default value to the first rule (if available)
        if (!ruleNames.isEmpty()) {
            ruleComboBox.setValue(ruleNames.get(0));
        }

        return ruleComboBox;
    }




    private ComboBox<String> createOperatorComboBox() {
        ComboBox<String> operatorComboBox = new ComboBox<>();
        operatorComboBox.setItems(FXCollections.observableArrayList("AND", "OR"));
        operatorComboBox.setValue("AND");
        return operatorComboBox;
    }

    private Button createOptionsButton(HBox container) {
        Button optionsButton = new Button("o");
        optionsButton.setOnAction(e -> showOptionsContextMenu(container));
        return optionsButton;
    }

    private void showOptionsContextMenu(HBox container) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(e -> deleteComponent(container));

        MenuItem moveItem = new MenuItem("Move");
        moveItem.setOnAction(e -> moveComponent(container));

        contextMenu.getItems().addAll(deleteItem, moveItem);
        contextMenu.show(container, Side.BOTTOM, 0, 0);
    }

    private void deleteComponent(HBox container) {
        componentOrder.remove(container);
        updateHBox();
    }

    private void moveComponent(HBox container) {
        movingComponent = container;
        highlightDropZones();
    }

    private HBox findContainer(Node component) {
        return componentOrder.stream()
                .filter(container -> container.getChildren().contains(component))
                .findFirst()
                .orElse(null);
    }

    private void highlightDropZones() {
        clearDropZones();
        for (int i = 0; i <= componentOrder.size(); i++) {
            Button dropZone = new Button("↓");
            dropZone.getStyleClass().add("drop-zone");
            final int index = i; // Use a final variable for the lambda expression
            dropZone.setOnAction(e -> onDropZoneClicked(index));
            hboxLogicContainer.getChildren().add(i * 2, dropZone);
        }
    }

    private void onDropZoneClicked(int index) {
        if (movingComponent != null) {
            componentOrder.remove(movingComponent);
            // Adjust index if it's the last position
            if (index > componentOrder.size()) {
                index = componentOrder.size();
            }
            componentOrder.add(index, movingComponent);
            updateHBox();
            clearDropZones();
            movingComponent = null;
        }
    }

    private void clearDropZones() {
        hboxLogicContainer.getChildren().removeIf(node -> node instanceof Button && "↓".equals(((Button) node).getText()));
    }

    private void updateHBox() {
        hboxLogicContainer.getChildren().clear();
        hboxLogicContainer.getChildren().addAll(componentOrder);
        hboxLogicContainer.getChildren().add(btnAddComponent); // Add button at the end
    }

    public String buildBooleanExpression() {
        StringBuilder booleanExpression = new StringBuilder();
        for (HBox container : componentOrder) {
            Node component = container.getChildren().get(0);
            if (component instanceof ComboBox) {
                String value = ((ComboBox<String>) component).getValue();
                if (isRule(value)) {
                    // Replace the rule with its boolean value
                    boolean ruleValue = getRuleValue(value); // Implement this method
                    booleanExpression.append(ruleValue ? "1" : "0");
                } else if (isOperator(value)) {
                    // Convert logical operators to their boolean counterparts
                    booleanExpression.append(convertToBooleanOperator(value));
                }
            } else if (component instanceof Label) {
                // Directly append parentheses
                booleanExpression.append(((Label) component).getText());
            }
        }
        return booleanExpression.toString();
    }

    private boolean getRuleValue(String rule) {
        // Assuming rule is in the format "Rule X"
        if (ruleMap.containsKey(rule)) {
            return ruleMap.get(rule).isActive();
        }
        // If the rule is not found, return a default value (false in this case)
        return false;
    }

    private String convertToBooleanOperator(String operator) {
        switch (operator) {
            case "AND":
                return "&&";
            case "OR":
                return "||";
            default:
                return "";
        }
    }



}
