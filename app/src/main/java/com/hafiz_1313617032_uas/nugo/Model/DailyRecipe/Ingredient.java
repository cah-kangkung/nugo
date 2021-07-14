package com.hafiz_1313617032_uas.nugo.Model.DailyRecipe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeValue(this.weight);
        dest.writeString(this.foodCategory);
        dest.writeString(this.foodId);
        dest.writeString(this.image);
    }

    public void readFromParcel(Parcel source) {
        this.text = source.readString();
        this.weight = (Double) source.readValue(Double.class.getClassLoader());
        this.foodCategory = source.readString();
        this.foodId = source.readString();
        this.image = source.readString();
    }

    public Ingredient() {
    }

    protected Ingredient(Parcel in) {
        this.text = in.readString();
        this.weight = (Double) in.readValue(Double.class.getClassLoader());
        this.foodCategory = in.readString();
        this.foodId = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
