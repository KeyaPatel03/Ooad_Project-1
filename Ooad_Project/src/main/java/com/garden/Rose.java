package com.garden;


import java.util.Arrays;

import javafx.scene.layout.GridPane;


public class Rose extends Plant {

    public Rose() {
        super("Rose", 70, 15, Arrays.asList("Aphids", "Spider Mites"), 100);
    }

    public Rose( GridPane gardenGrid) {
        super("Rose", 70, 15, Arrays.asList("Aphids", "Spider Mites"), 100);
    }


    public void handleRain(int amount) {
        water(amount);
        if (getCurrentWaterLevel() > getWaterRequirement() * 2) {
            setAlive(false);
        }
    }


    public void handleInsectAttack(String insectType) {
        if (getParasites().contains(insectType)) {
            setAlive(false); // Insect attack kills the rose
        }
    }
}
