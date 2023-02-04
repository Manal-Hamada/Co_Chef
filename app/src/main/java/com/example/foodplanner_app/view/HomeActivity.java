package com.example.foodplanner_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.foodplanner_app.Model;
import com.example.foodplanner_app.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TabLayout tab;
    Home_Fragment homeFragment;
    DailyPlanFragment menuFragment ;
    FavouritFragment favouritFragmentn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setTabLayout();
        //setFragmentTrasaction();


    }

    public void init(){
        Home_Fragment homeFragment = new Home_Fragment();
        DailyPlanFragment menuFragment = new DailyPlanFragment();
        FavouritFragment favouritFragmentn = new FavouritFragment();
        tab=findViewById(R.id.tablayout);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);

    }
    public void setFragmentTrasaction(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,menuFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.home);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);
    }
    public void setTabLayout(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPageAdapter myPagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        tab.setupWithViewPager(viewPager);
    }


}