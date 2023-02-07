package com.example.foodplanner_app.view;

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

import com.example.foodplanner_app.Model;
import com.example.foodplanner_app.R;

import java.util.ArrayList;

public class FavouritFragment extends Fragment {

    RecyclerView recycler;
    FavouriteAdapter adapter;
    ArrayList<Model> arr;
    TextView topRated;
    ImageView arrwo;
    SearchView search;

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

        topRated=getActivity().findViewById(R.id.top_txt);
        arrwo=getActivity().findViewById(R.id.Arr);
        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.GONE);
        topRated.setVisibility(View.GONE);
        arrwo.setVisibility(View.GONE);

        setArr();
        setRecycler();
    }
    public void setRecycler(){

        recycler= requireView().findViewById(R.id.fav_list);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manger = new LinearLayoutManager(getActivity());
        manger.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(manger);
        adapter=new FavouriteAdapter(getActivity(),arr);
        recycler.setAdapter(adapter);
    }
    public ArrayList<Model> setArr(){

        arr.add(new Model("Egyptian soap",R.drawable.fav_outer_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Egyptian soap",R.drawable.fav_ic,R.drawable.calender,R.drawable.nodels));
        arr.add(new Model("Nodels",R.drawable.fav_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Nodels",R.drawable.fav_outer_ic,R.drawable.calender,R.drawable.nodels));
        arr.add(new Model("shesh",R.drawable.fav_ic,R.drawable.calender,R.drawable.shesh_dish));
        arr.add(new Model("Soap",R.drawable.fav_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("shesh",R.drawable.fav_outer_ic,R.drawable.calender,R.drawable.shesh_dish));
        arr.add(new Model("Soap",R.drawable.fav_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Nodels",R.drawable.fav_outer_ic,R.drawable.calender,R.drawable.nodels));

        return arr;
    }
}