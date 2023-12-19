package com.example.alertsystem.model.alertcomponent;

import com.example.alertsystem.model.Condition;

public class ConditionComponent implements AlertComponent {
    private Condition condition;

    public ConditionComponent(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String display() {
        // Example: convert the condition to a readable string
        return "RULE" + condition.toString();
    }
}
