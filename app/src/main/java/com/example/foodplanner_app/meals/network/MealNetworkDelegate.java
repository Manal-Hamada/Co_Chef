package com.example.foodplanner_app.meals.network;

import com.example.foodplanner_app.meals.model.Meal_Model;

import java.util.ArrayList;

public interface MealNetworkDelegate {

    public void onSuccess(ArrayList<Meal_Model> meals);

    public void onFailure(String error);
}
