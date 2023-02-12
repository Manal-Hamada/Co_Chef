package com.example.foodplanner_app.fav_meals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.fav_meals.repository.Repository;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;

import java.util.ArrayList;
import java.util.List;

public class FavouritFragment extends Fragment implements DetailsOnClickListener,Fav_Meal_Interface {

    RecyclerView recycler;
    FavouriteAdapter adapter;
    ArrayList<MealDetailsModel> arr;
    SearchView search;
    Db_Repository repo;
    Repository favRepo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public FavouritFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_favourit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arr=new ArrayList<>();
       // repo=Db_Repository.getInstance();
        Log.i("lllllll", ""+arr.size());
        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.GONE);
        setRecycler(view);
        favRepo= Repository.getInstance(getActivity());
       /* favRepo.getAllMealls(getContext()).observe(getActivity(), new Observer<List<MealDetailsModel>>() {
            @Override
            public void onChanged(List<MealDetailsModel> mealDetailsModels) {
                arr = (ArrayList<MealDetailsModel>) mealDetailsModels;
                adapter.setList(arr);
                recycler.setAdapter(adapter);
            }
        });*/
        favRepo.dao.getAllMeals().observe(getViewLifecycleOwner(), new Observer<List<MealDetailsModel>>() {
            @Override
            public void onChanged(List<MealDetailsModel> mealDetailsModels) {
                arr = (ArrayList<MealDetailsModel>) mealDetailsModels;
                adapter.setList(arr);
                recycler.setAdapter(adapter);
            }
        });

    }

    public void setRecycler(View v){
        recycler= v.findViewById(R.id.fav_list);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manger = new LinearLayoutManager(getActivity());
        manger.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(manger);
        adapter=new FavouriteAdapter(getActivity(),arr,this,this);
    }

    @Override
    public void navToDetails(int id) {

    }

    @Override
    public void deleteMeal(MealDetailsModel meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                favRepo.dao.deleteMeal(meal);
            }
        }).start();
    }
}