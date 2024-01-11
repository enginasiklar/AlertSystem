package com.example.alertsystem.model;

import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;


public class SharedResource {
    private static final List<HBox> componentOrder = new ArrayList<>();

    public static List<HBox> getComponentOrder() {
        return componentOrder;
    }
}


