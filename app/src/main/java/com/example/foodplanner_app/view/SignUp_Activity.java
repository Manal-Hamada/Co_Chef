package com.example.foodplanner_app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner_app.R;

public class SignUp_Activity extends AppCompatActivity {

    TextView loginTv;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginTv = findViewById(R.id.login_tv);
        signIn = findViewById(R.id.signupBtn);
        setSignInAction();
        setLoginTvAction();

    }

    public void setLoginTvAction() {

        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToLogin();
            }
        });
    }

    public void navToLogin() {
        Intent intent = new Intent(SignUp_Activity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void setSignInAction() {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToLogin();
            }
        });


    }
}