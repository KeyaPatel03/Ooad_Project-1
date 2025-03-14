package com.garden;

import java.util.Objects;

import javafx.scene.image.Image;

public class Caterpillar extends Insect {
        private static final Image caterpillar = new Image(Objects.requireNonNull(Mites.class.getResourceAsStream("/images/caterpillar.png")));

    public Caterpillar() {
        super("Caterpillar", caterpillar, 0, 0);
    }

    public int getDamageByPlant(String plantType) {
        switch (plantType.toLowerCase()) {
            case "rose":
                return 7;
            case "maple":
                return 5;
            case "orange":
                return 4;
            case "pine":
                return 6;
            case "lemon":
                return 6;
            default:
                return 3;
        }
    }
}


