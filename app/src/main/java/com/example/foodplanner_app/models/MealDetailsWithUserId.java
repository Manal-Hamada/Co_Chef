package com.example.foodplanner_app.models;

import com.example.foodplanner_app.details.model.MealDetailsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealDetailsWithUserId {

    private String isFav;
    private String hasDate;

    public String getIsFav() {
        return isFav;
    }

    public void setIsFav(String isFav) {
        this.isFav = isFav;
    }

    public void setHasDate(String hasDate) {
        this.hasDate = hasDate;
    }

    public String getHasDate() {
        return hasDate;
    }

    public MealDetailsWithUserId(MealDetailsModel mealDetailsModel, String idUser) {
        this.mealDetailsModel = mealDetailsModel;
        this.idUser = idUser;
    }

    private MealDetailsModel mealDetailsModel;

    public MealDetailsModel getMealDetailsModel() {
        return mealDetailsModel;
    }

    public void setMealDetailsModel(MealDetailsModel mealDetailsModel) {
        this.mealDetailsModel = mealDetailsModel;
    }

    private String idUser;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @SerializedName("idMeal")
    @Expose
    private String idMeal;
    @SerializedName("strMeal")
    @Expose
    private String strMeal;
    @SerializedName("strDrinkAlternate")
    @Expose
    private String strDrinkAlternate;
    @SerializedName("strCategory")
    @Expose
    private String strCategory;
    @SerializedName("strArea")
    @Expose
    private String strArea;
    @SerializedName("strInstructions")
    @Expose
    private String strInstructions;
    @SerializedName("strMealThumb")
    @Expose
    private String strMealThumb;
    @SerializedName("strTags")
    @Expose
    private String strTags;
    @SerializedName("strYoutube")
    @Expose
    private String strYoutube;
    @SerializedName("strIngredient1")
    @Expose
    private String strIngredient1;
    @SerializedName("strIngredient2")
    @Expose
    private String strIngredient2;
    @SerializedName("strIngredient3")
    @Expose
    private String strIngredient3;
    @SerializedName("strIngredient4")
    @Expose
    private String strIngredient4;
    @SerializedName("strIngredient5")
    @Expose
    private String strIngredient5;
    @SerializedName("strIngredient6")
    @Expose
    private String strIngredient6;
    @SerializedName("strIngredient7")
    @Expose
    private String strIngredient7;
    @SerializedName("strIngredient8")
    @Expose
    private String strIngredient8;
    @SerializedName("strIngredient9")
    @Expose
    private String strIngredient9;
    @SerializedName("strIngredient10")
    @Expose
    private String strIngredient10;
    @SerializedName("strIngredient11")
    @Expose
    private String strIngredient11;
    @SerializedName("strIngredient12")
    @Expose
    private String strIngredient12;
    @SerializedName("strIngredient13")
    @Expose
    private String strIngredient13;
    @SerializedName("strIngredient14")
    @Expose
    private String strIngredient14;
    @SerializedName("strIngredient15")
    @Expose
    private String strIngredient15;
    @SerializedName("strIngredient16")
    @Expose
    private String strIngredient16;
    @SerializedName("strIngredient17")
    @Expose
    private String strIngredient17;
    @SerializedName("strIngredient18")
    @Expose
    private String strIngredient18;
    @SerializedName("strIngredient19")
    @Expose
    private String strIngredient19;
    @SerializedName("strIngredient20")
    @Expose
    private String strIngredient20;
    @SerializedName("strMeasure1")
    @Expose
    private String strMeasure1;
    @SerializedName("strMeasure2")
    @Expose
    private String strMeasure2;
    @SerializedName("strMeasure3")
    @Expose
    private String strMeasure3;
    @SerializedName("strMeasure4")
    @Expose
    private String strMeasure4;
    @SerializedName("strMeasure5")
    @Expose
    private String strMeasure5;
    @SerializedName("strMeasure6")
    @Expose
    private String strMeasure6;
    @SerializedName("strMeasure7")
    @Expose
    private String strMeasure7;
    @SerializedName("strMeasure8")
    @Expose
    private String strMeasure8;
    @SerializedName("strMeasure9")
    @Expose
    private String strMeasure9;
    @SerializedName("strMeasure10")
    @Expose
    private String strMeasure10;
    @SerializedName("strMeasure11")
    @Expose
    private String strMeasure11;
    @SerializedName("strMeasure12")
    @Expose
    private String strMeasure12;
    @SerializedName("strMeasure13")
    @Expose
    private String strMeasure13;
    @SerializedName("strMeasure14")
    @Expose
    private String strMeasure14;
    @SerializedName("strMeasure15")
    @Expose
    private String strMeasure15;
    @SerializedName("strMeasure16")
    @Expose
    private String strMeasure16;
    @SerializedName("strMeasure17")
    @Expose
    private String strMeasure17;
    @SerializedName("strMeasure18")
    @Expose
    private String strMeasure18;
    @SerializedName("strMeasure19")
    @Expose
    private String strMeasure19;
    @SerializedName("strMeasure20")
    @Expose
    private String strMeasure20;
    @SerializedName("strSource")
    @Expose
    private String strSource;
    @SerializedName("strImageSource")
    @Expose
    private String strImageSource;
    @SerializedName("strCreativeCommonsConfirmed")
    @Expose
    private String strCreativeCommonsConfirmed;
    @SerializedName("dateModified")
    @Expose
    private String dateModified;

    public String getIdMeal() {
        return mealDetailsModel.getIdMeal();
    }

    public String getStrMeal() {
        return mealDetailsModel.getStrMeal();
    }

    public String getStrDrinkAlternate() {
        return mealDetailsModel.getStrDrinkAlternate();
    }

    public String getStrCategory() {
        return mealDetailsModel.getStrCategory();
    }

    public String getStrArea() {
        return mealDetailsModel.getStrArea();
    }

    public String getStrInstructions() {
        return mealDetailsModel.getStrInstructions();
    }

    public String getStrMealThumb() {
        return mealDetailsModel.getStrMealThumb();
    }

    public String getStrTags() {
        return mealDetailsModel.getStrTags();
    }

    public String getStrYoutube() {
        return mealDetailsModel.getStrYoutube();
    }

    public String getStrIngredient1() {
        return mealDetailsModel.getStrIngredient1();
    }

    public String getStrIngredient2() {
        return mealDetailsModel.getStrIngredient2();
    }

    public String getStrIngredient3() {
        return mealDetailsModel.getStrIngredient3();
    }

    public String getStrIngredient4() {
        return mealDetailsModel.getStrIngredient4();
    }

    public String getStrIngredient5() {
        return mealDetailsModel.getStrIngredient5();
    }

    public String getStrIngredient6() {
        return mealDetailsModel.getStrIngredient6();
    }

    public String getStrIngredient7() {
        return mealDetailsModel.getStrIngredient7();
    }

    public String getStrIngredient8() {
        return mealDetailsModel.getStrIngredient8();
    }

    public String getStrIngredient9() {
        return mealDetailsModel.getStrIngredient9();
    }

    public String getStrIngredient10() {
        return mealDetailsModel.getStrIngredient10();
    }

    public String getStrIngredient11() {
        return mealDetailsModel.getStrIngredient11();
    }

    public String getStrIngredient12() {
        return mealDetailsModel.getStrIngredient12();
    }

    public String getStrIngredient13() {
        return mealDetailsModel.getStrIngredient13();
    }

    public String getStrIngredient14() {
        return mealDetailsModel.getStrIngredient14();
    }

    public String getStrIngredient15() {
        return mealDetailsModel.getStrIngredient15();
    }

    public String getStrIngredient16() {
        return mealDetailsModel.getStrIngredient16();
    }

    public String getStrIngredient17() {
        return mealDetailsModel.getStrIngredient17();
    }

    public String getStrIngredient18() {
        return mealDetailsModel.getStrIngredient18();
    }

    public String getStrIngredient19() {
        return mealDetailsModel.getStrIngredient19();
    }

    public String getStrIngredient20() {
        return mealDetailsModel.getStrIngredient20();
    }

    public String getStrMeasure1() {
        return mealDetailsModel.getStrMeasure1();
    }

    public String getStrMeasure2() {
        return mealDetailsModel.getStrMeasure2();
    }

    public String getStrMeasure3() {
        return mealDetailsModel.getStrMeasure3();
    }

    public String getStrMeasure4() {
        return mealDetailsModel.getStrMeasure4();
    }

    public String getStrMeasure5() {
        return mealDetailsModel.getStrMeasure5();
    }

    public String getStrMeasure6() {
        return mealDetailsModel.getStrMeasure6();
    }

    public String getStrMeasure7() {
        return mealDetailsModel.getStrMeasure7();
    }

    public String getStrMeasure8() {
        return mealDetailsModel.getStrMeasure8();
    }

    public String getStrMeasure9() {
        return mealDetailsModel.getStrMeasure9();
    }

    public String getStrMeasure10() {
        return mealDetailsModel.getStrMeasure10();
    }

    public String getStrMeasure11() {
        return mealDetailsModel.getStrMeasure11();
    }

    public String getStrMeasure12() {
        return mealDetailsModel.getStrMeasure12();
    }

    public String getStrMeasure13() {
        return mealDetailsModel.getStrMeasure13();
    }

    public String getStrMeasure14() {
        return mealDetailsModel.getStrMeasure14();
    }

    public String getStrMeasure15() {
        return mealDetailsModel.getStrMeasure15();
    }

    public String getStrMeasure16() {
        return mealDetailsModel.getStrMeasure16();
    }

    public String getStrMeasure17() {
        return mealDetailsModel.getStrMeasure17();
    }

    public String getStrMeasure18() {
        return mealDetailsModel.getStrMeasure18();
    }

    public String getStrMeasure19() {
        return mealDetailsModel.getStrMeasure19();
    }

    public String getStrMeasure20() {
        return mealDetailsModel.getStrMeasure20();
    }

    public String getStrSource() {
        return mealDetailsModel.getStrSource();
    }

    public String getStrImageSource() {
        return mealDetailsModel.getStrImageSource();
    }

    public String getStrCreativeCommonsConfirmed() {
        return mealDetailsModel.getStrCreativeCommonsConfirmed();
    }

    public String getDateModified() {
        return mealDetailsModel.getDateModified();
    }
}