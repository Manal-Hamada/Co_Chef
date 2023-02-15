package com.example.foodplanner_app.ingredients.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.ingredients.repository.Repository;
import com.example.foodplanner_app.ingredients.model.IngredientModel;
import com.example.foodplanner_app.meals.view.MealsFragment;

import java.util.ArrayList;

public class IngredienstFr extends Fragment implements IngredientsOnClickListener {
    SearchView search;
    RecyclerView recycler;
    Ingredients_Adapter adapter;
    ArrayList<IngredientModel> arr;
    Repository repo;
    public IngredienstFr() {
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
        return inflater.inflate(R.layout.fragment_ingredienst, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.VISIBLE);
        arr=new ArrayList<IngredientModel>();
        setRecycler();
        repo=Repository.getInstance();
        repo.ingredients();
        Repository.muArray.observe(getActivity(), new Observer<ArrayList<IngredientModel>>() {
            @Override
            public void onChanged(ArrayList<IngredientModel> ingredientModels) {
                adapter.setList(ingredientModels);
                adapter.notifyDataSetChanged();
                recycler.setAdapter(adapter);
            }

    });

        showCategoryTexts();
}
    public void setRecycler(){
        recycler= requireView().findViewById(R.id.ingredients_list);
        adapter=new Ingredients_Adapter(getActivity(),arr,this);
        GridLayoutManager manger = new GridLayoutManager(getActivity(),4);
        manger.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(manger);
    }
    public void showCategoryTexts(){
        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.VISIBLE);

    }
    @Override
    public void navToMeals(String ingredientName,int key) {
        addFragments(new MealsFragment(ingredientName,key));
    }
    public void addFragments(Fragment fragment){
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.search_bar).setVisibility(View.GONE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);

    }
}