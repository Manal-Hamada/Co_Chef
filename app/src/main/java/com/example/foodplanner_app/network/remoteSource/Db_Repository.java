package com.example.foodplanner_app.network.remoteSource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner_app.fav_meals.model.Favourite_Model;
import com.example.foodplanner_app.fav_meals.view.Fav_Meal_Interface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Db_Repository {

    public static Db_Repository repo = null;
    private Fire_Db_Creation db;
    private DocumentReference docRef;
    Favourite_Model model1;
    Fav_Meal_Interface fav_meal_interface;

    public Fire_Db_Creation getDb() {
        return db;
    }

    public void setDb(Fire_Db_Creation db) {
        this.db = db;
    }

    public Db_Repository( Fav_Meal_Interface fav_meal_interface) {
        db = Fire_Db_Creation.getInnstance();
        this.fav_meal_interface=fav_meal_interface;
    }

    public static Db_Repository getInstance(Fav_Meal_Interface fav_meal_interface) {
        if (repo == null)
            repo = new Db_Repository(fav_meal_interface);
        return repo;
    }

    public void addFavouriteMeal(String mealName, String img) {
        Log.i("TEST", "add fav");
        Map<String, Object> user = new HashMap<>();
        user.put("name", mealName);
        db.database.collection("test").add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
    }

    public Favourite_Model getFavouriteMeals() {
        DocumentReference docRef = db.database.collection("test").document("Er4HKPqItaFzI696tyMs");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                model1 = documentSnapshot.toObject(Favourite_Model.class);
                fav_meal_interface.getfavMeal(model1);
                Log.i("TAAAAAAG", "onSuccess: " + model1.getName());
            }
        });
        return model1;
    }

    public void getAllFavData() {
        db.database.collection("test")
                // .whereEqualTo("capital", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

}
