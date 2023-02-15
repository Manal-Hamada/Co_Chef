package com.example.foodplanner_app.country_meals.network;

import com.example.foodplanner_app.country_meals.model.Country_Model;

import java.util.ArrayList;

public interface Countyry_Network_Delegate {
    public void onSuccess(ArrayList<Country_Model> categories);

    public void onFailure(String error);
}
