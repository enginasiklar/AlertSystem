package com.example.alertsystem.model;

public class Condition {
    private boolean isActive;
    public int id;

    public Condition(boolean isActive, int id) {
        this.isActive = isActive;
        this.id = id;
    }

    // Getter for isActive
    public boolean isActive() {
        return isActive;
    }
}
