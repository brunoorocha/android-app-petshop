package com.example.brunoorocha.petshop;

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

    private String name, species;
    private Foods foods;

    public Pets(String name, String species, Foods foods) {
        this.name = name;
        this.species = species;
        this.foods = foods;
    }

    public Pets() {}



}