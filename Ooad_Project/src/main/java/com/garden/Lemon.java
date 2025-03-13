package com.garden;

import java.util.Arrays;

import javafx.scene.layout.GridPane;

public class Lemon extends Plant {
    public Lemon() {
        super("Lemon", 60,12, Arrays.asList("flea beetles", "leaf miners"), 100);
    }

    public Lemon(GridPane gardenGrid) {
        super("Lemon", 60,12, Arrays.asList("flea beetles", "leaf miners"), gardenGrid);
    }
}
