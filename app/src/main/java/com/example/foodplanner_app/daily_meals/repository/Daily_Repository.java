package com.example.foodplanner_app.daily_meals.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner_app.Data_Base.local_db.Local_Data;
import com.example.foodplanner_app.Data_Base.local_db.MealDao;
import com.example.foodplanner_app.Data_Base.local_db.PlandDao;
import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.details.repository.MealDetailsRepository;
import com.example.foodplanner_app.fav_meals.repository.Repository;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;

import java.util.List;

public class Daily_Repository {

    private static Daily_Repository repo = null;
    public PlandDao dao;
    public Db_Repository dbRepo;
    Daily_Repository mealDbRepo;
    public LiveData<List<Db_Model>> list;
    public  Local_Data data;

    public Daily_Repository(Context c) {
        data= Local_Data.getInstance(c);
        Log.i("m vkjgr vg", "Daily_Repository: "+data);
        dbRepo=Db_Repository.getInstance();
        Log.i("m vkjgr vg", "Daily_Repository: "+dbRepo);
        dao=data.planDao;
        Log.i("m vkjgr vg", "Daily_Repository: "+dao);
    }

    public static Daily_Repository getInstance(Context context) {

        if (repo == null)
            repo = new Daily_Repository(context);

        return repo;
    }

   public LiveData<List<Db_Model>> getAllMealls(Context context, String date) {
        //dbRepo = Db_Repository.getInstance();

       //TODO ana hena
       Log.i("vjknjvndkjgn", "getAllMealls: "+date);

        repo.dbRepo.getAllPlannedData(date,context);

         return repo.dao.getAllPlandMeals(date);
    }


}
