package com.example.foodplanner_app.network;

import android.annotation.SuppressLint;

import com.example.foodplanner_app.category_meals.model.Category_Response;
import com.example.foodplanner_app.category_meals.network.CategoryNetworkDelegate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public static ApiClient client=null;
    public static Single<Category_Response> call;

    private ApiClient(){}
    public static ApiClient getInstance(){
        if(client == null)
            client = new ApiClient();

        return client;
    }

    @SuppressLint("CheckResult")
    public void enqueueCall(CategoryNetworkDelegate networkDelegate){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        ApiServices service =retrofit.create(ApiServices.class);

        call = service.getAllCtegories();
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(item-> networkDelegate.onSuccess(item.getCategories()),
                        (error -> error.toString()));

    }
}
