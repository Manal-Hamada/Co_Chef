package com.example.foodplanner_app.daily_meals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.daily_meals.repository.Daily_Repository;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.details.view.DetailsFragment;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.fav_meals.repository.Repository;
import com.example.foodplanner_app.models.Model;
import com.example.foodplanner_app.models.Utilities;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DailyPlanFragment extends Fragment implements DetailsOnClickListener {

    RecyclerView recycler;
    DailyMealsAdapter adapter;
    ArrayList<Db_Model> arr;
    TextView topRated;
    ImageView arrwo;
    SearchView search;
    Db_Repository repo;
    CalendarView calendarView;
    String selectedDate;
    Daily_Repository dailyRepo;
    public DailyPlanFragment() {
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
        return inflater.inflate(R.layout.fragment_daily_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arr = new ArrayList<>();
        search = getActivity().findViewById(R.id.search_bar);
        setRecycler();
        search=getActivity().findViewById(R.id.search_bar);
        calendarView = view.findViewById(R.id.daily_calender);
        search.setVisibility(View.GONE);
        dailyRepo= Daily_Repository.getInstance(getActivity());
        setSelectedDate();
        Log.i("jrsvjhfb", "onViewCreated: "+ setSelectedDate());

      // dailyRepo.getAllMealls(getActivity());

    }

    public String setSelectedDate() {
        final Calendar date = Calendar.getInstance();
        date.getTime();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String s;
                date.set(year, month, dayOfMonth);
                s=DateFormat.getDateInstance(DateFormat.DEFAULT).format(date.getTime());//.compareTo()
                Log.i("tgnkjrtnbk", "onSelectedDayChangedededed: ");

                if (FirebaseAuth.getInstance().getUid() == null)
                    Utilities.showDialog(getContext(),"please login first!", getActivity());
                else {
                dailyRepo.getAllMealls(getActivity(),s).observe(getViewLifecycleOwner(), new Observer<List<Db_Model>>() {
                    @Override
                    public void onChanged(List<Db_Model> mealDetailsModels) {
                        arr.clear();
                        arr = (ArrayList<Db_Model>) mealDetailsModels;
                        Log.i("tgnkjrtnbk", "arr size: "+arr.size());
                        adapter.setList(arr);
                        adapter.notifyDataSetChanged();
                        recycler.setAdapter(adapter);
                        //TODO handle the adapter
                        Log.i("vkjtnkjvrnjt", "onChanged: "+mealDetailsModels.size());

                    }
                });
            }}
        });
        //calendarView.setOn
        selectedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date.getTime());//.compareTo()
        return selectedDate;
    }

    public void setRecycler() {

        recycler = requireView().findViewById(R.id.day_list);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manger=new LinearLayoutManager(getActivity());
        manger.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(manger);
        adapter = new DailyMealsAdapter(getActivity(), arr, this);
    }
    @Override
    public void navToDetails(int id) {
            getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.pager).setVisibility(View.GONE);
            getActivity().findViewById(R.id.tablayout).setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            topRated.setVisibility(View.GONE);
            arrwo.setVisibility(View.GONE);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new DetailsFragment()).addToBackStack(null).commit();
    }
    @Override
    public void addPlan(Db_Model model) {

    }
    public void setAddFab(){

    }
}

