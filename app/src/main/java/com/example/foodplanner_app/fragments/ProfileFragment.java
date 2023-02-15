package com.example.foodplanner_app.fragments;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner_app.R;
import com.example.foodplanner_app.authantication.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    TextView topRated;
    ImageView arrwo;
    SearchView search;
    TextView logoutTv;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logoutTv = view.findViewById(R.id.logout_tv);
        logoutTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HHHHH", "onClick: " );
                deleteId();
                FirebaseAuth.getInstance().signOut();
                navToLogin();
            }
        });
    }

    private void deleteId() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("loggedId", "");
        editor.clear();
        editor.apply();
    }

    public void navToLogin() {
        Intent intent = new Intent(this.getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private String testId(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        return preferences.getString("loggedId", "");
    }
}