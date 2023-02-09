package com.example.foodplanner_app.app_activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner_app.models.Model;
import com.example.foodplanner_app.R;

import java.util.ArrayList;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.TopViewHolder> {
    Context context;
    ArrayList<Model> list;


    public TopRatedAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.category_item,parent,false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {
       holder.mealImg.setImageResource(list.get(position).getMealImg());
       holder.mealName.setText(list.get(position).getMealName());
       holder.fav.setImageResource(list.get(position).getFavImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TopViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        ImageView fav,mealImg;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.top_meal_name);
            mealImg=itemView.findViewById(R.id.top_meal_img);


        }
    }
}
