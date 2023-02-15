package com.example.foodplanner_app.country_meals.view;

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
import com.example.foodplanner_app.category_meals.model.Category_Model;
import com.example.foodplanner_app.category_meals.view.CategoryAdapter;
import com.example.foodplanner_app.country_meals.model.Country_Model;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;

import java.util.ArrayList;

public class Country_Adapter extends RecyclerView.Adapter<Country_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Country_Model> list;
    CountryOnClickListener countryOnClickListener;


    public Country_Adapter(Context context, ArrayList<Country_Model> list,CountryOnClickListener countryOnClickListener) {
        this.context = context;
        this.list = list;
        this.countryOnClickListener=countryOnClickListener;

    }


    @NonNull
    @Override
    public Country_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.country_item,parent,false);
        return new Country_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Country_Adapter.MyViewHolder holder, int position) {
        Log.i("TAG", "onBindViewHolder: ");
        holder.mealName.setText(list.get(position).getStrArea());
        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOnClickListener.navToMeals(list.get( holder.getAdapterPosition()).getStrArea(),2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        CardView mealCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.top_meal_name);
            mealCard=itemView.findViewById(R.id.category_item_card);
        }
    }

    public void setList(ArrayList<Country_Model> list) {
        this.list = list;
    }

}
