package com.garden;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GardenSimulator {
    private static final Logger log = LogManager.getLogger(GardenSimulator.class);
    private static int dayCount = 0;

    public static void main(String[] args) {

        GardenSimulatorAPI gardenAPI = new GardenSimulatorAPI();

        // beginning of the simulation
        gardenAPI.initializeGarden(); // This marks the beginning of the clock
        Map<String, Object> initialPlantDetails = gardenAPI.getPlants();

        gardenAPI.rain(2);
        gardenAPI.temperature(50);
        sleepOneHour(); // end of first day

        // beginning of the second day
        gardenAPI.temperature(60);
        gardenAPI.parasites("Aphids");
        sleepOneHour(); // end of second day

        // beginning of the third day
        gardenAPI.temperature(45);
        gardenAPI.parasites("Mites");
        sleepOneHour(); // end of third day

        // beginning of the fourth day
        gardenAPI.temperature(25);
        gardenAPI.parasites("caterpillar");
        sleepOneHour(); // end of fourth day

        // beginning of the fifth day
        gardenAPI.temperature(30);
        gardenAPI.parasites("beetles");
        sleepOneHour(); // end of fifth \day

        // .... after 24 days

        gardenAPI.getStatus();

    }

    public static void sleepOneHour() {
        try {
            Thread.sleep(3600); // Sleep for one hour
            dayCount += 1;
            log.info("---------------- End of Day {} of Garden simulation -----------------", dayCount);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
    }
}
