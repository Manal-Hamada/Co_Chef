package com.example.foodplanner_app.meals.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;
import com.example.foodplanner_app.meals.model.Meal_Model;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder>  {

        Context context;
        ArrayList<Meal_Model> list;
        DetailsOnClickListener listener;
        AddFavClickListener clickListenerFor_db_store;
        UnFavClickListener unFavClickListener;


        public MealAdapter(Context context, ArrayList<Meal_Model> list, DetailsOnClickListener detailsListener, AddFavClickListener clickListenerFor_db_store,UnFavClickListener unFavClickListener) {
            this.context = context;
            this.list = list;
            this.listener = detailsListener;
            this.clickListenerFor_db_store=clickListenerFor_db_store;
            this.unFavClickListener=unFavClickListener;
        }

        public void setList(ArrayList<Meal_Model> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.meal_item_two,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.mealName.setText(list.get(position).getStrMeal());
            holder.fav.setImageResource(R.drawable.fav_outer_ic);
            Glide.with(context).load(list.get(position).getStrMealThumb()).into(holder.mealImg);
            holder.fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 // clickListenerFor_db_store.addFavItem();
                  //  Log.i("ad mealllll  ", ""+list.get(position).getStrMeal().toString());
                }
            });
            holder.mealLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.navToDetails(Integer.parseInt(list.get( holder.getAdapterPosition()).getIdMeal()));
                }
            });
            holder.unFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            ConstraintLayout mealLayout;
            TextView mealName;
            ImageView mealImg,fav,unFav;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                mealLayout = itemView.findViewById(R.id.meal_layout);
                mealName = itemView.findViewById(R.id.meal_name);
                mealImg = itemView.findViewById(R.id.circleImg);
                fav=itemView.findViewById(R.id.meal_fav_ic);
                unFav=itemView.findViewById(R.id.meal_outer_fav_ic);
            }
        }
    }

