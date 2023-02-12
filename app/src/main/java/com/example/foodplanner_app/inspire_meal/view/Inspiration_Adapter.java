package com.example.foodplanner_app.inspire_meal.view;

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
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.ingredients.model.IngredientModel;
import com.example.foodplanner_app.ingredients.view.IngredientsOnClickListener;
import com.example.foodplanner_app.inspire_meal.model.Inspirational_Model;


import java.util.ArrayList;

public class Inspiration_Adapter extends RecyclerView.Adapter<Inspiration_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Inspirational_Model> list;
    DetailsOnClickListener detailsOnClickListener;


    public Inspiration_Adapter(Context context, ArrayList<Inspirational_Model> list,DetailsOnClickListener detailsOnClickListener ) {
        this.context = context;
        this.list = list;
        this.detailsOnClickListener=detailsOnClickListener;

    }

    @NonNull
    @Override
    public Inspiration_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.inspirational_item,parent,false);
        return new Inspiration_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Inspiration_Adapter.MyViewHolder holder, int position) {
        Log.i("TAG", "onBindViewHolder: ");
        holder.mealName.setText(list.get(position).getStrMeal());
        holder.category.setText(list.get(position).getStrCategory());
        holder.country.setText(list.get(position).getStrArea());
       // Glide.with(context).load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png",list.get(position).getStrMealThumb())).into(holder.inspirationalImg);
        Glide.with(context).load(list.get(position).getStrMealThumb()).into(holder.inspirationalImg);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                detailsOnClickListener.navToDetails(Integer.parseInt(list.get( holder.getAdapterPosition()).getIdMeal()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mealName,category,country;
        ImageView inspirationalImg;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.ins_meal_name);
            category=itemView.findViewById(R.id.ins_category);
            country=itemView.findViewById(R.id.ins_country);
            inspirationalImg=itemView.findViewById(R.id.ins_meal_img);
            card =itemView.findViewById(R.id.ins_card);
        }
    }

    public void setList(ArrayList<Inspirational_Model> list) {
        this.list = list;
    }
    
}
