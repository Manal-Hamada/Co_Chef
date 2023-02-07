package com.example.foodplanner_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.view.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    TextView skip_tv;
    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout onBoardingIndicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        onBoardingIndicators = findViewById(R.id.layoutOnBoardingIndicators);

        setOnBoardingAdapter();

        ViewPager2 onBoardingViewPager = findViewById(R.id.onBoardingViewPager);
        onBoardingViewPager.setAdapter(onBoardingAdapter);

        setOnBoardingIndicators();
        setCurrentOnBoardingIndicators(0);

        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicators(position);
            }
        });

        skip_tv = findViewById(R.id.skip_on_boarding);
        skip_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnboardingActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void setOnBoardingAdapter(){
        List<OnBoardingItem> onBoardingItemList = new ArrayList<>();

        OnBoardingItem item1 = new OnBoardingItem();
        item1.setTitle("Follow the meals steps");
        item1.setImage(R.drawable.onboarding_one);

        OnBoardingItem item2 = new OnBoardingItem();
        item2.setTitle("You are the CHEF");
        item2.setImage(R.drawable.onboarding_two);

        OnBoardingItem item3 = new OnBoardingItem();
        item3.setTitle("Discover the best meals");
        item3.setImage(R.drawable.onboarding_three);

        onBoardingItemList.add(item1);
        onBoardingItemList.add(item2);
        onBoardingItemList.add(item3);

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItemList);
    }

    public void setOnBoardingIndicators() {
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i=0;i<indicators.length;i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            onBoardingIndicators.addView(indicators[i]);
        }
    }

    public void setCurrentOnBoardingIndicators(int index) {
        int childCount = onBoardingIndicators.getChildCount();
        for (int i=0;i<childCount;i++){
            ImageView imageView = (ImageView) onBoardingIndicators.getChildAt(i);
            if(i==index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active));
            }else
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
        }
    }
}