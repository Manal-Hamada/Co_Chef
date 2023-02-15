package com.example.foodplanner_app.app_activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.foodplanner_app.category_meals.view.CategoryFr;
import com.example.foodplanner_app.country_meals.view.CountryFr;
import com.example.foodplanner_app.ingredients.view.IngredienstFr;

public class MyPageAdapter extends FragmentStatePagerAdapter {

    public MyPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CategoryFr();
            case 1:
                return new CountryFr();
            case 2:
                return new IngredienstFr();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Category";
            case 1:
                return "Country";
            case 2:
                return "Ingredients";
            default:
                return null;
        }
    }
}
