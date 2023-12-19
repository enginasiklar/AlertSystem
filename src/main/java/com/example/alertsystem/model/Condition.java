package com.example.alertsystem.model;


public class Condition {

    private boolean isActive;
    public int id;
    /*private String criteria;
    private String symbol;
    private String condition;
    private double value;
    private String unit;*/

    public Condition(boolean isActive, int id)
            //, String criteria, String symbol, String condition, double value, String unit)
    {
        this.isActive = isActive;
        this.id = id;
        /*this.criteria = criteria;
        this.symbol = symbol;
        this.condition = condition;
        this.value = value;
        this.unit = unit;*/
    }
}
