package com.example.foodplanner_app.country_meals.repository;

import android.annotation.SuppressLint;
import androidx.lifecycle.MutableLiveData;
import com.example.foodplanner_app.country_meals.model.Country_Model;
import com.example.foodplanner_app.country_meals.model.Country_Response;
import com.example.foodplanner_app.network.ApiClient;
import java.util.ArrayList;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {
    Single<Country_Response> call;
    public static Repository repo = null;
    ApiClient client;
    ArrayList<Country_Model> arr = new ArrayList<>();
    public static MutableLiveData<ArrayList<Country_Model>> muArray= new MutableLiveData<ArrayList<Country_Model>>();

    private Repository() {
    }
    public static Repository getInstance() {
        if (repo == null)
            repo = new Repository();
        return repo;
    }

    @SuppressLint("CheckResult")
    public void  countries() {
        client = ApiClient.getInstance();
        call = client.country();
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item -> {
                   this.arr = item.getMeals();
                    muArray.setValue(item.getMeals());
                   // System.out.println(arr.size());

                }, (error -> error.toString()));

    }


}
