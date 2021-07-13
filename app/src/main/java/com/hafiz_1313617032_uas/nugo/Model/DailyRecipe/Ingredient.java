package com.hafiz_1313617032_uas.nugo.Model.DailyRecipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("foodCategory")
    @Expose
    private String foodCategory;
    @SerializedName("foodId")
    @Expose
    private String foodId;
    @SerializedName("image")
    @Expose
    private String image;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
