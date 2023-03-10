package com.example.foodplanner_app.details.view;

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
import com.example.foodplanner_app.R;
import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.example.foodplanner_app.fav_meals.view.Fav_Meal_Interface;
import com.example.foodplanner_app.meals.view.AddFavClickListener;
import com.example.foodplanner_app.network.remoteSource.Db_Repository;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {

    Context context;
    ArrayList<MealDetailsModel> list;
    DetailsOnClickListener listener;
    ArrayList<String> ingredientsList;
    ArrayList<String> ingredientMeasureList;
    Fav_Meal_Interface fav_meal_interface;
    Db_Repository repository;


    public DetailsAdapter(Context context, ArrayList<MealDetailsModel> list,Fav_Meal_Interface fav_meal_interface) {
        this.context = context;
        this.list = list;
        this.fav_meal_interface = fav_meal_interface;
    }

    public void setList(ArrayList<MealDetailsModel> list) {
        this.list = list;
        getIngerdientsWithMeasures();
        Log.i("bnbnbnnb", "setList size: "+ ingredientsList.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.ingredient_item_two,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ingredientNameTv.setText(ingredientsList.get(holder.getAdapterPosition()));
        holder.ingredientMeasureTv.setText(ingredientMeasureList.get(holder.getAdapterPosition())+"  ");
        holder.mealImg.setImageResource(R.drawable.salt);
            DetailsFragment.meal=list.get(0);
            fav_meal_interface.addFavItem(list.get(0));
    }

    private void getIngerdientsWithMeasures() {
        Log.i("bkrntkbr", "getIngerdientsWithMeasures: " + list.get(0).getIdMeal());
        ingredientsList = new ArrayList<>();
        if (list.get(0).getStrIngredient1()!=null &&!list.get(0).getStrIngredient1().equals("") ) ingredientsList.add(list.get(0).getStrIngredient1());
        if (list.get(0).getStrIngredient2()!=null &&!list.get(0).getStrIngredient2().equals("") ) ingredientsList.add(list.get(0).getStrIngredient2());
        if (list.get(0).getStrIngredient3()!=null &&!list.get(0).getStrIngredient3().equals("") ) ingredientsList.add(list.get(0).getStrIngredient3());
        if (list.get(0).getStrIngredient4()!=null &&!list.get(0).getStrIngredient4().equals("") ) ingredientsList.add(list.get(0).getStrIngredient4());
        if (list.get(0).getStrIngredient5()!=null &&!list.get(0).getStrIngredient5().equals("") ) ingredientsList.add(list.get(0).getStrIngredient5());
        if (list.get(0).getStrIngredient6()!=null &&!list.get(0).getStrIngredient6().equals("") ) ingredientsList.add(list.get(0).getStrIngredient6());
        if (list.get(0).getStrIngredient7()!=null &&!list.get(0).getStrIngredient7().equals("") ) ingredientsList.add(list.get(0).getStrIngredient7());
        if (list.get(0).getStrIngredient8()!=null &&!list.get(0).getStrIngredient8().equals("") ) ingredientsList.add(list.get(0).getStrIngredient8());
        if (list.get(0).getStrIngredient9()!=null &&!list.get(0).getStrIngredient9().equals("") ) ingredientsList.add(list.get(0).getStrIngredient9());
        if (list.get(0).getStrIngredient10()!=null &&!list.get(0).getStrIngredient10().equals("")) ingredientsList.add(list.get(0).getStrIngredient10());
        if (list.get(0).getStrIngredient11()!=null &&!list.get(0).getStrIngredient11().equals("")) ingredientsList.add(list.get(0).getStrIngredient11());
        if (list.get(0).getStrIngredient12()!=null &&!list.get(0).getStrIngredient12().equals("")) ingredientsList.add(list.get(0).getStrIngredient12());
        if (list.get(0).getStrIngredient13()!=null &&!list.get(0).getStrIngredient13().equals("")) ingredientsList.add(list.get(0).getStrIngredient13());
        if (list.get(0).getStrIngredient14()!=null &&!list.get(0).getStrIngredient14().equals("")) ingredientsList.add(list.get(0).getStrIngredient14());
        if (list.get(0).getStrIngredient15()!=null && !list.get(0).getStrIngredient15().equals("")) ingredientsList.add(list.get(0).getStrIngredient15());
        if (list.get(0).getStrIngredient16()!=null && !list.get(0).getStrIngredient16().equals("")) ingredientsList.add((String) list.get(0).getStrIngredient16());
        if ( list.get(0).getStrIngredient17()!=null && !list.get(0).getStrIngredient17().equals("")) ingredientsList.add((String) list.get(0).getStrIngredient17());
        if ( list.get(0).getStrIngredient18()!=null && !list.get(0).getStrIngredient18().equals("")) ingredientsList.add((String) list.get(0).getStrIngredient18());
        if (list.get(0).getStrIngredient19()!=null && !list.get(0).getStrIngredient19().equals("")) ingredientsList.add((String) list.get(0).getStrIngredient19());
        if (list.get(0).getStrIngredient20()!=null && !list.get(0).getStrIngredient20().equals("")) ingredientsList.add((String) list.get(0).getStrIngredient20());

        ingredientMeasureList = new ArrayList<>();
        if (list.get(0).getStrIngredient1()!=null && !list.get(0).getStrIngredient1().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure1());
        if (list.get(0).getStrIngredient2()!=null && !list.get(0).getStrIngredient2().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure2());
        if (list.get(0).getStrIngredient3()!=null && !list.get(0).getStrIngredient3().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure3());
        if (list.get(0).getStrIngredient4()!=null && !list.get(0).getStrIngredient4().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure4());
        if (list.get(0).getStrIngredient5()!=null && !list.get(0).getStrIngredient5().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure5());
        if (list.get(0).getStrIngredient6()!=null && !list.get(0).getStrIngredient6().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure6());
        if (list.get(0).getStrIngredient7()!=null && !list.get(0).getStrIngredient7().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure7());
        if (list.get(0).getStrIngredient8()!=null && !list.get(0).getStrIngredient8().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure8());
        if (list.get(0).getStrIngredient9()!=null && !list.get(0).getStrIngredient9().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure9());
        if (list.get(0).getStrIngredient10()!=null && !list.get(0).getStrIngredient10().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure10());
        if (list.get(0).getStrIngredient11()!=null && !list.get(0).getStrIngredient11().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure11());
        if (list.get(0).getStrIngredient12()!=null && !list.get(0).getStrIngredient12().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure12());
        if (list.get(0).getStrIngredient13()!=null && !list.get(0).getStrIngredient13().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure13());
        if (list.get(0).getStrIngredient14()!=null && !list.get(0).getStrIngredient14().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure14());
        if (list.get(0).getStrIngredient15()!=null && !list.get(0).getStrIngredient15().equals("") ) ingredientMeasureList.add(list.get(0).getStrMeasure15());
        if (list.get(0).getStrIngredient16()!=null && !list.get(0).getStrIngredient16().equals("") ) ingredientMeasureList.add((String) list.get(0).getStrMeasure16());
        if (list.get(0).getStrIngredient17()!=null && !list.get(0).getStrIngredient17().equals("") ) ingredientMeasureList.add((String) list.get(0).getStrMeasure17());
        if (list.get(0).getStrIngredient18()!=null && !list.get(0).getStrIngredient18().equals("") ) ingredientMeasureList.add((String) list.get(0).getStrMeasure18());
        if (list.get(0).getStrIngredient19()!=null && !list.get(0).getStrIngredient19().equals("") ) ingredientMeasureList.add((String) list.get(0).getStrMeasure19());
        if (list.get(0).getStrIngredient20()!=null &&!list.get(0).getStrIngredient20().equals("") ) ingredientMeasureList.add((String) list.get(0).getStrMeasure20());
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout mealLayout;
        TextView ingredientNameTv, ingredientMeasureTv;
        ImageView mealImg,fav;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealLayout = itemView.findViewById(R.id.meal_layout);
            ingredientNameTv = itemView.findViewById(R.id.meal_name);
            mealImg = itemView.findViewById(R.id.circleImg);
            ingredientMeasureTv = itemView.findViewById(R.id.ingredient_measure_tv);

        }
    }
}