package com.example.foodplanner_app.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner_app.R;


public class Search_Fragment extends Fragment {
    TextView meal_name,category,country;
    ImageView meal_img;
    public Search_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void init(View v){
        meal_name=v.findViewById(R.id.ins_meal_name);
        category=v.findViewById(R.id.ins_category);
        country=v.findViewById(R.id.ins_country);
        meal_img=v.findViewById(R.id.ins_meal_img);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);



    }


}