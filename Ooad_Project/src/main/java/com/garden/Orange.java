package com.garden;

import java.util.Arrays;

import javafx.scene.layout.GridPane;

public class Orange extends Plant {

    public Orange() {
        super("Orange", 75, 10, Arrays.asList("citrus psyllid", "Navel orangeworm"), 10);
    }

    public Orange(GridPane gardenGrid) {
        super("Orange", 75, 10, Arrays.asList("citrus psyllid", "Navel orangeworm"), 10);
    }
}
