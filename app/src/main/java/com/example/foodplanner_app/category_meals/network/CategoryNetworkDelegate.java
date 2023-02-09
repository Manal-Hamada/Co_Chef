package com.example.foodplanner_app.category_meals.network;

import com.example.foodplanner_app.category_meals.model.Category_Model;

import java.util.ArrayList;

public interface CategoryNetworkDelegate {
    public void onSuccess(ArrayList<Category_Model> categories);

    public void onFailure(String error);
}
