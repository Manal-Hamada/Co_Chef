package com.example.foodplanner_app.network.remoteSource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.fav_meals.model.Favourite_Model;
import com.example.foodplanner_app.fav_meals.view.Fav_Meal_Interface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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

        db.database.collection("MealData").add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAError", "Error adding document", e);
                        // dialog
                    }
                });
        Map<String, Object> user2 = new HashMap<>();
        user2.put("idUser", FirebaseAuth.getInstance().getUid());
        user2.put("isFav", "true");
        db.database.collection("MealData").add(user2);
    }

    public Favourite_Model getFavouriteMeals() {
        DocumentReference docRef = db.database.collection("test").document("EdFcmoXro3muNyU65E4J");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                model1 = documentSnapshot.toObject(Favourite_Model.class);
                // fav_meal_interface.getfavMeal(model1);
                Log.i("TAAAAAAG", "onSuccess: " + model1.getName());
            }
        });
        return model1;
    }

    public void getAllFavData() {
        db.database.collection("test")
                .whereEqualTo("id", FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("tassssssssk", document.getId() + " => " + document.getData());
                                ArrayList<Favourite_Model> arr = new ArrayList<>();
                                arr.add(new Favourite_Model(document.getData().get("name").
                                        toString(), document.getData().get("id").toString()));
                                Log.i("arrrr", " " + arr.size());
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}
