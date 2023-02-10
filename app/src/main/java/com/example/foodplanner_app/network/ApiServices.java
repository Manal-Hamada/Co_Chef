package com.example.foodplanner_app.network;

import com.example.foodplanner_app.category_meals.model.Category_Response;
import com.example.foodplanner_app.meals.model.Meals_Response;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("categories.php")
    Single<Category_Response> getAllCtegories();

    @GET("list.php?c=list")
    Single<Category_Response> getAllCtegorieMeals();

    @GET("filter.php")
    Single<Meals_Response> getCategoryMeals(@Query("c") String categoryName);

}
