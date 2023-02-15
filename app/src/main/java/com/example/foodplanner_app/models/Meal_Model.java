package com.example.foodplanner_app.models;

public class Meal_Model {

    int id;
    String mealName,category,area,steps,imgUrl,videoUrl,ingredients,measure;
    boolean hasDate,isfavourite;

    public Meal_Model(int id, String mealName, String category, String area, String steps,
                      String imgUrl, String videoUrl, String ingredients, String measure, boolean hasDate, boolean isfavourite) {
        this.id = id;
        this.mealName = mealName;
        this.category = category;
        this.area = area;
        this.steps = steps;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.ingredients = ingredients;
        this.measure = measure;
        this.hasDate = hasDate;
        this.isfavourite = isfavourite;
    }

    public Meal_Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public boolean isHasDate() {
        return hasDate;
    }

    public void setHasDate(boolean hasDate) {
        this.hasDate = hasDate;
    }

    public boolean isIsfavourite() {
        return isfavourite;
    }

    public void setIsfavourite(boolean isfavourite) {
        this.isfavourite = isfavourite;
    }
}
