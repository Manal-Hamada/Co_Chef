package com.example.foodplanner_app.fav_meals.model;

public class Favourite_Model {

    String name;

    public Favourite_Model(String name) {
        this.name = name;
    }

    public Favourite_Model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
