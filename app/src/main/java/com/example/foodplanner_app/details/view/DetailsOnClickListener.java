package com.example.foodplanner_app.details.view;

import com.example.foodplanner_app.Data_Base.local_db.model.Db_Model;

public interface DetailsOnClickListener {
    public void navToDetails(int id);
    public void addPlan(Db_Model model);
}
