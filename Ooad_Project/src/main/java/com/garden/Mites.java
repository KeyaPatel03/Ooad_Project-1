package com.garden;

import java.util.Objects;

import javafx.scene.image.Image;

public class Mites extends Insect {
    private static final Image caterpillar = new Image(Objects.requireNonNull(Mites.class.getResourceAsStream("/images/miite.png")));

    public Mites() {
        super("Mites", caterpillar, 0, 0);
    }

    public int getDamageByPlant(String plantType) {
        switch (plantType.toLowerCase()) {
            case "rose":
                return 7;
            case "maple":
                return 5;
            case "lemon":
                return 4;
            case "orange":
                return 6;
            case "pine":
                return 6;
            default:
                return 3;
        }
    }
}


