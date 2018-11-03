package com.example.brunoorocha.petshop.model;

import com.example.brunoorocha.petshop.model.Foods;

public class Pets {
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

    private String name, species, price;
    private Foods foods;

    public Pets(String name, String species, Foods foods) {
        this.name = name;
        this.species = species;
        this.foods = foods;
    }

    public Pets() {}

}