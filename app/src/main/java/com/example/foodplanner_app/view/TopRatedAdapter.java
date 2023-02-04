package com.example.foodplanner_app.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
                inflate(R.layout.top_meal_item,parent,false);
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
            fav=itemView.findViewById(R.id.top_fav_ic);

        }
    }
}
