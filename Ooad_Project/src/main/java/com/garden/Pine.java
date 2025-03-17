package com.garden;

import java.util.Arrays;

import javafx.scene.layout.GridPane;

public class Pine extends Plant {
    public Pine() {
        super("Pine", 60,12, Arrays.asList("flea beetles", "leaf miners"), 100);
    }

    public Pine(GridPane gardenGrid) {
        super("Pine", 60,12, Arrays.asList("flea beetles", "leaf miners"), 100);
    }
}
