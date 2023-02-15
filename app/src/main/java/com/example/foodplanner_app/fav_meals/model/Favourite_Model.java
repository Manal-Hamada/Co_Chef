package com.example.foodplanner_app.fav_meals.model;

public class Favourite_Model {

    String name,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Favourite_Model(String name, String id) {
        this.name = name;
        this.id = id;
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
