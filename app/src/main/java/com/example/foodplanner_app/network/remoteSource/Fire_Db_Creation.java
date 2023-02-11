package com.example.foodplanner_app.network.remoteSource;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Fire_Db_Creation {

    public FirebaseFirestore database=null;
    public static Fire_Db_Creation db;

    public Fire_Db_Creation() {
        database=FirebaseFirestore.getInstance();
    }

    public static Fire_Db_Creation getInnstance(){
        if (db==null)
            db=new Fire_Db_Creation();
        return  db;
    }

}
