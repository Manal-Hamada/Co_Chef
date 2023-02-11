package com.example.foodplanner_app.app_activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.example.foodplanner_app.view.OnBoardingItem;
import com.example.foodplanner_app.view.OnboardingActivity;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.authantication.LoginActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkFirstTime();
                checkLoggedIn();
            }
        },1000);
    }

    private void checkLoggedIn() {
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String loggedToken = preferences.getString("loggedId", "");

        if (loggedToken.equals("")){
            startActivity(new Intent(SplashActivity.this, OnboardingActivity.class));
            finish();
        }
        else{
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        }
    }

    private void checkFirstTime() {
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String firstTime = preferences.getString("FirstTimeInstall", "");

        if (firstTime.equals("no")){
            Intent signUpIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(signUpIntent);
            finish();
        }
        else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall", "no");
            editor.apply();
        }
    }
}