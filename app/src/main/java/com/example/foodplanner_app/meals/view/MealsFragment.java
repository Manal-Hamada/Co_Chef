package com.example.foodplanner_app.meals.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.fav_meals.model.Favourite_Model;
import com.example.foodplanner_app.fav_meals.view.Fav_Meal_Interface;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;
import com.example.foodplanner_app.R;

import com.example.foodplanner_app.meals.repository.Repository;
import com.example.foodplanner_app.details.view.DetailsFragment;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.meals.model.Meal_Model;

import java.util.ArrayList;


public class MealsFragment extends Fragment implements DetailsOnClickListener {
    RecyclerView recycler;
    MealAdapter adapter;
    ArrayList<Meal_Model>arr;
    EditText search;
    Repository repo;
    String categoryName;
    int key;
    TextView categoryLabel;
    Db_Repository db_Repo;
    //public MealsFragment(){}
    public MealsFragment(String categoryName, int key){
        this.categoryName = categoryName;
        this.key=key;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db_Repo=Db_Repository.getInstance();

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
        repo=Repository.getInstance();
        checkFragment();
        Repository.mutableMealsArray.observe(getActivity(), new Observer<ArrayList<Meal_Model>>() {
            @Override
            public void onChanged(ArrayList<Meal_Model> meal_models) {
                Log.i("TAGGGGhh", "onChanged: "+meal_models.get(0).getStrMeal());
                arr = meal_models;
              //  arr.add(new Meal_Model("kkk","mm","kk"));
                adapter.setList(arr);
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
    public void navToDetails(int id) {
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
        hideCategoryTexts();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new DetailsFragment(id)).addToBackStack(null).commit();
    }

    @Override
    public void addPlan(Db_Model model) {

    }

    public void checkFragment(){
         if(key==1)
             repo.getMeals(categoryName);
         else if (key==2)
             repo.getCountryMeals(categoryName);
         else
             repo.getIngredientsMeals(categoryName);
}

}