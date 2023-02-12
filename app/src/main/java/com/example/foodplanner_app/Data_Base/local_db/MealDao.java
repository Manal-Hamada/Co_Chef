package com.example.foodplanner_app.Data_Base.local_db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner_app.details.model.MealDetailsModel;

import java.util.List;

@Dao
public interface MealDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertMeal(MealDetailsModel meal);

    @Delete
    void deleteMeal(MealDetailsModel model);

    @Query("SELECT * FROM meals")
    LiveData<List<MealDetailsModel>> getAllMeals();
}
