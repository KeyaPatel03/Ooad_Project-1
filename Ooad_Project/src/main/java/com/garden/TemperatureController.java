package com.garden;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TemperatureController {
    public static final int LOWER_TEMPERATURE_THRESHOLD = 40;
    public static final int Higher_TEMPERATURE_THRESHOLD = 120;
    private static final Logger log = LogManager.getLogger(TemperatureController.class);
    private final HeatingController heatingController = new HeatingController();

    private final CoolingController coolingController = new CoolingController();

    public void adjustTemperature(int temperature, List<Plant> plants) {
        log.info("Adjusting temperature to {} °F.", temperature);

        if (temperature < LOWER_TEMPERATURE_THRESHOLD) {
            log.warn("Detected low temperature of {} which is below {}. Activating Garden Heating system", temperature, LOWER_TEMPERATURE_THRESHOLD);
            temperature = heatingController.activateHeating();
        } else if (temperature > 120) {
            log.warn("Detected high temperature of {} which is above {}. Activating Garden Cooling system", temperature, Higher_TEMPERATURE_THRESHOLD);
            temperature = coolingController.activateCooling();
        }
        adjustPlantTemperatures(plants, temperature);
    }

    private void adjustPlantTemperatures(List<Plant> plants, int temperature) {
        for (Plant plant : plants) {
            plant.adjustTemperature(temperature);
        }
    }
}
