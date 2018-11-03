package com.example.brunoorocha.petshop.model;

import com.example.brunoorocha.petshop.model.Foods;

import java.io.Serializable;

public class Pets implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Foods getFoods() {
        return foods;
    }

    public void setFoods(Foods foods) {
        this.foods = foods;
    }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public int getImageResourceId() { return imageResourceId; }

    public void setImageResourceId(int imageResourceId) { this.imageResourceId = imageResourceId; }

    private String name, species, price;
    private int imageResourceId;
    private Foods foods;

    public Pets(String name, String species, String price, int imageResourceId, Foods foods) {
        this.name = name;
        this.species = species;
        this.price = price;
        this.imageResourceId = imageResourceId;
        this.foods = foods;
    }

    public Pets() {}

}