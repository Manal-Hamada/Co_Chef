package com.example.foodplanner_app.Data_Base.local_db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.meals.view.AddFavClickListener;

import java.util.List;

public class Local_Data implements AddFavClickListener {

     public MealDao mealDAO;
     public PlandDao planDao;
    private static Local_Data localSource = null;
    private LiveData<List<MealDetailsModel>> storedFavourits;
    private LiveData<List<Db_Model>> storedPlannd;

    private Local_Data(Context context) {
        App_Room_Db appDataBase = App_Room_Db.getInstance(context.getApplicationContext());
        mealDAO = appDataBase.mealDao();
        planDao=appDataBase.plandDao();
        storedFavourits = mealDAO.getAllMeals();
        storedPlannd=planDao.getAllPlandMeals();
    }
    public static Local_Data getInstance(Context context) {
        if (localSource == null)
            localSource = new Local_Data(context);
        return localSource;
    }

    public Local_Data(MealDao mealDAO,PlandDao plandDao) {

        this.mealDAO = mealDAO;
        this.planDao=plandDao;
    }
    @Override
    public void addFavItem(MealDetailsModel model) {

    }
}
