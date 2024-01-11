package com.example.alertsystem.model;

import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedResource {
    private static final List<HBox> componentOrder = new ArrayList<>();
    private static final Map<String, Condition> ruleMap = new HashMap<>();

    public static List<HBox> getComponentOrder() {
        return componentOrder;
    }

    public static Map<String, Condition> getRuleMap() {
        return ruleMap;
    }
}
