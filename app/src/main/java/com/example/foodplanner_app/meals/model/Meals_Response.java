package com.example.foodplanner_app.meals.model;

import java.util.ArrayList;

public class Meals_Response {
    ArrayList<Meal_Model> meals;

    public Meals_Response(ArrayList<Meal_Model> meals) {
        this.meals = meals;
    }

    public ArrayList<Meal_Model> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal_Model> meals) {
        this.meals = meals;
    }
}
