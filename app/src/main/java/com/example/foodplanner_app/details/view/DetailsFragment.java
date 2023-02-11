package com.example.foodplanner_app.details.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.Repositry.MealDetailsRepository;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class DetailsFragment extends Fragment {

    public Activity myActivity;
    TextView topRated;
    ImageView arrwo;
    SimpleDateFormat simpleDateFormat;
    DatePickerDialog datePickerDialog;
    FloatingActionButton addMealFab;

    RecyclerView recycler;
    DetailsAdapter adapter;
    ArrayList<MealDetailsModel> arr;
    MealDetailsRepository repo;

    TextView mealStepsTV, mealCountryTv, mealNameTv;
    ImageView mealImg;
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
        repo = MealDetailsRepository.getInstance();
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

        mealNameTv = view.findViewById(R.id.meal_str_name);
        mealCountryTv = view.findViewById(R.id.meal_str_country);
        mealStepsTV = view.findViewById(R.id.meal_str_steps);
        mealImg = view.findViewById(R.id.meal_img);
        Activity myActivity = getActivity();
        youTubePlayerView = view.findViewById(R.id.youtube_video);
        setRecycler(view);
        repo = MealDetailsRepository.getInstance();
        repo.getMeals(mealId);
        MealDetailsRepository.mutableMealArray.observe(requireActivity(), new Observer<ArrayList<MealDetailsModel>>() {
            @Override
            public void onChanged(ArrayList<MealDetailsModel> meal_details_models) {
                Log.i("xzzzzzzzzzxxx", "onChanged: size = "+meal_details_models.size());
                mealNameTv.setText(meal_details_models.get(0).getStrMeal());
                String steps = meal_details_models.get(0).getStrInstructions().replace("\n","\n\n");
                mealStepsTV.setText(steps);
                mealCountryTv.setText("Origin country: "+meal_details_models.get(0).getStrArea());
                Glide.with(myActivity).load(meal_details_models.get(0).getStrMealThumb()).into(mealImg);
                //callYoutubeAPI(meal_details_models);
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
                            youTubePlayer.loadVideo(youtubeVideoCode[1],0);
                        }

                    });
                }
            }
        });

        //-----------manal---------------
//        addMealFab =view.findViewById(R.id.add_meal_fab);
//        simpleDateFormat = new SimpleDateFormat("yyy-MM-ddd");
//        // pickDateTime();
//        setAddBtnAction();



       /* topRated=view.findViewById(R.id.top_txt);
        arrwo=view.findViewById(R.id.Arr);
        topRated.setVisibility(View.VISIBLE);
        arrwo.setVisibility(View.VISIBLE);*/

    }


    public void setRecycler(View view){
        recycler= view.findViewById(R.id.ingredient_recyclerview);
        adapter=new DetailsAdapter(getActivity(),arr);
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
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();
    }
    public void setAddBtnAction(){
        addMealFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateTime();
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
}

