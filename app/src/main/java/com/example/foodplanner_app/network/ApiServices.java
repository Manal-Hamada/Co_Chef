package com.example.foodplanner_app.network;

import com.example.foodplanner_app.category_meals.model.Category_Response;
import com.example.foodplanner_app.country_meals.model.Country_Model;
import com.example.foodplanner_app.country_meals.model.Country_Response;
import com.example.foodplanner_app.details.model.MealDetailsResponse;
import com.example.foodplanner_app.ingredients.model.IngredientResponse;
import com.example.foodplanner_app.inspire_meal.model.Inspirational_Response;
import com.example.foodplanner_app.meals.model.Meals_Response;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("categories.php")
    Single<Category_Response> getAllCtegories();

    @GET("list.php?a=list")
    Single<Country_Response> getAllCountries();

    @GET("list.php?i=list")
    Single<IngredientResponse> getAllIngredients();

    @GET("random.phpt")
    Single<Inspirational_Response> getInspirationalMeal();

    @GET("filter.php")
    Single<Meals_Response> getCategoryMeals(@Query ("c") String categoryName);
    @GET("filter.php")
    Single<Meals_Response> getCaountryMeals(@Query ("a") String countryName);
    @GET("filter.php")
    Single<Meals_Response> getIngredientMeals(@Query ("i") String IngredientName);
    @GET("lookup.php")
    Single<MealDetailsResponse> getMealDetails(@Query("i") int id);
}
