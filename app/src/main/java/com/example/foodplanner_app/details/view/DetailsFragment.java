package com.example.foodplanner_app.details.view;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailsFragment extends Fragment {

    TextView topRated;
    ImageView arrwo;
    SimpleDateFormat simpleDateFormat;
    DatePickerDialog datePickerDialog;
    FloatingActionButton addMeal;

    public DetailsFragment() {
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
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addMeal=view.findViewById(R.id.add_meal_fab);
        simpleDateFormat = new SimpleDateFormat("yyy-MM-ddd");
       // pickDateTime();
        setAddBtnAction();
       /* topRated=view.findViewById(R.id.top_txt);
        arrwo=view.findViewById(R.id.Arr);
        topRated.setVisibility(View.VISIBLE);
        arrwo.setVisibility(View.VISIBLE);*/

    }
    private void pickDateTime(){
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date.set(year, month, dayOfMonth);

            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();
    }
    public void setAddBtnAction(){
        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateTime();
            }
        });

    }
}

