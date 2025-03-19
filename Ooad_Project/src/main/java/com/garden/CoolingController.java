package com.garden;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoolingController {
    Logger log = LogManager.getLogger(CoolingController.class);
    private static final int MINIMUM_SAFE_TEMPERATURE = 50; // Sets a minimum safe temperature for plants

    public int activateCooling() {
        log.info("Activating cooling system to reduce temperature to {} °F.", MINIMUM_SAFE_TEMPERATURE);
        return MINIMUM_SAFE_TEMPERATURE;
    }
}