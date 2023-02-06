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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodplanner_app.Model;
import com.example.foodplanner_app.R;

import java.util.ArrayList;
import java.util.Objects;


public class CategoryFr extends Fragment implements DetailsOnClickListener {
    RecyclerView recycler;
    RecyclerView topRecycler;
    MealsAdapter adapter;
    ArrayList<Model> arr;
    ArrayList<Model> topArr;
    TopRatedAdapter topAdapter;
    TextView topRated;
    ImageView arrwo;
    SearchView search;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        arr=new ArrayList<>();
        setArr();
        showCategoryTexts();
        setTopRecycler();
        setRecycler();
    }
    public void setRecycler(){
        recycler= requireView().findViewById(R.id.meal_list);
        GridLayoutManager manger = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(manger);
        adapter=new MealsAdapter(getActivity(),arr,this);
        recycler.setAdapter(adapter);
    }
    public void setTopRecycler(){
        topRecycler= requireView().findViewById(R.id.top_list);
        LinearLayoutManager manger1=new LinearLayoutManager(getActivity());
        manger1.setOrientation(RecyclerView.HORIZONTAL);
        topRecycler.setLayoutManager(manger1);
        topAdapter=new TopRatedAdapter(getActivity(),arr);
        topRecycler.setAdapter(topAdapter);
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

    public void hideCategoryTexts(){
        topRated.setVisibility(View.GONE);
        arrwo.setVisibility(View.GONE);
        search.setVisibility(View.GONE);
    }
    public void showCategoryTexts(){
        topRated=getActivity().findViewById(R.id.top_txt);
        arrwo=getActivity().findViewById(R.id.Arr);
        search=getActivity().findViewById(R.id.search_bar);
        topRated.setVisibility(View.VISIBLE);
        arrwo.setVisibility(View.VISIBLE);
        search.setVisibility(View.VISIBLE);
    }

    @Override
    public void navToDetails() {
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
        hideCategoryTexts();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new DetailsFragment()).commit();
    }
}