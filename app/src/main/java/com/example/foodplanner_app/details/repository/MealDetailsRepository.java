package com.example.foodplanner_app.details.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.details.model.MealDetailsResponse;

import com.example.foodplanner_app.network.ApiClient;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsRepository {

    Single<MealDetailsResponse> call;
    public static MealDetailsRepository repo = null;
    ApiClient client;
    ArrayList<MealDetailsModel> arr = new ArrayList<>();

    public static MutableLiveData<ArrayList<MealDetailsModel>> mutableMealArray = new MutableLiveData<ArrayList<MealDetailsModel>>();
    public MutableLiveData<ArrayList<MealDetailsModel>> mealArray = new MutableLiveData<ArrayList<MealDetailsModel>>();


    private MealDetailsRepository() {
    }

    public static MealDetailsRepository getInstance() {
        if (repo == null)
            repo = new MealDetailsRepository();
        return repo;
    }

    @SuppressLint("CheckResult")
    public void  getMeals(int id) {
        client = ApiClient.getInstance();
        call = client.enqueueCallMealDetails(id);

        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item -> {

                    Log.i("TTTTTTT", "getMeals size: "+item.getMeals());
                    this.arr.addAll( item.getMeals());
                    Log.i("TTTTTTT", "getMeals: "+item);
                    mutableMealArray.setValue(item.getMeals());
                    mealArray.setValue(item.getMeals());
                    //System.out.println(arr.size());

                }, (error -> error.toString()));

    }

    public ArrayList<MealDetailsModel> getArr() {
        return arr;
    }

    public void setArr(ArrayList<MealDetailsModel> arr) {
        this.arr = arr;
    }
}