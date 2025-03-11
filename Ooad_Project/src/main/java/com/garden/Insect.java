package com.garden;

import java.util.ArrayList;

import javafx.scene.image.Image;

public abstract class Insect {
    public static ArrayList<Insect> insects = new ArrayList<>();

    protected String name;
    protected int damage;
    protected Image image;
    int row;
    int col;


    public Insect(String name, Image image, int row, int col) {
        this.name = name;
        this.image = image;
        this.row = row;
        this.col = col;
    }

    public String getName() {
        return name;
    }

    public int getDamage(){
        return damage;
    }

    public Image getImage(){
        return image;
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

    abstract int getDamageByPlant(String plantType);
}

