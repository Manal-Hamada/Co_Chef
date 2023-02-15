package com.example.foodplanner_app.Data_Base.local_db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.details.model.MealDetailsModel;

@Database(entities = {MealDetailsModel.class,Db_Model.class}, version = 2)
public abstract class App_Room_Db extends RoomDatabase {

    private static App_Room_Db appDataBase = null;
    private static  String  DATA_BASE_NAME="meals";
    public abstract MealDao mealDao();
    public abstract PlandDao plandDao();

    public synchronized static App_Room_Db getInstance(Context context) {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(context.getApplicationContext(), App_Room_Db.class,DATA_BASE_NAME).allowMainThreadQueries()
                     .fallbackToDestructiveMigration()  .build();
        }
        return appDataBase;
    }
    }


