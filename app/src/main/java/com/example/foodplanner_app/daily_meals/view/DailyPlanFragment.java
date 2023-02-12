package com.example.foodplanner_app.daily_meals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.view.DetailsFragment;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.models.Model;

import java.util.ArrayList;

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
        search = getActivity().findViewById(R.id.search_bar);
      //  setRecycler();
    }

    public void setRecycler() {

        recycler = requireView().findViewById(R.id.day_list);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manger=new LinearLayoutManager(getActivity());
        manger.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(manger);
        adapter = new DailyMealsAdapter(getActivity(), arr, this);
        recycler.setAdapter(adapter);
    }



    @Override
    public void navToDetails(int id) {
            getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.pager).setVisibility(View.GONE);
            getActivity().findViewById(R.id.tablayout).setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            topRated.setVisibility(View.GONE);
            arrwo.setVisibility(View.GONE);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new DetailsFragment()).commit();
    }
}

