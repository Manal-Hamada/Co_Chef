package com.example.foodplanner_app.Data_Base.local_db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner_app.network.remoteSource.Db_Model;

@Database(entities = {Db_Model.class}, version = 1)
public abstract class App_Room_Db extends RoomDatabase {

    private static App_Room_Db appDataBase = null;
    private static  String  DATA_BASE_NAME="meals";
    public abstract MealDao mealDao();

    public synchronized static App_Room_Db getInstance(Context context) {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(context.getApplicationContext(), App_Room_Db.class,DATA_BASE_NAME).build();
        }
        return appDataBase;
    }
}
