package com.example.foodplanner_app.details.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplanner_app.Data_Base.local_db.Local_Data;
import com.example.foodplanner_app.Data_Base.local_db.MealDao;
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
    public MealDao dao;
    public Local_Data data;

    public static MutableLiveData<ArrayList<MealDetailsModel>> mutableMealArray = new MutableLiveData<ArrayList<MealDetailsModel>>();
    public MutableLiveData<ArrayList<MealDetailsModel>> mealArray = new MutableLiveData<ArrayList<MealDetailsModel>>();


    private MealDetailsRepository(Context context) {
        data=Local_Data.getInstance(context);
        dao=data.mealDAO;
    }
    public static MealDetailsRepository getInstance(Context context) {
        if (repo == null)
            repo = new MealDetailsRepository(context);
        return repo;
    }
    @SuppressLint("CheckResult")
    public void  getMeals(int id) {
        client = ApiClient.getInstance();
        call = client.enqueueCallMealDetails(id);

        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item -> {
                    mutableMealArray.setValue(item.getMeals());
                    mealArray.setValue(item.getMeals());
                }, (error -> error.toString()));

    }

    public ArrayList<MealDetailsModel> getArr() {
        return arr;
    }

    public void setArr(ArrayList<MealDetailsModel> arr) {
        this.arr = arr;
    }
}