package com.garden;

import java.util.Arrays;

import javafx.scene.layout.GridPane;

public class Maple extends Plant {
    public Maple() {
        super("Maple", 75, 10, Arrays.asList("citrus psyllid", "Navel orangeworm"), 100);
    }

    public Maple(GridPane gardenGrid) {
        super("Maple", 75, 10, Arrays.asList("citrus psyllid", "Navel orangeworm"), gardenGrid);
    }
}
