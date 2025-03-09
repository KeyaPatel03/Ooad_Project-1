package com.garden;

import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class Orange extends Plant {

    public Orange() {
        super("Orange", 75, 10, Arrays.asList("citrus psyllid", "Navel orangeworm"));
    }

    public Orange(GridPane gardenGrid) {
        super("Orange", 75, 10, Arrays.asList("citrus psyllid", "Navel orangeworm"), gardenGrid);
    }
}
