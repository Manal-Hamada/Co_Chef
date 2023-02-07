package com.example.foodplanner_app.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner_app.R;

public class ProfileFragment extends Fragment {

    TextView topRated;
    ImageView arrwo;
    SearchView search;

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
        topRated=getActivity().findViewById(R.id.top_txt);
        arrwo=getActivity().findViewById(R.id.Arr);
        search=getActivity().findViewById(R.id.search_bar);
        search.setVisibility(View.GONE);
        topRated.setVisibility(View.GONE);
        arrwo.setVisibility(View.GONE);

    }
}