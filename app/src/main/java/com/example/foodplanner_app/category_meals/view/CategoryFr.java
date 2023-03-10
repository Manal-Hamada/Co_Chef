package com.example.foodplanner_app.category_meals.view;

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

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.category_meals.repository.Repository;
import com.example.foodplanner_app.details.view.DetailsFragment;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.meals.view.MealsFragment;
import com.example.foodplanner_app.category_meals.model.Category_Model;
import java.util.ArrayList;



public class CategoryFr extends Fragment implements DetailsOnClickListener,CategoryOnClickListener {
    RecyclerView recycler;
    CategoryAdapter adapter;
    ArrayList<Category_Model >arr;
    SearchView search;
    Repository repo;

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

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arr=new ArrayList<Category_Model>();
       // arr.add(new Category_Model("1","beef","ll","mm"));
        setRecycler(view);
        repo=Repository.getInstance();
        repo.categories();
        Repository.muArray.observe(getActivity(), new Observer<ArrayList<Category_Model>>() {

            @Override
            public void onChanged(ArrayList<Category_Model> category_models) {
                adapter.setList(category_models);
                adapter.notifyDataSetChanged();
                recycler.setAdapter(adapter);

            }
        });
        showCategoryTexts();

    }
    public void setRecycler(View v){
        recycler= v.findViewById(R.id.meal_list);
        adapter=new CategoryAdapter(getActivity(),arr,this,this);
        GridLayoutManager manger = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(manger);
    }

    public void hideCategoryTexts(){
        search.setVisibility(View.GONE);
    }
    public void showCategoryTexts(){
        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.VISIBLE);

    }



    @Override
    public void navToMeals(String categoryName,int key) {
        //getActivity().findViewById(R.id.tablayout).setVisibility(View.GONE);
        addFragments(new MealsFragment(categoryName,key));
    }
    public void addFragments(Fragment fragment){
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.search_bar).setVisibility(View.GONE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);


    }

    @Override
    public void navToDetails(int id) {
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
        hideCategoryTexts();
        Log.i("wjfkjrebv", "navToDetails: replace");

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new DetailsFragment()).addToBackStack(null).commit();

    }

    @Override
    public void addPlan(Db_Model model) {

    }
}