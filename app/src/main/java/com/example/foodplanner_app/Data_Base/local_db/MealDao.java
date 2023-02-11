package com.example.foodplanner_app.Data_Base.local_db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.foodplanner_app.network.remoteSource.Db_Model;
import java.util.List;

@Dao
public interface MealDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertMeal(Db_Model meal);

    @Delete
    void deleteMeal(Db_Model meal);

    @Query("SELECT * FROM meals")
    LiveData<List<Db_Model>> getAllProducts();
}
