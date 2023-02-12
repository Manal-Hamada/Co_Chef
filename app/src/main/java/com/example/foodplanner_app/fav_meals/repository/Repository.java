package com.example.foodplanner_app.fav_meals.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner_app.Data_Base.local_db.MealDao;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.details.repository.MealDetailsRepository;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;

import java.util.List;

public class Repository {

    private static Repository repo = null;
    public MealDao dao;
    public Db_Repository dbRepo;
    MealDetailsRepository mealDbRepo;
     public LiveData<List<MealDetailsModel>> list;



    public Repository(Context c) {
        mealDbRepo=MealDetailsRepository.getInstance(c);
        dao=mealDbRepo.dao;
        dbRepo=Db_Repository.getInstance();
    }

    public static Repository getInstance(Context context) {

        if (repo == null)
            repo = new Repository(context);

        return repo;
    }

   public LiveData<List<MealDetailsModel>> getAllMealls(Context context) {
         dbRepo=Db_Repository.getInstance();
       dbRepo.getAllFavData(context);

      return mealDbRepo.dao.getAllMeals();
    }


}
