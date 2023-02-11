package com.example.foodplanner_app.network.remoteSource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.fav_meals.model.Favourite_Model;
import com.example.foodplanner_app.fav_meals.view.Fav_Meal_Interface;
import com.example.foodplanner_app.models.Constants;
import com.example.foodplanner_app.models.MealDetailsWithUserId;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

//        reference.child(user.getIdMeal()).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//            @Override
//            public void onSuccess(DataSnapshot dataSnapshot) {
//
//            }
//        });


    }
    public void unFavMeal(MealDetailsModel user){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(FirebaseAuth.getInstance().getUid());
        reference.child(Constants.REF_FAV).child(user.getIdMeal()).removeValue();
    }



    public void getAllFavData() {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(FirebaseAuth.getInstance().getUid());
        reference.child(Constants.REF_FAV).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("tgnkjrtnbk", "onDataChange: "+snapshot.getChildrenCount());
                if(snapshot.hasChildren())
                {
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Favourite_Model model = dataSnapshot.getValue(Favourite_Model.class);
                        //room insert and observe
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//
//        db.database.collection("test")
//                .whereEqualTo("id", FirebaseAuth.getInstance().getUid())
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("tassssssssk", document.getId() + " => " + document.getData());
//                                ArrayList<Favourite_Model> arr = new ArrayList<>();
//                                arr.add(new Favourite_Model(document.getData().get("name").
//                                        toString(), document.getData().get("id").toString()));
//                                Log.i("arrrr", " " + arr.size());
//                            }
//                        } else {
//                            Log.d("TAG", "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
    }

}
