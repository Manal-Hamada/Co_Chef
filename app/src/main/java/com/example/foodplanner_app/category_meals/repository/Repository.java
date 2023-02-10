package com.example.foodplanner_app.category_meals.repository;

import android.annotation.SuppressLint;
import android.util.MutableInt;

import androidx.lifecycle.MutableLiveData;

import com.example.foodplanner_app.category_meals.model.Category_Model;
import com.example.foodplanner_app.category_meals.model.Category_Response;
import com.example.foodplanner_app.network.ApiClient;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {

    Single<Category_Response> call;
    public static Repository repo = null;
    ApiClient client;
    ArrayList<Category_Model> arr = new ArrayList<>();
    public static MutableLiveData<ArrayList<Category_Model>> muArray= new MutableLiveData<ArrayList<Category_Model>>();

    private Repository() {
    }

    public static Repository getInstance() {
        if (repo == null)
            repo = new Repository();
        return repo;
    }

    @SuppressLint("CheckResult")
    public void  categories() {
        client = ApiClient.getInstance();
        call = client.enqueueCallCategory();
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item -> {
                    this.arr = item.getCategories();
                    muArray.setValue(arr);
                    System.out.println(arr.size());

                }, (error -> error.toString()));

    }

    public ArrayList<Category_Model> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Category_Model> arr) {
        this.arr = arr;
    }
}
