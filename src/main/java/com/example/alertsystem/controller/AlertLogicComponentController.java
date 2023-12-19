package com.example.alertsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class AlertLogicComponentController {

    @FXML
    public Button btnAddComponent;
    @FXML
    private HBox hboxLogicContainer;

    @FXML
    private void onAddComponent(ActionEvent event) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem addRuleItem = new MenuItem("Add Rule");
        addRuleItem.setOnAction(e -> addRule());

        MenuItem addOperatorItem = new MenuItem("Add Operator");
        addOperatorItem.setOnAction(e -> addOperator());

        contextMenu.getItems().addAll(addRuleItem, addOperatorItem);

        // Cast the event source to a Node and show the context menu next to the node
        Node source = (Node) event.getSource();
        contextMenu.show(source, javafx.geometry.Side.BOTTOM, 0, 0); // This positions the context menu below the button
    }


    private void addRule() {
        ComboBox<String> ruleComboBox = createRuleComboBox();
        hboxLogicContainer.getChildren().add(hboxLogicContainer.getChildren().size() - 1, ruleComboBox);
    }

    private void addOperator() {
        ComboBox<String> operatorComboBox = createOperatorComboBox();
        hboxLogicContainer.getChildren().add(hboxLogicContainer.getChildren().size() - 1, operatorComboBox);
    }

    private ComboBox<String> createRuleComboBox() {
        ComboBox<String> ruleComboBox = new ComboBox<>();
        ruleComboBox.setItems(FXCollections.observableArrayList("Rule 1", "Rule 2", "Rule 3"));
        ruleComboBox.setValue("Rule 1"); // Set the default value or use some logic
        setupComboBoxForDrag(ruleComboBox);
        return ruleComboBox;
    }

    private ComboBox<String> createOperatorComboBox() {
        ComboBox<String> operatorComboBox = new ComboBox<>();
        operatorComboBox.setItems(FXCollections.observableArrayList("AND", "OR"));
        operatorComboBox.setValue("AND"); // Set the default value or use some logic
        setupComboBoxForDrag(operatorComboBox);
        return operatorComboBox;
    }

    private void setupComboBoxForDrag(ComboBox<String> comboBox) {
        comboBox.setOnDragDetected(event -> {
            Dragboard db = comboBox.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(comboBox.getValue()); // or any unique identifier
            db.setContent(content);
            event.consume();
        });

        comboBox.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                hboxLogicContainer.getChildren().remove(comboBox);
            }
            event.consume();
        });
    }

    private void createDropZones() {
        for (int i = 0; i < hboxLogicContainer.getChildren().size() + 1; i++) {
            Label dropZone = new Label(" ");
            dropZone.getStyleClass().add("drop-zone"); // Use CSS to style the drop zone
            dropZone.setOnDragOver(event -> {
                if (event.getGestureSource() != dropZone && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });

            dropZone.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    // Add the component at the dropZone index
                    // Retrieve and move the component associated with the dragboard content
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            });

            hboxLogicContainer.getChildren().add(i * 2, dropZone);
        }
    }

    private void clearDropZones() {
        hboxLogicContainer.getChildren().removeIf(node -> " ".equals(node.getId()));
    }

}
