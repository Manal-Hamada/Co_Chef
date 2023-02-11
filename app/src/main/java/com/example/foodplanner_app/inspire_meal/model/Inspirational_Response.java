package com.example.foodplanner_app.inspire_meal.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Inspirational_Response {

        @SerializedName("meals")
        private ArrayList<Inspirational_Model> meals;

        public ArrayList<Inspirational_Model> getMeals() {
            return meals;
        }

        public void setMeals( ArrayList<Inspirational_Model> meals) {
            this.meals = meals;
        }


}
