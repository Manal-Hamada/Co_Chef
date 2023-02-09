package com.example.foodplanner_app.category_meals.view;

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
import com.example.foodplanner_app.category_meals.model.Category_Model;
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.view.DetailsOnClickListener;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<Category_Model> list;
    DetailsOnClickListener listener;

    public CategoryAdapter(Context context, ArrayList<Category_Model> list, DetailsOnClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener= listener;

    }

    public void setList(ArrayList<Category_Model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.category_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("TAG", "onBindViewHolder: ");
        holder.mealName.setText(list.get(position).getStrCategory());
        Glide.with(context).load(list.get(position).getStrCategoryThumb()).into(holder.mealImg);
        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        ImageView mealImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.top_meal_name);
            mealCard=itemView.findViewById(R.id.category_item_card);
            mealImg=itemView.findViewById(R.id.top_meal_img);



        }
    }
}
