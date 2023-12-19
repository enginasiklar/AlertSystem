package com.example.alertsystem.model;

import com.example.alertsystem.model.alertcomponent.AlertComponent;

import java.util.ArrayList;
import java.util.List;

public class Alert {
    private List<AlertComponent> components;

    public Alert() {
        this.components = new ArrayList<>();
    }

    public void addComponent(AlertComponent component) {
        components.add(component);
    }

    public String buildExpression() {
        StringBuilder expression = new StringBuilder();
        for (AlertComponent component : components) {
            expression.append(component.display()).append(" ");
        }
        return expression.toString().trim();
    }

    // Additional methods as needed...
}
