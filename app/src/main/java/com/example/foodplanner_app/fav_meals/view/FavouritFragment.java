package com.example.foodplanner_app.fav_meals.view;

import static com.example.foodplanner_app.details.view.DetailsFragment.meal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.details.view.DetailsFragment;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.fav_meals.repository.Repository;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FavouritFragment extends Fragment implements DetailsOnClickListener,Fav_Meal_Interface {

    RecyclerView recycler;
    FavouriteAdapter adapter;
    ArrayList<MealDetailsModel> arr;
    SearchView search;
    Db_Repository repo;
    Repository favRepo;
    DetailsFragment dFr;
    MealDetailsModel deletedMeal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public FavouritFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_favourit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arr=new ArrayList<>();
        dFr=new DetailsFragment();
       // repo=Db_Repository.getInstance();
        Log.i("lllllll", ""+arr.size());
        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.GONE);
        setRecycler(view);
        favRepo= Repository.getInstance(getActivity());
        favRepo.getAllMealls(getActivity());
        favRepo.getAllMealls(getActivity()).observe(getViewLifecycleOwner(), new Observer<List<MealDetailsModel>>() {
            @Override
            public void onChanged(List<MealDetailsModel> mealDetailsModels) {
                arr = (ArrayList<MealDetailsModel>) mealDetailsModels;
                adapter.setList(arr);
                recycler.setAdapter(adapter);
            }
        });
      /* favRepo.dao.getAllMeals().observe(getViewLifecycleOwner(), new Observer<List<MealDetailsModel>>() {
            @Override
            public void onChanged(List<MealDetailsModel> mealDetailsModels) {
                arr = (ArrayList<MealDetailsModel>) mealDetailsModels;
                adapter.setList(arr);
                recycler.setAdapter(adapter);
            }
        });*/
      setswipping();
    }

    public void setRecycler(View v){
        recycler= v.findViewById(R.id.fav_list);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manger = new LinearLayoutManager(getActivity());
        manger.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(manger);
        adapter=new FavouriteAdapter(getActivity(),arr,this,this);
    }

    @Override
    public void navToDetails(int id) {
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.pager) .setVisibility(View.GONE);
        getActivity().findViewById(R.id.tablayout) .setVisibility(View.GONE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new DetailsFragment(id)).addToBackStack(null).commit();
    }

    @Override
    public void addPlan(Db_Model model) {

    }

    @Override
    public void deleteMeal(MealDetailsModel meal) {
        deleteThread(meal);
    }

    @Override
    public void addFavItem(MealDetailsModel model) {

    }

    public void setswipping(){

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                MealDetailsModel deletedMeal = arr.get(viewHolder.getAdapterPosition());
                deleteThread(meal);
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deletedMeal = arr.get(viewHolder.getAdapterPosition());
                 deleteThread(meal);
                int position = viewHolder.getAdapterPosition();
                arr.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                Snackbar.make(recycler, deletedMeal.getStrMeal()+" is deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arr.add(position, deletedMeal);

                        adapter.notifyItemInserted(position);

                    }
                }).show();
            }
        }).attachToRecyclerView(recycler);
    }
    public void deleteThread(MealDetailsModel meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                favRepo.dao.deleteMeal(meal);
                favRepo.dbRepo.unFavMeal(meal);
            }
        }).start();
    }
}

