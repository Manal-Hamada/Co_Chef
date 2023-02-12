package com.example.foodplanner_app.fav_meals.view;

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
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavViewHolder> {

    Context context;
    ArrayList<MealDetailsModel> list;
    DetailsOnClickListener detailsOnClickListener;
    Fav_Meal_Interface fav_meal_interface;

    public FavouriteAdapter(Context context, ArrayList<MealDetailsModel> list,DetailsOnClickListener detailsOnClickListener,Fav_Meal_Interface fav_meal_interface) {
        this.context = context;
        this.list = list;
        this.detailsOnClickListener=detailsOnClickListener;
        this.fav_meal_interface=fav_meal_interface;
    }
    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fav_list_item,parent,false);
        Log.i("TAG", "onCreateViewHolder: ");
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        holder.mealName.setText(list.get(position).getStrMeal());
        Glide.with(context).load(list.get(position).getStrMealThumb()).into(holder.mealImg);
        holder.delete.setImageResource(R.drawable.delete_ic);
        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //detailsOnClickListener.navToDetails(holder.);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fav_meal_interface.deleteMeal(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        CardView mealCard;
        ImageView delete,mealImg;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.fav_meal_name);
            mealImg=itemView.findViewById(R.id.fav_meal_img);
            delete=itemView.findViewById(R.id.fav_delete_ic);
            mealCard=itemView.findViewById(R.id.fav_cardView);
        }
    }
    public void setList(ArrayList<MealDetailsModel> list) {
        this.list = list;
    }
}
