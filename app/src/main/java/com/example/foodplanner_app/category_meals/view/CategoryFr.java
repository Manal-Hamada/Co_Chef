package com.example.foodplanner_app.category_meals.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.view.DetailsFragment;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.network.ApiClient;
import com.example.foodplanner_app.category_meals.network.CategoryNetworkDelegate;
import com.example.foodplanner_app.category_meals.model.Category_Model;

import java.util.ArrayList;


public class CategoryFr extends Fragment implements DetailsOnClickListener, CategoryNetworkDelegate {
    RecyclerView recycler;
    CategoryAdapter adapter;
    ArrayList<Category_Model> arr;
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
        setRecycler();
        ApiClient client = ApiClient.getInstance();
        client.enqueueCall(CategoryFr.this);

        showCategoryTexts();

    }
    public void setRecycler(){
        arr=new ArrayList<>();
        recycler= requireView().findViewById(R.id.meal_list);
        adapter=new CategoryAdapter(getActivity(),arr,this);
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
    public void navToDetails() {
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
        hideCategoryTexts();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new DetailsFragment()).commit();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onSuccess(ArrayList<Category_Model> categories) {
        arr= categories;
        adapter.setList(categories);
        recycler.setAdapter(adapter);
      //  Log.i("TEST","TAG"+arr.get(0).getName());
        adapter.notifyDataSetChanged();
           }

    @Override
    public void onFailure(String error) {

    }
}