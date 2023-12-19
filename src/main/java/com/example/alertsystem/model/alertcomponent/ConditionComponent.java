package com.example.alertsystem.model.alertcomponent;

import com.example.alertsystem.model.Condition;

public class ConditionComponent implements AlertComponent {
    private Condition condition;

    public ConditionComponent(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String display() {
        // Display the condition as "Condition[id]"
        return "Condition[" + condition.id + "]";
    }

    public Condition getCondition() {
        return condition;
    }
}
