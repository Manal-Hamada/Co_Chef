package com.example.foodplanner_app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.view.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        signUp=findViewById(R.id.signup_tv);
        setLoginBtn();
        setsignup_tv();

    }
    public void setsignup_tv(){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUp_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void setLoginBtn(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("loggedToken", "id:12345012");
                editor.apply();

                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}