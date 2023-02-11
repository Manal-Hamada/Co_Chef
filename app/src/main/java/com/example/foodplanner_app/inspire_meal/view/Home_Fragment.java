package com.example.foodplanner_app.inspire_meal.view;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.category_meals.model.Category_Model;
import com.example.foodplanner_app.category_meals.view.CategoryAdapter;
import com.example.foodplanner_app.category_meals.view.CategoryOnClickListener;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.inspire_meal.repository.Repository;
import com.example.foodplanner_app.inspire_meal.model.Inspirational_Model;
import com.example.foodplanner_app.meals.view.MealsFragment;

import java.util.ArrayList;

public class Home_Fragment extends Fragment implements DetailsOnClickListener, CategoryOnClickListener {
    ArrayList<Inspirational_Model> arr;
    RecyclerView recycler;
    CategoryAdapter adapter;
    ArrayList<Category_Model> categoryArr;
    com.example.foodplanner_app.category_meals.repository. Repository categoryRepo;
    Repository repo;
    TextView mealName, area, category, descreption;
    ImageView mealImg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        arr = new ArrayList<Inspirational_Model>();
        setRecycler(view);
        setInspirationalData();
        categoryArr = new ArrayList<>();
        setCategoryData();


    }

    public void init(View v) {
        mealName = v.findViewById(R.id.ins_meal_name);
        area = v.findViewById(R.id.ins_category);
        category = v.findViewById(R.id.ins_category);
        mealImg = v.findViewById(R.id.ins_meal_img);
    }

    public void setRecycler(View v) {
        recycler = v.findViewById(R.id.ins_category_list);
        adapter = new CategoryAdapter(getActivity(), categoryArr, this, this);
        GridLayoutManager manger = new GridLayoutManager(getActivity(), 2);
        manger.setOrientation(RecyclerView.HORIZONTAL);
        recycler.setLayoutManager(manger);
    }

    @Override
    public void navToMeals(String categoryName,int key) {
        addFragments(new MealsFragment(categoryName,key));
    }
    public void addFragments(Fragment fragment){
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.search_bar).setVisibility(View.GONE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);


    }

    @Override
    public void navToDetails() {

    }

    public void setInspirationalData() {
        repo = Repository.getInstance();
        repo.inspirationalMeal();
        Repository.muArray.observe(getActivity(), new Observer<ArrayList<Inspirational_Model>>() {
            @Override
            public void onChanged(ArrayList<Inspirational_Model> inspirational_models) {
                Log.i("MANAL", inspirational_models.get(0).getStrMeal().toString());
                Glide.with(getActivity()).load(inspirational_models.get(0).getStrImageSource()).into(mealImg);
                mealName.setText(inspirational_models.get(0).getStrMeal().toString());
            }
        });
    }
    public void setCategoryData(){
        categoryRepo= com.example.foodplanner_app.category_meals.repository.Repository.getInstance();
        categoryRepo.categories();
        com.example.foodplanner_app.category_meals.repository.Repository.muArray.observe(getActivity()
                , new Observer<ArrayList<Category_Model>>() {

            @Override
            public void onChanged(ArrayList<Category_Model> category_models) {
                adapter.setList(category_models);
                adapter.notifyDataSetChanged();
                recycler.setAdapter(adapter);
            }
        });

    }
}