package com.example.foodplanner_app.inspire_meal.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.example.foodplanner_app.country_meals.model.Country_Model;
import com.example.foodplanner_app.country_meals.model.Country_Response;
import com.example.foodplanner_app.inspire_meal.model.Inspirational_Model;
import com.example.foodplanner_app.inspire_meal.model.Inspirational_Response;
import com.example.foodplanner_app.network.ApiClient;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.example.foodplanner_app.inspire_meal.repository.Repository;
public class Repository {
    Single<Inspirational_Response> call;
    public static Repository repo = null;
    ApiClient client;
    ArrayList<Inspirational_Model> arr = new ArrayList<>();
    public static MutableLiveData<ArrayList<Inspirational_Model>> muArray= new MutableLiveData<ArrayList<Inspirational_Model>>();

    private Repository() {
    }
    public static Repository getInstance() {
        if (repo == null)
            repo = new Repository();
        return repo;
    }

    @SuppressLint("CheckResult")
    public void  inspirationalMeal() {
        client = ApiClient.getInstance();
        call = client.Inspirational();
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item -> {
                    this.arr = item.getMeals();
                    System.out.println("ffffffffffffffff"+item.getMeals().size());
                    muArray.setValue(item.getMeals());

                }, (error -> error.toString()));

    }
}
