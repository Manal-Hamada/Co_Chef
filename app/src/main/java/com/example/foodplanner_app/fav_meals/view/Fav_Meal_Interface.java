package com.example.foodplanner_app.fav_meals.view;

import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.fav_meals.model.Favourite_Model;

public interface Fav_Meal_Interface {

    public void deleteMeal(MealDetailsModel meal);
    public void addFavItem(MealDetailsModel model);

}
