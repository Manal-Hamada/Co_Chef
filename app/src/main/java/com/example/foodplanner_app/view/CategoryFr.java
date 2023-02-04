package com.example.foodplanner_app.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.foodplanner_app.Model;
import com.example.foodplanner_app.R;

import java.util.ArrayList;
import java.util.Objects;


public class CategoryFr extends Fragment {
    RecyclerView recycler;
    RecyclerView topRecycler;
    MealsAdapter adapter;
    ArrayList<Model> arr;
    ArrayList<Model> topArr;
    TopRatedAdapter topAdapter;

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

        setTopRecycler();
        setRecycler();
    }
    public void setRecycler(){
        recycler= requireView().findViewById(R.id.meal_list);
        GridLayoutManager manger = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(manger);
        adapter=new MealsAdapter(getActivity(),setArr(arr));
        recycler.setAdapter(adapter);
    }
    public void setTopRecycler(){
        topRecycler= requireView().findViewById(R.id.top_list);
        LinearLayoutManager manger1=new LinearLayoutManager(getActivity());
        manger1.setOrientation(RecyclerView.HORIZONTAL);
        topRecycler.setLayoutManager(manger1);
        topAdapter=new TopRatedAdapter(getActivity(),setArr(arr));
        topRecycler.setAdapter(topAdapter);
    }
    public ArrayList<Model> setArr(ArrayList<Model> arr){

        arr.add(new Model("Soap",R.drawable.fav_outer_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Soap",R.drawable.fav_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Soap",R.drawable.fav_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Soap",R.drawable.fav_outer_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Soap",R.drawable.fav_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Soap",R.drawable.fav_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Soap",R.drawable.fav_outer_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Soap",R.drawable.fav_ic,R.drawable.calender,R.drawable.soap));
        arr.add(new Model("Soap",R.drawable.fav_outer_ic,R.drawable.calender,R.drawable.soap));

        return arr;
    }

}