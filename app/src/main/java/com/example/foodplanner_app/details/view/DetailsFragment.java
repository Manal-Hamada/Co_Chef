package com.example.foodplanner_app.details.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.repository.MealDetailsRepository;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.meals.view.AddFavClickListener;
import com.example.foodplanner_app.models.MealDetailsWithUserId;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DetailsFragment extends Fragment implements AddFavClickListener {

    public Activity myActivity;
    SimpleDateFormat simpleDateFormat;
    DatePickerDialog datePickerDialog;
    FloatingActionButton addMealFab;
    RecyclerView recycler;
    Db_Repository dbRepo;
    DetailsAdapter adapter;
    ArrayList<MealDetailsModel> arr;
    MealDetailsRepository repo;
    public static MealDetailsModel meal;
    TextView mealStepsTV, mealCountryTv, mealNameTv;
    ImageView mealImg,details_fav_ic,details_UnFav_ic;
    YouTubePlayerView youTubePlayerView;
    int mealId;

    public DetailsFragment() {
        // Required empty public constructor
        myActivity = getActivity();
    }

    public DetailsFragment(int mealId) {
        this.mealId = mealId;
        myActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repo = MealDetailsRepository.getInstance(getActivity());
        dbRepo=Db_Repository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        MealDetailsRepository.mutableMealArray.observe(requireActivity(), new Observer<ArrayList<MealDetailsModel>>() {
            @Override
            public void onChanged(ArrayList<MealDetailsModel> meal_details_models) {
                Log.i("xzzzzzzzzzxxx", "onChanged: size = "+meal_details_models.size());
                mealNameTv.setText(meal_details_models.get(0).getStrMeal());
                String steps = meal_details_models.get(0).getStrInstructions().replace("\n","\n\n");
                mealStepsTV.setText(steps);
                mealCountryTv.setText("Origin " + ": "+meal_details_models.get(0).getStrArea());
                if(DetailsFragment.this!= null && DetailsFragment.this.isVisible())
                    Glide.with(DetailsFragment.this).load(meal_details_models.get(0).getStrMealThumb()).into(mealImg);
                callYoutubeAPI(meal_details_models);
                adapter.setList(meal_details_models);
                recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }


            private void callYoutubeAPI(ArrayList<MealDetailsModel> meal_details_models) {
                if (meal_details_models.get(0).getStrYoutube().isEmpty()){
                    youTubePlayerView.setVisibility(View.GONE);
                }else {

                    getLifecycle().addObserver(youTubePlayerView) ;
                    String[] youtubeVideoCode = meal_details_models.get(0).getStrYoutube().split("=");
                    youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError error) {
                            super.onError(youTubePlayer, error);
                            Log.i("Dettt", "onError: ");
                        }

                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            super.onReady(youTubePlayer);
                            Log.i("Dettt", "onReady: ");
                            youTubePlayer.cueVideo(youtubeVideoCode[1],0);
                        }

                    });
                }
            }
        });

        addMealFab =view.findViewById(R.id.add_meal_fab);
        simpleDateFormat = new SimpleDateFormat("yyy-MM-ddd");
        setAddBtnAction();
        setDetailsFav();


    }
    public void setRecycler(View view){
        recycler= view.findViewById(R.id.ingredient_recyclerview);
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recycler);
        adapter=new DetailsAdapter(getActivity(),arr,this);
        GridLayoutManager manger = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(manger);
    }
    private void pickDateTime(){

        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date.set(year, month, dayOfMonth);

            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.WEEK_OF_MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();
    }
    public void setAddBtnAction(){
        addMealFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pickDateTime();

                Date date = new Date();
                dbRepo.addPlannedMeal(meal, date.toString());
            }
        });

    }
    public void init( View view){
        mealNameTv = view.findViewById(R.id.meal_str_name);
        mealCountryTv = view.findViewById(R.id.meal_str_country);
        mealStepsTV = view.findViewById(R.id.meal_str_steps);
        mealImg = view.findViewById(R.id.meal_img);
        Activity myActivity = getActivity();
        details_UnFav_ic=view.findViewById(R.id.details_un_fav_ic);
        youTubePlayerView = view.findViewById(R.id.youtube_video);
        details_fav_ic= view.findViewById(R.id.details_fav_ic);
        setRecycler(view);
       // repo = MealDetailsRepository.getInstance();
        repo.getMeals(mealId);
    }
    public void setFavIcAction(){
        // call addfav from repo
    }
    public void setDetailsFav(){
        details_fav_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(details_UnFav_ic.getVisibility()==View.GONE) {
                    dbRepo.addFavouriteMeal(meal);
                    insertToRoom(meal);
                    //getAllMealls();
                    details_UnFav_ic.setVisibility(View.VISIBLE);
                    details_fav_ic.setVisibility(View.GONE);
                }
                else {
                    //TODO remove
                    dbRepo.unFavMeal(meal);
                    repo.dao.deleteMeal(meal);
                   // deleteFromRoom(meal);
                    details_UnFav_ic.setVisibility(View.GONE);
                    details_fav_ic.setVisibility(View.VISIBLE);
                }

            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("DetailsFFFF", "onDestroy: ");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("DetailsFFFF", "onAttach: ");
    }


    @Override
    public void addFavItem(MealDetailsModel model) {

        meal=model;
    }
    public void insertToRoom(MealDetailsModel meal){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    repo.dao.insertMeal(meal);
                }
            }).start();
        }
    public void deleteFromRoom(MealDetailsModel meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                repo.dao.deleteMeal(meal);
            }
        }).start();
    }

    }

