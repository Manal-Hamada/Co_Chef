package com.example.foodplanner_app.app_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.foodplanner_app.R;

import com.example.foodplanner_app.databinding.ActivityHomeBinding;
import com.example.foodplanner_app.category_meals.view.CategoryFr;
import com.example.foodplanner_app.fragments.ProfileFragment;
import com.example.foodplanner_app.daily_meals.view.DailyPlanFragment;
import com.example.foodplanner_app.fav_meals.view.FavouritFragment;
import com.example.foodplanner_app.inspire_meal.view.Home_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;
    static TabLayout  tab;
    ViewPager viewPager;
    MyPageAdapter myPagerAdapter;
    ActivityHomeBinding binding;
    FragmentContainerView container;
    SearchView search;
    String mode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i =getIntent();
        mode=i.getStringExtra("mode");
        replaceFragments(new Home_Fragment());
        init();
       // setTabLayout();
        setTabSelectedListiner();
        setBottomNav();

    }

    public void init(){
        tab=findViewById(R.id.tablayout);
        container= findViewById(R.id.container);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.pager);
        myPagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setVisibility(View.GONE);
        setTabLayout();

        search=findViewById(R.id.search_bar);
        search.setVisibility(View.GONE);

    }

    public void replaceFragments(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

    }
    public void setTabLayout(){
        viewPager.setAdapter(myPagerAdapter);
        tab.setupWithViewPager(viewPager);
    }

    public void setTabSelectedListiner(){
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
                 switch (position){
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
            if(mode!="guest")
                userMode(item);
            else
              guestMode(item);

            return true;
        });
    }

    public void hideCategoryTexts(){

        search.setVisibility(View.GONE);
    }
    public void showCategoryTexts(){
        search.setVisibility(View.VISIBLE);
    }

    public void userMode(MenuItem item){
        switch (item.getItemId( )) {

            case R.id.fav:
                viewPager.setVisibility(View.GONE);
                tab.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                replaceFragments(new FavouritFragment());
                break;
            case R.id.menu:
                tab.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                replaceFragments(new DailyPlanFragment());
                break;
            case R.id.profile:
                viewPager.setVisibility(View.GONE);
                tab.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
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
    public void guestMode(MenuItem item){
        switch (item.getItemId( )) {

            case R.id.fav:
                tab.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
                replaceFragments(new FavouritFragment());
                break;
            case R.id.menu:
                tab.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                hideCategoryTexts();
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
}