package com.example.alertsystem.model.alertcomponent;

public class OperatorComponent implements AlertComponent {
    private String operator; // "AND", "OR", "(", ")"

    public OperatorComponent(String operator) {
        this.operator = operator;
    }

    @Override
    public String display() {
        return operator;
    }
}
