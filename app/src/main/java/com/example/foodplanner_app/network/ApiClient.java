package com.example.foodplanner_app.network;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodplanner_app.category_meals.model.Category_Model;
import com.example.foodplanner_app.category_meals.model.Category_Response;
import com.example.foodplanner_app.category_meals.network.CategoryNetworkDelegate;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.details.model.MealDetailsResponse;
import com.example.foodplanner_app.meals.model.Meals_Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // String url="https://www.themealdb.com/api/json/v1/1/categories.php";
    public static final String TAG ="App_Client";
    public static final String BASE_URL ="https://www.themealdb.com/api/json/v1/1/";

    ArrayList<Category_Model> category_model_list;
    public static ApiClient client=null;
    public static Single<Meals_Response> call;
    private static Gson gson;
    private static Retrofit retrofit;
    ApiServices service;


    private ApiClient(){
        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
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

    @SuppressLint("CheckResult")
    public Single<MealDetailsResponse> enqueueCallMealDetails(int id){
        return service.getMealDetails(id);
    }

    @SuppressLint("CheckResult")
    public Single<Meals_Response> enqueueCallCategoryMeals(String categoryName){
        return service.getCategoryMeals(categoryName);
    }

    @SuppressLint("CheckResult")
    public Single<Category_Response> enqueueCallCategory(){
        return service.getAllCtegories();
    }
}
