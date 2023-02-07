package com.example.foodplanner_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder> {

    private List<OnBoardingItem> onBoardingItems;
    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItems){
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    class OnBoardingViewHolder extends RecyclerView.ViewHolder{

        private ImageView onBoardingImageView;
        private TextView titleTextView, descriptionTextview;
        public OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.onBoardingTitleTV);
            descriptionTextview = itemView.findViewById(R.id.onBoardingDescriptionTV);
            onBoardingImageView = itemView.findViewById(R.id.onBoardingImageview);
        }
        void setOnBoardingData(OnBoardingItem onBoardingItem){
            titleTextView.setText(onBoardingItem.getTitle());
            descriptionTextview.setText(onBoardingItem.getDescription());
            onBoardingImageView.setImageResource(onBoardingItem.getImage());
        }
    }
}
