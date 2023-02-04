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
import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MyViewHolder> {
    Context context;
    ArrayList<Model> list;

    public MealsAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.meal_item,parent,false);
        Log.i("TAG", "onCreateViewHolder: ");
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mealName.setText(list.get(position).getMealName());
        holder.mealImg.setImageResource(list.get(position).getMealImg());
        holder.fav.setImageResource(list.get(position).getFavImg());
        holder.add.setImageResource(list.get(position).getAddImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        CardView mealCard;
        ImageView fav,add,mealImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.meal_name);
            mealCard=itemView.findViewById(R.id.meal_card);
            mealImg=itemView.findViewById(R.id.circleImg);
            fav=itemView.findViewById(R.id.fav_ic);
            add=itemView.findViewById(R.id.add_ic);


        }
    }
}
