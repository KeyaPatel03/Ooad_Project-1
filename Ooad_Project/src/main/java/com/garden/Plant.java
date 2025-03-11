package com.garden;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Plant {
    public static final HashMap<Plant, ImageView> plantImageViewMap = new HashMap<>();
    public static ArrayList<Plant> plantsList = new ArrayList<>();
    public int numPests;// New field to track pesticide application
    int row;
    int col;
    private String name;
    private int waterRequirement;
    private List<String> parasites;
    private int currentWaterLevel;
    private int temperature;
    private boolean isAlive;
    private boolean pesticideApplied;
    private GridPane gardenGrid;
    private int health;


    public Plant(String name, int temperature, int waterRequirement, List<String> parasites, int health) {
        this.name = name;
        this.waterRequirement = waterRequirement;
        this.parasites = parasites;
        this.currentWaterLevel = 0;
        this.temperature = temperature;
        this.isAlive = true;
        this.health = 100;
    }

    public Plant(String name, int temperature, int waterRequirement, List<String> parasites, GridPane gardenGrid) {
        this.name = name;
        this.waterRequirement = waterRequirement;
        this.parasites = parasites;
        this.currentWaterLevel = 0;
        this.temperature = temperature;
        this.isAlive = true;
        this.gardenGrid = gardenGrid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(int waterRequirement) {
        this.waterRequirement = waterRequirement;
    }

    public List<String> getParasites() {
        return parasites;
    }

    public void setParasites(List<String> parasites) {
        this.parasites = parasites;
    }

    public int getCurrentWaterLevel() {
        return currentWaterLevel;
    }

    public void setCurrentWaterLevel(int currentWaterLevel) {
        this.currentWaterLevel = currentWaterLevel;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void water(int amount) {
        currentWaterLevel += amount;
        if (currentWaterLevel > waterRequirement * 2) {
            isAlive = false; // Over-watering kills the plant
        }
    }

    public void adjustTemperature(int temp) {
        temperature = temp;
        if (temperature < 40 || temperature > 120) {
            isAlive = false; // Extreme temperatures kill the plant
        }
    }

    public void infest(String type) {
        if (parasites.contains(type)) {
            isAlive = false; // Infestation kills the plant
        }
    }

    public boolean isPesticideApplied() {
        return pesticideApplied;
    }

    public void setPesticideApplied(boolean pesticideApplied) {
        this.pesticideApplied = pesticideApplied;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getHealth() {
        return health;
    }

    public String getPlantType() {
        return this.name;
    }
    

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            this.isAlive = false; // The plant dies when health hits zero
        }
    }
    
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", currentWaterLevel=" + currentWaterLevel +
                ", waterRequirement=" + waterRequirement +
                ", temperature=" + temperature +
                ", isAlive=" + isAlive +
                '}';
    }
}
