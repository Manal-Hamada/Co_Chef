package com.example.foodplanner_app.country_meals.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.category_meals.model.Category_Model;
import com.example.foodplanner_app.country_meals.repository.Repository;
import com.example.foodplanner_app.category_meals.view.CategoryAdapter;
import com.example.foodplanner_app.country_meals.model.Country_Model;
import com.example.foodplanner_app.meals.view.MealsFragment;

import java.util.ArrayList;


public class CountryFr extends Fragment implements CountryOnClickListener {
    SearchView search;
    RecyclerView recycler;
    Country_Adapter adapter;
    ArrayList<Country_Model> arr;
    Repository repo;

    public CountryFr() {
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
        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.VISIBLE);
        arr=new ArrayList<Country_Model>();
        setRecycler();
        arr.add(new Country_Model("Egypt"));
        repo=Repository.getInstance();
        repo.countries();
        Repository.muArray.observe(getActivity(), new Observer<ArrayList<Country_Model>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<Country_Model> country_models) {
                adapter.setList(country_models);
                adapter.notifyDataSetChanged();
                recycler.setAdapter(adapter);
            }
        });

        showCategoryTexts();

    }
    public void setRecycler(){
        recycler= requireView().findViewById(R.id.country_list);
        adapter=new Country_Adapter(getActivity(),arr,this);
        GridLayoutManager manger = new GridLayoutManager(getActivity(),2);
        manger.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(manger);
    }
    public void showCategoryTexts(){
        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.VISIBLE);

    }

    @Override
    public void navToMeals(String countryName,int key) {
        addFragments(new MealsFragment(countryName,key));
    }
    public void addFragments(Fragment fragment){
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.search_bar).setVisibility(View.GONE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);

    }
}