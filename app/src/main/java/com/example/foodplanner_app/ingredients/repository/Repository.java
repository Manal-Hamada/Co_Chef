package com.example.foodplanner_app.ingredients.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.example.foodplanner_app.ingredients.model.IngredientModel;
import com.example.foodplanner_app.ingredients.model.IngredientResponse;
import com.example.foodplanner_app.network.ApiClient;

import java.util.ArrayList;
import com.example.foodplanner_app.ingredients.repository.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {
    Single<IngredientResponse> call;
    public static Repository repo = null;
    ApiClient client;
    ArrayList<IngredientModel> arr = new ArrayList<>();
    public static MutableLiveData<ArrayList<IngredientModel>> muArray= new MutableLiveData<ArrayList<IngredientModel>>();

    private Repository() {
    }
    public static Repository getInstance() {
        if (repo == null)
            repo = new Repository();
        return repo;
    }

    @SuppressLint("CheckResult")
    public void  ingredients() {
        client = ApiClient.getInstance();
        call = client.ingredients();
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item -> {
                    // this.arr = item.getCategories();
                    muArray.setValue(item.getMeals());
                    System.out.println(arr.size());

                }, (error -> error.toString()));

    }

    public ArrayList<IngredientModel> getArr() {
        return arr;
    }

    public void setArr(ArrayList<IngredientModel> arr) {
        this.arr = arr;
    }
}
