package com.example.foodplanner_app.country_meals.model;

import com.example.foodplanner_app.category_meals.model.Category_Model;

import java.util.ArrayList;

public class Country_Response {
    ArrayList<Country_Model> meals;

    public Country_Response(ArrayList<Country_Model> meals) {
        this.meals = meals;
    }

    public ArrayList<Country_Model> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Country_Model> meals) {
        this.meals = meals;
    }
}
