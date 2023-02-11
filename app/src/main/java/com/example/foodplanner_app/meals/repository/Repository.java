package com.example.foodplanner_app.meals.repository;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;


import com.example.foodplanner_app.meals.model.Meal_Model;
import com.example.foodplanner_app.meals.model.Meals_Response;
import com.example.foodplanner_app.network.ApiClient;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {
    Single<Meals_Response> call;
    public static Repository repo = null;
    ApiClient client;
    ArrayList<Meal_Model> arr= new ArrayList<>();
    ArrayList<Meal_Model> arr2= new ArrayList<>();

    public static MutableLiveData<ArrayList<Meal_Model>> mutableMealsArray= new MutableLiveData<ArrayList<Meal_Model>>();


    private Repository() {
        client = ApiClient.getInstance();
    }

    public static Repository getInstance() {
        if (repo == null)
            repo = new Repository();
        return repo;
    }

    @SuppressLint("CheckResult")
    public void  getMeals(String categoryName) {
        call = client.enqueueCallCategoryMeals(categoryName);

        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item -> {

                    Log.i("TTTTTTT", "getMeals size: "+item.getMeals());
                  //  this.arr.add( item.getMeals());
                    Log.i("TTTTTTT", "getMeals: "+item);
                    mutableMealsArray.setValue(item.getMeals());

                }, (error -> error.toString()));

    }
    @SuppressLint("CheckResult")
    public void  getCountryMeals(String countryName) {
        call = client.enqueueCountryMeals(countryName);

        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item -> {

                    Log.i("TTTTTTT", "getMeals size: "+item.getMeals());
                  //  this.arr.addAll( item.getMeals());
                    Log.i("TTTTTTT", "getMeals: "+item);
                    mutableMealsArray.setValue(item.getMeals());

                }, (error -> error.toString()));

    }
    @SuppressLint("CheckResult")
    public void  getIngredientsMeals(String ingredientName) {
        call = client.enqueueIngredientMeals(ingredientName);

        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item -> {

                    Log.i("TTTTTTT", "getMeals size: "+item.getMeals());
                    //  this.arr.addAll( item.getMeals());
                    Log.i("TTTTTTT", "getMeals: "+item);
                    mutableMealsArray.setValue(item.getMeals());

                }, (error -> error.toString()));

    }

    public ArrayList<Meal_Model> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Meal_Model> arr) {
        this.arr = arr;
    }
}