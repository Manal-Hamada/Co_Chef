package com.example.foodplanner_app.fav_meals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.fav_meals.model.Favourite_Model;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;

import java.util.ArrayList;

public class FavouritFragment extends Fragment  {

    RecyclerView recycler;
    FavouriteAdapter adapter;
    ArrayList<Favourite_Model> arr;
    SearchView search;
    Db_Repository repo;
    Favourite_Model model;

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
        repo=Db_Repository.getInstance();

        Log.i("lllllll", ""+arr.size());
        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.GONE);
        repo.getAllFavData();
        setRecycler(view);
    }
    public void setRecycler(View v){
        recycler= v.findViewById(R.id.fav_list);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manger = new LinearLayoutManager(getActivity());
        manger.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(manger);
        adapter=new FavouriteAdapter(getActivity(),arr);
    }

   /* @Override
    public void getfavMeal(Favourite_Model model) {
     /*   arr.add(model);
        adapter.setList(arr);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }*/


}