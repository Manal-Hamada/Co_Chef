package com.example.foodplanner_app.category_meals.model;

import java.util.ArrayList;

public class Category_Response  {

    ArrayList<Category_Model> categories;

    public Category_Response(ArrayList<Category_Model> categories) {
        this.categories = categories;
    }

    public ArrayList<Category_Model> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category_Model> categories) {
        this.categories = categories;
    }
}

