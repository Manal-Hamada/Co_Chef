package com.example.foodplanner_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentManager;
import android.os.Bundle;

import com.example.foodplanner_app.Model;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.databinding.ActivityHomeBinding;
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
    ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setTabLayout();
        //setFragmentTrasaction();


    }

    public void init(){
      /*  Home_Fragment homeFragment = new Home_Fragment();
        DailyPlanFragment menuFragment = new DailyPlanFragment();
        FavouritFragment favouritFragmentn = new FavouritFragment();*/
        tab=findViewById(R.id.tablayout);
       // bottomNavigationView=findViewById(R.id.bottomNavigationView);
        binding.bottomNavigationView.setOnItemReselectedListener(item -> {
            switch (item.getItemId()){

                case R.id.fav:
                     replaceFragments(new Home_Fragment());
                    break;
                case R.id.menu:
                    replaceFragments(new DailyPlanFragment());
                    break;
                case R.id.profile:
                    replaceFragments(new DailyPlanFragment());
                    break;
                case  R.id.home:
                    replaceFragments(new Home_Fragment());
                    break;

            }
        });

    }

    public void replaceFragments(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_tab_lay_out,fragment).commit();

    }
    public void setTabLayout(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPageAdapter myPagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        tab.setupWithViewPager(viewPager);
    }


}