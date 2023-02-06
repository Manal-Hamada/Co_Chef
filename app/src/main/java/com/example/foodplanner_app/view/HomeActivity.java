package com.example.foodplanner_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner_app.Model;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.databinding.ActivityHomeBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;
    static TabLayout  tab;
    ViewPager viewPager;
    MyPageAdapter myPagerAdapter;
    ActivityHomeBinding binding;
    FragmentContainerView container;
    TextView topRated;
    ImageView arrwo;
    SearchView search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        viewPager.setAdapter(myPagerAdapter);
        tab.setupWithViewPager(viewPager);

        topRated=findViewById(R.id.top_txt);
        arrwo=findViewById(R.id.Arr);
        search=findViewById(R.id.search_bar);
        topRated.setVisibility(View.VISIBLE);
        arrwo.setVisibility(View.VISIBLE);
        search.setVisibility(View.VISIBLE);

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

    public void setBottomNav(){
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            viewPager.setVisibility(View.GONE);
            container.setVisibility(View.VISIBLE);
            switch (item.getItemId()){

                case R.id.fav:
                    viewPager.setVisibility(View.GONE);
                    tab.setVisibility(View.GONE);
                    hideCategoryTexts();
                    replaceFragments(new FavouritFragment());
                    break;
                case R.id.menu:
                    tab.setVisibility(View.GONE);
                    viewPager.setVisibility(View.GONE);
                    hideCategoryTexts();
                    replaceFragments(new DailyPlanFragment());
                    break;
                case R.id.profile:
                    viewPager.setVisibility(View.GONE);
                    tab.setVisibility(View.GONE);
                    hideCategoryTexts();
                    replaceFragments(new ProfileFragment());
                    break;
                case  R.id.home:
                     viewPager.setVisibility(View.VISIBLE);
                     tab.setVisibility(View.VISIBLE);
                     viewPager.getCurrentItem();
                     showCategoryTexts();
                     container.setVisibility(View.GONE);

                    break;

            }
            return true;
        });
    }

    public void hideCategoryTexts(){
        topRated.setVisibility(View.GONE);
        arrwo.setVisibility(View.GONE);
        search.setVisibility(View.GONE);
    }
    public void showCategoryTexts(){
        topRated.setVisibility(View.VISIBLE);
        arrwo.setVisibility(View.VISIBLE);
        search.setVisibility(View.VISIBLE);
    }

}