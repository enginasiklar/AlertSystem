package com.example.alertsystem.model;


public class Condition {

    private boolean isActive;
    private String criteria;
    private String symbol;
    private String condition;
    private double value;
    private String unit;

    // Constructor
    public Condition(boolean isActive, String criteria, String symbol, String condition, double value, String unit) {
        this.isActive = isActive;
        this.criteria = criteria;
        this.symbol = symbol;
        this.condition = condition;
        this.value = value;
        this.unit = unit;
    }
}
