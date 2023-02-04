package com.example.foodplanner_app.view;

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

import com.example.foodplanner_app.Model;
import com.example.foodplanner_app.R;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavViewHolder> {

    Context context;
    ArrayList<Model> list;

    public FavouriteAdapter(Context context, ArrayList<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.meal_item,parent,false);
        Log.i("TAG", "onCreateViewHolder: ");
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        Log.i("TAG", "from on bind view Holder"+list.size());
        Log.i("TAG", "from on bind view Holder"+list.get(0).getMealName());
       // Log.i("TAG", "from on bind view Holder"+list.get(0).getMealImg());
     // holder.mealImg.setImageResource(list.get(position).getMealImg());
      holder.mealName.setText(list.get(position).getMealName());
      holder.add.setImageResource(list.get(position).getAddImg());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        CardView mealCard;
        ImageView fav,add,mealImg;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.fav_meal_name);
            mealImg=itemView.findViewById(R.id.fav_meal_img);
            add=itemView.findViewById(R.id.fav_add_ic);

        }
    }
}
