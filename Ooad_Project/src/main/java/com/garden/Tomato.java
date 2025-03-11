package com.garden;

import java.util.Arrays;

import javafx.scene.layout.GridPane;

public class Tomato extends Plant {
    public Tomato() {
        super("Tomato", 60,12, Arrays.asList("flea beetles", "leaf miners"), 100);
    }

    public Tomato(GridPane gardenGrid) {
        super("Tomato", 60,12, Arrays.asList("flea beetles", "leaf miners"), gardenGrid);
    }
}
