package com.garden;

import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class Tomato extends Plant {
    public Tomato() {
        super("Tomato", 60,12, Arrays.asList("flea beetles", "leaf miners"));
    }

    public Tomato(GridPane gardenGrid) {
        super("Tomato", 60,12, Arrays.asList("flea beetles", "leaf miners"), gardenGrid);
    }
}
