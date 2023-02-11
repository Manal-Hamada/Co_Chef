package com.example.foodplanner_app.details.model;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealDetailsResponse {

    @SerializedName("meals")
    @Expose
    private ArrayList<MealDetailsModel> meals;

    public ArrayList<MealDetailsModel> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealDetailsModel> meals) {
        this.meals = meals;
    }

}