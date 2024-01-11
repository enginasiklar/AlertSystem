package com.example.alertsystem.model.alertcomponent;

import com.example.alertsystem.model.Condition;

public class ConditionComponent implements AlertComponent {
    private Condition condition;

    public ConditionComponent(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String display() {
        // Display the condition's boolean value as a string ("1" or "0")
        return condition.isActive() ? "1" : "0";
    }

    public Condition getCondition() {
        return condition;
    }
}
