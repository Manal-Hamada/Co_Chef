package com.example.foodplanner_app.ingredients.view;

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

public class IngredienstFr extends Fragment {
    TextView topRated;
    ImageView arrwo;



    public IngredienstFr() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredienst, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     /*   topRated=view.findViewById(R.id.top_txt);
        arrwo=view.findViewById(R.id.Arr);
        topRated.setVisibility(View.VISIBLE);
        arrwo.setVisibility(View.VISIBLE);*/

}
}