package com.example.foodplanner_app.inspire_meal.view;

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

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.category_meals.model.Category_Model;
import com.example.foodplanner_app.category_meals.view.CategoryAdapter;
import com.example.foodplanner_app.category_meals.view.CategoryOnClickListener;
import com.example.foodplanner_app.details.view.DetailsFragment;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.inspire_meal.repository.Repository;
import com.example.foodplanner_app.inspire_meal.model.Inspirational_Model;
import com.example.foodplanner_app.meals.view.MealsFragment;

import java.util.ArrayList;

public class Home_Fragment extends Fragment implements DetailsOnClickListener, CategoryOnClickListener {
    ArrayList<Inspirational_Model> arr;
    ArrayList<Category_Model> categoryArr;
    RecyclerView recycler,insRecycler;
    CategoryAdapter adapter;
    Inspiration_Adapter insAdapter;
    com.example.foodplanner_app.category_meals.repository. Repository categoryRepo;
    Repository repo;
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
        arr = new ArrayList<Inspirational_Model>();
        categoryArr = new ArrayList<>();
        setInspirationalRecycler(view);
        setCategoryRecycler(view);
        setInspirationalData();
        setCategoryData();
    }

    public void setCategoryRecycler(View v) {
        recycler = v.findViewById(R.id.ins_category_list);
        adapter = new CategoryAdapter(getActivity(), categoryArr, this, this);
        GridLayoutManager manger = new GridLayoutManager(getActivity(), 2);
        manger.setOrientation(RecyclerView.HORIZONTAL);
        recycler.setLayoutManager(manger);
    }
    public  void setInspirationalRecycler(View v){
        insRecycler = v.findViewById(R.id.ins_list);
        insAdapter = new Inspiration_Adapter(getActivity(), arr, this);
        LinearLayoutManager manger = new LinearLayoutManager(getActivity());
        manger.setOrientation(RecyclerView.HORIZONTAL);
        insRecycler.setLayoutManager(manger);
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

    public void setInspirationalData() {
        repo = Repository.getInstance();
        repo.inspirationalMeal();
        Log.i("insideeee", "from ins dataaa ");
        Repository.muArray.observe(getActivity(), new Observer<ArrayList<Inspirational_Model>>() {
            @Override
            public void onChanged(ArrayList<Inspirational_Model> inspirational_models) {
                insAdapter.setList(inspirational_models);
                Log.i("Insspirr Arrrrrrau size", ""+inspirational_models.size());
                insAdapter.notifyDataSetChanged();
                insRecycler.setAdapter(insAdapter);
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

    @Override
    public void navToDetails(int id) {
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
       // hideCategoryTexts();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new DetailsFragment(id)).commit();

    }

    @Override
    public void addPlan(Db_Model model) {

    }


}