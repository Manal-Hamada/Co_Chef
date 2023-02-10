package com.example.foodplanner_app.meals.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner_app.R;

import com.example.foodplanner_app.meals.repository.Repository;
import com.example.foodplanner_app.details.view.DetailsFragment;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.meals.model.Meal_Model;
import com.example.foodplanner_app.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MealsFragment extends Fragment implements DetailsOnClickListener {
    RecyclerView recycler;
    MealAdapter adapter;
    ArrayList<Meal_Model>arr;
    SearchView search;
    Repository repo;
    String categoryName;
    TextView categoryLabel;
    //public MealsFragment(){}
    public MealsFragment(String categoryName){
        this.categoryName = categoryName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repo=Repository.getInstance();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals, container, false);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arr=new ArrayList<Meal_Model>();
        categoryLabel = view.findViewById(R.id.label_tv);
        categoryLabel.setText(categoryName);
        setRecycler();
        //arr.add(new Meal_Model("d","d","d"));
        repo=Repository.getInstance();
        repo.getMeals(categoryName);
        Repository.mutableMealsArray.observe(getActivity(), new Observer<ArrayList<Meal_Model>>() {
            @Override
            public void onChanged(ArrayList<Meal_Model> meal_models) {
                Log.i("TAGGGGhh", "onChanged: "+meal_models.get(0).getStrMeal());
                arr = meal_models;
                adapter.setList(meal_models);
                recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.i("TAGGGGhh", "onChanged: "+arr.size());
            }
        });

        showCategoryTexts();

    }
    public void setRecycler(){
        recycler= requireView().findViewById(R.id.meal_list);
        adapter=new MealAdapter(getActivity(),arr,this);
        GridLayoutManager manger = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(manger);
    }

    public void hideCategoryTexts(){
        search.setVisibility(View.GONE);
    }
    public void showCategoryTexts(){
        search=getActivity().findViewById(R.id.search_bar);
        //search.setVisibility(View.VISIBLE);

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