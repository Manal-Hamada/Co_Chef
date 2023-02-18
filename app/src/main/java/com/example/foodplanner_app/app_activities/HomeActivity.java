package com.example.foodplanner_app.app_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.R;

import com.example.foodplanner_app.databinding.ActivityHomeBinding;
import com.example.foodplanner_app.category_meals.view.CategoryFr;
import com.example.foodplanner_app.details.view.DetailsFragment;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.fragments.ProfileFragment;
import com.example.foodplanner_app.daily_meals.view.DailyPlanFragment;
import com.example.foodplanner_app.fav_meals.view.FavouritFragment;
import com.example.foodplanner_app.inspire_meal.view.Home_Fragment;
import com.example.foodplanner_app.meals.model.Meal_Model;
import com.example.foodplanner_app.meals.model.Meals_Response;

import com.example.foodplanner_app.meals.view.MealAdapter;
import com.example.foodplanner_app.models.Utilities;
import com.example.foodplanner_app.network.ApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity implements DetailsOnClickListener {

    BottomNavigationView bottomNavigationView;
    static TabLayout tab;
    ViewPager viewPager;
    MyPageAdapter myPagerAdapter;
    ActivityHomeBinding binding;
    FragmentContainerView container;
    EditText search;
    ApiClient client;
    Single<Meals_Response> call;
    String mode;
    RecyclerView searchList;
    MealAdapter adapter;
    public static MutableLiveData<ArrayList<Meal_Model>> muArray = new MutableLiveData<ArrayList<Meal_Model>>();
    ArrayList<Meal_Model> arr = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i = getIntent();
        if (i.getStringExtra("mode") != null)
            mode = i.getStringExtra("mode");
        else
            mode = "user";
        replaceFragments(new Home_Fragment());
        init();
        // setTabLayout();
        setTabSelectedListiner();
        setBottomNav();
        setSearchAction();
    }

    public void init() {
        arr = new ArrayList<>();
        tab = findViewById(R.id.tablayout);
        container = findViewById(R.id.container);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.pager);
        myPagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setVisibility(View.GONE);
        searchList = findViewById(R.id.search_list);
        adapter = new MealAdapter(this, arr, this);
        setTabLayout();

        search = findViewById(R.id.search_bar);
        search.setVisibility(View.GONE);

    }

    public void replaceFragments(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

    }

    public void setTabLayout() {
        viewPager.setAdapter(myPagerAdapter);
        tab.setupWithViewPager(viewPager);
    }

    public void setTabSelectedListiner() {
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setVisibility(View.VISIBLE);
                container.setVisibility(View.GONE);
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                        Objects.requireNonNull(tab.getTabAt(position)).select();
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setBottomNav() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (!mode.equals("guest"))
                userMode(item);
            else
                guestMode(item);

            return true;
        });
    }

    public void hideCategoryTexts() {

        search.setVisibility(View.GONE);
    }

    public void showCategoryTexts() {
        search.setVisibility(View.VISIBLE);
    }

    public void userMode(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.fav:
                viewPager.setVisibility(View.GONE);
                tab.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                searchList.setVisibility(View.GONE);
                replaceFragments(new FavouritFragment());
                break;
            case R.id.menu:
                tab.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                searchList.setVisibility(View.GONE);
                replaceFragments(new DailyPlanFragment());
                break;
            case R.id.profile:
                viewPager.setVisibility(View.GONE);
                tab.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                searchList.setVisibility(View.GONE);
                replaceFragments(new ProfileFragment());
                break;
            case R.id.home:
                viewPager.setVisibility(View.GONE);
                tab.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                replaceFragments(new Home_Fragment());
                break;
            case R.id.search:
                if (viewPager.getVisibility() == View.VISIBLE) {
                    container.setVisibility(View.GONE);
                    viewPager.setVisibility(View.VISIBLE);
                    tab.setVisibility(View.VISIBLE);
                    viewPager.getCurrentItem();
                    searchList.setVisibility(View.GONE);
                    showCategoryTexts();
                    replaceFragments(new CategoryFr());
                } else {
                    tab.setVisibility(View.VISIBLE);
                    replaceFragments(new CategoryFr());
                    showCategoryTexts();
                }
                break;

        }
    }

    public void guestMode(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.fav:
                tab.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                searchList.setVisibility(View.GONE);
                replaceFragments(new FavouritFragment());

                break;
            case R.id.menu:
                tab.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                searchList.setVisibility(View.GONE);
                replaceFragments(new DailyPlanFragment());
                break;
            case R.id.profile:
                Toast.makeText(this, "You have to sign in ", Toast.LENGTH_SHORT).show();

                break;
            case R.id.home:
                viewPager.setVisibility(View.GONE);
                tab.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                replaceFragments(new Home_Fragment());
                break;
            case R.id.search:
                Toast.makeText(this, "You have to sign in ", Toast.LENGTH_SHORT).show();

                break;

        }
    }

    public void setSearchAction() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("CheckResult")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("") || s.toString().isEmpty()) {
                    searchList.setVisibility(View.GONE);
                    tab.setVisibility(View.GONE);
                } else {
                    tab.setVisibility(View.GONE);
                    viewPager.setVisibility(View.GONE);
                    container.setVisibility(View.GONE);
                    searchList.setVisibility(View.VISIBLE);
                    client = ApiClient.getInstance();
                    call = client.searchedMeals(s.toString());
                    call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                            subscribe(item -> {
                                // this.arr = item.getCategories();
                                if (item.getMeals() != null) {
                                    arr = item.getMeals();
                                    GridLayoutManager manger = new GridLayoutManager(HomeActivity.this, 2);
                                    manger.setOrientation(RecyclerView.VERTICAL);
                                    searchList.setLayoutManager(manger);
                                    adapter.setList(arr);
                                    muArray.setValue(item.getMeals());
                                    searchList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();

                                    System.out.println(arr.size());
                                } else {

                                    Toast.makeText(HomeActivity.this, "No items", Toast.LENGTH_SHORT).show();
                                    searchList.setVisibility(View.GONE);

                                }

                            }, (error -> error.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void addPlan(Db_Model model) {

    }

    @Override
    public void navToDetails(int id) {
        this.findViewById(R.id.container).setVisibility(View.VISIBLE);
        this.findViewById(R.id.pager).setVisibility(View.GONE);
        this.findViewById(R.id.tablayout).setVisibility(View.GONE);
        searchList.setVisibility(View.GONE);
        search.setText("");
        search.setVisibility(View.GONE);
        this.getSupportFragmentManager().beginTransaction().replace(R.id.container, new DetailsFragment(id)).addToBackStack(null).commit();
    }
}