package com.example.foodplanner_app.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner_app.Model;
import com.example.foodplanner_app.R;

import java.util.ArrayList;

public class DailyMealsAdapter extends RecyclerView.Adapter<DailyMealsAdapter.DayViewHolder> {

    Context context;
    ArrayList<Model> list;
    DetailsOnClickListener listener;

    public DailyMealsAdapter(Context context, ArrayList<Model> list, DetailsOnClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.day_meal_item,parent,false);
        Log.i("TAG", "onCreateViewHolder: ");
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder,int position) {
        holder.mealName.setText(list.get(position).getMealName());
        holder.fav.setImageResource(list.get(position).getFavImg());
        holder.mealImg.setImageResource(list.get(position).getMealImg());
        holder.date.setText(list.get(position).getDate().toString());
        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.navToDetails();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {

        TextView mealName,date;
        CardView mealCard;
        ImageView fav,mealImg;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.day_meal_name);
            mealImg=itemView.findViewById(R.id.day_meal_img);
            fav=itemView.findViewById(R.id.day_fav_ic);
            mealCard=itemView.findViewById(R.id.day_cardView);
            date=itemView.findViewById(R.id.meal_date);
        }
    }
}


