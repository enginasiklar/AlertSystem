package com.example.alertsystem.model.alertcomponent;

public class ParanthesisComponent implements AlertComponent {
    private final String parenthesis; // "(" or ")"

    public ParanthesisComponent(String parenthesis) {
        this.parenthesis = parenthesis;
    }

    @Override
    public String display() {
        return parenthesis;
    }
}
