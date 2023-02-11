package com.example.foodplanner_app.network;

import android.annotation.SuppressLint;

import com.example.foodplanner_app.category_meals.model.Category_Response;
import com.example.foodplanner_app.country_meals.model.Country_Model;
import com.example.foodplanner_app.country_meals.model.Country_Response;
import com.example.foodplanner_app.ingredients.model.IngredientResponse;
import com.example.foodplanner_app.inspire_meal.model.Inspirational_Response;
import com.example.foodplanner_app.meals.model.Meals_Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("CheckResult")
public class ApiClient {

    // String url="https://www.themealdb.com/api/json/v1/1/categories.php";
    public static final String TAG ="App_Client";
    public static final String BASE_URL ="https://www.themealdb.com/api/json/v1/1/";

    public static ApiClient client=null;
    ApiServices service;

    private ApiClient(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        service =retrofit.create(ApiServices.class);
    }
    public static ApiClient getInstance(){
        if(client == null)
            client = new ApiClient();

        return client;
    }

    public Single<Category_Response> category(){
        return service.getAllCtegories();

    }
    public Single<Country_Response> country(){
        return service.getAllCountries();

    }
    public Single<IngredientResponse> ingredients(){
        return service.getAllIngredients();

    }
    public Single<Inspirational_Response> Inspirational(){
        return service.getInspirationalMeal();

    }
    @SuppressLint("CheckResult")
    public Single<Meals_Response> enqueueCallCategoryMeals(String categoryName){
        return service.getCategoryMeals(categoryName);
    }
    @SuppressLint("CheckResult")
    public Single<Meals_Response> enqueueCountryMeals(String countryName){
        return service.getCaountryMeals(countryName);
    }
    @SuppressLint("CheckResult")
    public Single<Meals_Response> enqueueIngredientMeals(String ingredientName){
        return service.getIngredientMeals(ingredientName);
    }

}
