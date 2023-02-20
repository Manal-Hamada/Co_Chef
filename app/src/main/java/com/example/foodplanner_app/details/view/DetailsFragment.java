package com.example.foodplanner_app.details.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.app_activities.HomeActivity;
import com.example.foodplanner_app.authantication.LoginActivity;
import com.example.foodplanner_app.daily_meals.repository.Daily_Repository;
import com.example.foodplanner_app.details.repository.MealDetailsRepository;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.fav_meals.view.Fav_Meal_Interface;
import com.example.foodplanner_app.meals.view.AddFavClickListener;
import com.example.foodplanner_app.models.MealDetailsWithUserId;
import com.example.foodplanner_app.models.Utilities;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Date;
import java.util.TimeZone;

public class DetailsFragment extends Fragment implements AddFavClickListener, Fav_Meal_Interface {

    public Activity myActivity;
    SimpleDateFormat simpleDateFormat;
    DatePickerDialog datePickerDialog;
    FloatingActionButton addMealFab;
    Daily_Repository dailyRepo;
    RecyclerView recycler;
    Db_Repository dbRepo;
    DetailsAdapter adapter;
    Db_Model model;
    DetailsOnClickListener listener;
    ArrayList<MealDetailsModel> arr;
    MealDetailsRepository repo;
    public static MealDetailsModel meal;
    TextView mealStepsTV, mealCountryTv, mealNameTv;
    ImageView mealImg, details_fav_ic, details_UnFav_ic;
    YouTubePlayerView youTubePlayerView;
    int mealId;
    String savedDate;

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
        dbRepo = Db_Repository.getInstance();
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
        dailyRepo = Daily_Repository.getInstance(getActivity());
        model = new Db_Model();

        MealDetailsRepository.mutableMealArray.observe(requireActivity(), new Observer<ArrayList<MealDetailsModel>>() {
            @Override
            public void onChanged(ArrayList<MealDetailsModel> meal_details_models) {
                mealNameTv.setText(meal_details_models.get(0).getStrMeal());
                String steps = meal_details_models.get(0).getStrInstructions().replace("\n", "\n\n");
                mealStepsTV.setText(steps);
                mealCountryTv.setText("Origin " + ": " + meal_details_models.get(0).getStrArea());
                if (DetailsFragment.this != null && DetailsFragment.this.isVisible())
                    Glide.with(DetailsFragment.this).load(meal_details_models.get(0).getStrMealThumb()).into(mealImg);
                callYoutubeAPI(meal_details_models);
                adapter.setList(meal_details_models);
                recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                meal = meal_details_models.get(0);
            }


            private void callYoutubeAPI(ArrayList<MealDetailsModel> meal_details_models) {
                if (meal_details_models.get(0).getStrYoutube().isEmpty()) {
                    youTubePlayerView.setVisibility(View.GONE);
                } else {

                    getLifecycle().addObserver(youTubePlayerView);
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
                            youTubePlayer.cueVideo(youtubeVideoCode[1], 0);
                        }

                    });
                }
            }
        });

        addMealFab = view.findViewById(R.id.add_meal_fab);
        simpleDateFormat = new SimpleDateFormat("yyy-MM-ddd");
        setAddBtnAction();
        setDetailsFav();


    }

    public void setRecycler(View view) {
        recycler = view.findViewById(R.id.ingredient_recyclerview);
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recycler);
        adapter = new DetailsAdapter(getActivity(), arr, this);
        GridLayoutManager manger = new GridLayoutManager(getActivity(), 2);
        recycler.setLayoutManager(manger);
    }

    private void pickDateTime() {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date.set(year, month, dayOfMonth);

                //add plan
                //savedDate=date.get(Calendar.DAY_OF_MONTH) + "-" + date.get(Calendar.MONTH) + "-" + date.get(1);
                String formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date.getTime());//.compareTo()

                //TODO Rx
                setMeal(formattedDate);
                //addPlanThread(model); //room
                dbRepo.addPlannedMeal(model);
                Log.i("tesjrwjkbhssssst", " this date" + model.getIdMeal());

            }

            private void setMeal(String formattedDate) {
                model = new Db_Model(formattedDate, meal.getIdMeal(), meal.getStrMeal()
                        , meal.getStrDrinkAlternate(), meal.getStrCategory(), meal.getStrArea()
                        , meal.getStrInstructions(), meal.getStrMealThumb(), meal.getStrTags()
                        , meal.getStrYoutube(), meal.getStrIngredient1(), meal.getStrIngredient2()
                        , meal.getStrIngredient3(), meal.getStrIngredient4(), meal.getStrIngredient5()
                        , meal.getStrIngredient6(), meal.getStrIngredient7(), meal.getStrIngredient8()
                        , meal.getStrIngredient9(), meal.getStrIngredient10()
                        , meal.getStrIngredient11(), meal.getStrIngredient12()
                        , meal.getStrIngredient13(), meal.getStrIngredient14()
                        , meal.getStrIngredient15(), meal.getStrIngredient16()
                        , meal.getStrIngredient17(), meal.getStrIngredient18()
                        , meal.getStrIngredient19(), meal.getStrIngredient20()
                        , meal.getStrMeasure1(), meal.getStrMeasure2(), meal.getStrMeasure3()
                        , meal.getStrMeasure4(), meal.getStrMeasure5(), meal.getStrMeasure6()
                        , meal.getStrMeasure7(), meal.getStrMeasure8(), meal.getStrMeasure9()
                        , meal.getStrMeasure10(), meal.getStrMeasure11(), meal.getStrMeasure12()
                        , meal.getStrMeasure13(), meal.getStrMeasure14(), meal.getStrMeasure15()
                        , meal.getStrMeasure16(), meal.getStrMeasure17(), meal.getStrMeasure18()
                        , meal.getStrMeasure19(), meal.getStrMeasure20(), meal.getStrSource()
                        , meal.getStrImageSource(), meal.getStrCreativeCommonsConfirmed()
                        , meal.getDateModified());
            }

        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        //TODO set max
    }

    public void setAddBtnAction() {
        addMealFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateTime();
            }
        });

    }

    public void init(View view) {
        mealNameTv = view.findViewById(R.id.meal_str_name);
        mealCountryTv = view.findViewById(R.id.meal_str_country);
        mealStepsTV = view.findViewById(R.id.meal_str_steps);
        mealImg = view.findViewById(R.id.meal_img);
        Activity myActivity = getActivity();
        details_UnFav_ic = view.findViewById(R.id.details_un_fav_ic);
        youTubePlayerView = view.findViewById(R.id.youtube_video);
        details_fav_ic = view.findViewById(R.id.details_fav_ic);
        setRecycler(view);
        // repo = MealDetailsRepository.getInstance();
        repo.getMeals(mealId);
    }

    public void setFavIcAction() {
        // call addfav from repo
    }

    public void setDetailsFav() {
        details_fav_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getUid() == null) {
                    Intent backToLoginIntent  = new Intent(getActivity(), LoginActivity.class);
                    Utilities.showDialogToLogin(getContext(), "please login", getActivity(),backToLoginIntent );
                }
                else {

                    if (details_UnFav_ic.getVisibility() == View.GONE) {
                        dbRepo.addFavouriteMeal(meal);
                        insertToRoom(meal);
                        //getAllMealls();
                        details_UnFav_ic.setVisibility(View.VISIBLE);
                        details_fav_ic.setVisibility(View.GONE);
                    } else {
                        //TODO remove
                        dbRepo.unFavMeal(meal);
                        repo.dao.deleteMeal(meal);
                        deleteFromRoom(meal);
                        details_UnFav_ic.setVisibility(View.GONE);
                        details_fav_ic.setVisibility(View.VISIBLE);
                    }
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
    public void deleteMeal(MealDetailsModel meal) {

    }

    @Override
    public void addFavItem(MealDetailsModel model) {

        meal = model;
    }

    public void insertToRoom(MealDetailsModel meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repo.dao.insertMeal(meal);
            }
        }).start();
    }

    public void deleteFromRoom(MealDetailsModel meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repo.dao.deleteMeal(meal);
            }
        }).start();
    }

    public void addPlanThread(Db_Model mealWithDate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("tessssssssst", " this date: " + mealWithDate.getIdMeal());

                //dailyRepo.dbRepo.addPlan(model, getActivity());
                dailyRepo.dao.insertPlandMeal(mealWithDate);

            }
        }).start();
    }
}

