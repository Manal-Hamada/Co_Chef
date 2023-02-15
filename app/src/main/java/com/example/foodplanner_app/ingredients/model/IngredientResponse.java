package com.example.foodplanner_app.ingredients.model;

import com.example.foodplanner_app.category_meals.model.Category_Model;

import java.util.ArrayList;

public class IngredientResponse {
    ArrayList<IngredientModel> meals;

    public IngredientResponse(ArrayList<IngredientModel> meals) {
        this.meals = meals;
    }

    public ArrayList<IngredientModel> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<IngredientModel> meals) {
        this.meals = meals;
    }
}
