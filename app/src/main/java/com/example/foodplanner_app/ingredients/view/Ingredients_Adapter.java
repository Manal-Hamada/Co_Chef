package com.example.foodplanner_app.ingredients.view;

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
import com.example.foodplanner_app.ingredients.model.IngredientModel;

import java.util.ArrayList;

public class Ingredients_Adapter extends RecyclerView.Adapter<Ingredients_Adapter.MyViewHolder> {
    Context context;
    ArrayList<IngredientModel> list;
    IngredientsOnClickListener ingredientsOnClickListener;


    public Ingredients_Adapter(Context context, ArrayList<IngredientModel> list,IngredientsOnClickListener ingredientsOnClickListener) {
        this.context = context;
        this.list = list;
        this.ingredientsOnClickListener=ingredientsOnClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.ingredient_item_two,parent,false);
        return new Ingredients_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("TAG", "onBindViewHolder: ");
        holder.ingredientName.setText(list.get(holder.getAdapterPosition()).getStrIngredient());
        Log.i("TAG", "onBindViewHolder:"+ list.get(holder.getAdapterPosition()).getStrIngredient());
        Glide.with(context).load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png",list.get(holder.getAdapterPosition()).getStrIngredient())).into(holder.ingredientImg);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ingredientsOnClickListener.navToMeals(list.get( holder.getAdapterPosition()).getStrIngredient(),3);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName;
        ImageView ingredientImg;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName=itemView.findViewById(R.id.ingredient_name);
            ingredientImg=itemView.findViewById(R.id.ingredient_img);
            card =itemView.findViewById(R.id.ingredient_card);
        }
    }

    public void setList(ArrayList<IngredientModel> list) {
        this.list = list;
    }
}
