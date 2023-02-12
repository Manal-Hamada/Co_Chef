package com.example.foodplanner_app.network.remoteSource;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.daily_meals.repository.Daily_Repository;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.fav_meals.repository.Repository;
import com.example.foodplanner_app.fav_meals.model.Favourite_Model;
import com.example.foodplanner_app.fav_meals.view.Fav_Meal_Interface;
import com.example.foodplanner_app.models.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.Date;


public class Db_Repository {

    public static Db_Repository repo = null;
    String dRef;
    private Fire_Db_Creation db;
    private DocumentReference docRef;
    Favourite_Model model1;
    Fav_Meal_Interface fav_meal_interface;
    FirebaseDatabase database;
    DatabaseReference reference;

    public Fire_Db_Creation getDb() {

        return db;
    }

    public void setDb(Fire_Db_Creation db) {
        this.db = db;
    }

    public Db_Repository(Fav_Meal_Interface fav_meal_interface) {
        db = Fire_Db_Creation.getInnstance();
        this.fav_meal_interface = fav_meal_interface;
    }

    public static Db_Repository getInstance() {
        if (repo == null)
            repo = new Db_Repository();
        return repo;
    }

    public Db_Repository() {
        db = Fire_Db_Creation.getInnstance();
        this.fav_meal_interface = fav_meal_interface;
    }

    public void addFavouriteMeal(MealDetailsModel user) {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(FirebaseAuth.getInstance().getUid());
        reference.child(Constants.REF_FAV).child(user.getIdMeal()).setValue(user);

    }

    public void unFavMeal(MealDetailsModel user) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(FirebaseAuth.getInstance().getUid());
        reference.child(Constants.REF_FAV).child(user.getIdMeal()).removeValue();
    }


    public void getAllFavData(Context context) {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(FirebaseAuth.getInstance().getUid());
        reference.child(Constants.REF_FAV).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("tgnkjrtnbk", "onDataChange: " + snapshot.getChildrenCount());
                if (snapshot.hasChildren()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        MealDetailsModel model = dataSnapshot.getValue(MealDetailsModel.class);
                        insert(model, context);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void insert(MealDetailsModel model, Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Repository repo = Repository.getInstance(context);
                repo.dao.insertMeal(model);
            }
        }).start();
    }

    public void addPlannedMeal(Db_Model user, String date) {

        Log.i("nktrnvkgk", "addPlannedMeal: " + date);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(date);
        reference.child(Constants.REF_WEEK).child(FirebaseAuth.getInstance().getUid())
                .child(user.getIdMeal()).setValue(user);
    }

    public void getAllPlannedData(String date, Context context) {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(date);
        reference.child(Constants.REF_WEEK)
                .child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.i("tgnkjrtnbk", "onDataChange: " + snapshot.getChildrenCount());
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Db_Model model = dataSnapshot.getValue(Db_Model.class);
                                addPlan(model, context);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void addPlan(Db_Model model, Context context) {
        Daily_Repository repo = Daily_Repository.getInstance(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                repo.dbRepo.addPlan(model,context);
            }
        }).start();
    }

}