package com.example.foodplanner_app.Data_Base.local_db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;

import java.util.List;
@Dao
public interface PlandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlandMeal(Db_Model meal);

    @Delete
    void deletePlandMeal(Db_Model model);

    @Query("SELECT * FROM pland_meals")
    LiveData<List<Db_Model>> getAllPlandMeals();
}
