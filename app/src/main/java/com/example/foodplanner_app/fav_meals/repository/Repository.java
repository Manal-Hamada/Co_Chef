package com.example.foodplanner_app.fav_meals.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner_app.Data_Base.local_db.MealDao;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.details.repository.MealDetailsRepository;

import java.util.List;

public class Repository {

    private static Repository repo = null;
    public MealDao dao;
    LiveData<List<MealDetailsModel>> list;
    private com.example.foodplanner_app.details.repository.MealDetailsRepository mealRepo;


    public Repository(Context c) {
        mealRepo = com.example.foodplanner_app.details.repository.MealDetailsRepository.getInstance(c);
        dao = mealRepo.dao;


    }

    public static Repository getInstance(Context context) {

        if (repo == null)
            repo = new Repository(context);

        return repo;
    }

   /* public LiveData<List<MealDetailsModel>> getAllMealls(Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {
             list= MealDetailsRepository.getInstance(context).dao.getAllMeals();

            }
        }).start();
        return list;
    }*/


}
