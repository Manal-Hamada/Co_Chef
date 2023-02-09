package com.example.foodplanner_app.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.foodplanner_app.Model;
import com.example.foodplanner_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class DailyPlanFragment extends Fragment implements DetailsOnClickListener {

    RecyclerView recycler;
    DailyMealsAdapter adapter;
    ArrayList<Model> arr;
    TextView topRated;
    ImageView arrwo;
    SearchView search;
    public DailyPlanFragment() {
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
        return inflater.inflate(R.layout.fragment_daily_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arr = new ArrayList<>();
        topRated = requireActivity().findViewById(R.id.top_txt);
        arrwo = getActivity().findViewById(R.id.Arr);
        search = getActivity().findViewById(R.id.search_bar);


        setArr();
        setRecycler();

    }

    public void setRecycler() {

        recycler = requireView().findViewById(R.id.day_list);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manger = new LinearLayoutManager(getActivity());
        manger.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(manger);
        adapter = new DailyMealsAdapter(getActivity(), arr, this);
        recycler.setAdapter(adapter);
    }

    public ArrayList<Model> setArr() {

        arr.add(new Model("Egyptian soap", "12/12/2022", R.drawable.fav_outer_ic, R.drawable.calender, R.drawable.soap));
        arr.add(new Model("Egyptian soap", "12/12/2022", R.drawable.fav_ic, R.drawable.calender, R.drawable.nodels));
        arr.add(new Model("Nodels", "12/12/2022", R.drawable.fav_ic, R.drawable.calender, R.drawable.soap));
        arr.add(new Model("Nodels", "12/12/2022", R.drawable.fav_outer_ic, R.drawable.calender, R.drawable.nodels));

        arr.add(new Model("Soap", "12/12/2022", R.drawable.fav_ic, R.drawable.calender, R.drawable.soap));

        arr.add(new Model("Soap", "12/12/2022", R.drawable.fav_ic, R.drawable.calender, R.drawable.soap));
        arr.add(new Model("Nodels", "12/12/2022", R.drawable.fav_outer_ic, R.drawable.calender, R.drawable.nodels));

        return arr;
    }

    @Override
    public void navToDetails() {
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.pager).setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout).setVisibility(View.GONE);
        search.setVisibility(View.GONE);
        topRated.setVisibility(View.GONE);
        arrwo.setVisibility(View.GONE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new DetailsFragment()).commit();
    }
}

