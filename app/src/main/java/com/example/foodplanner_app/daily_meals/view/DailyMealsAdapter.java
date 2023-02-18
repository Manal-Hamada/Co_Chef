package com.example.foodplanner_app.daily_meals.view;

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

import com.bumptech.glide.Glide;
import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.models.Model;
import com.example.foodplanner_app.R;

import java.util.ArrayList;

public class DailyMealsAdapter extends RecyclerView.Adapter<DailyMealsAdapter.DayViewHolder> {

    Context context;
    ArrayList<Db_Model> list;
    DetailsOnClickListener listener;

    public DailyMealsAdapter(Context context, ArrayList<Db_Model> list, DetailsOnClickListener listener) {
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
        holder.mealName.setText(list.get(position).getStrMeal());
        Glide.with(context).load(list.get(position).getStrMealThumb()).into(holder.mealImg);
        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.navToDetails(Integer.parseInt(list.get( holder.getAdapterPosition()).getIdMeal()));
            }
        });
    }


    @Override
    public int getItemCount() {

        Log.i("tgnkjrtnbk", "getItemCount: "+list.size());
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
            mealCard=itemView.findViewById(R.id.planed_item_card);
        }
    }

    public void setList(ArrayList<Db_Model> list) {
        this.list = list;
        Log.i("tgnkjrtnbk", "getItemCount: set list = "+list.size());
    }
}


