package com.hafiz_1313617032_uas.nugo.Model.DailyRecipe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("shareAs")
    @Expose
    private String shareAs;
    @SerializedName("yield")
    @Expose
    private Integer yield;
    @SerializedName("dietLabels")
    @Expose
    private List<String> dietLabels = null;
    @SerializedName("healthLabels")
    @Expose
    private List<String> healthLabels = null;
    @SerializedName("cautions")
    @Expose
    private List<String> cautions = null;
    @SerializedName("ingredientLines")
    @Expose
    private List<String> ingredientLines = null;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;
    @SerializedName("calories")
    @Expose
    private Double calories;
    @SerializedName("totalWeight")
    @Expose
    private Double totalWeight;
    @SerializedName("totalTime")
    @Expose
    private Integer totalTime;
    @SerializedName("cuisineType")
    @Expose
    private List<String> cuisineType = null;
    @SerializedName("mealType")
    @Expose
    private List<String> mealType = null;
    @SerializedName("dishType")
    @Expose
    private List<String> dishType = null;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShareAs() {
        return shareAs;
    }

    public void setShareAs(String shareAs) {
        this.shareAs = shareAs;
    }

    public Integer getYield() {
        return yield;
    }

    public void setYield(Integer yield) {
        this.yield = yield;
    }

    public List<String> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(List<String> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(List<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public List<String> getCautions() {
        return cautions;
    }

    public void setCautions(List<String> cautions) {
        this.cautions = cautions;
    }

    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public List<String> getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(List<String> cuisineType) {
        this.cuisineType = cuisineType;
    }

    public List<String> getMealType() {
        return mealType;
    }

    public void setMealType(List<String> mealType) {
        this.mealType = mealType;
    }

    public List<String> getDishType() {
        return dishType;
    }

    public void setDishType(List<String> dishType) {
        this.dishType = dishType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uri);
        dest.writeString(this.label);
        dest.writeString(this.image);
        dest.writeString(this.source);
        dest.writeString(this.url);
        dest.writeString(this.shareAs);
        dest.writeValue(this.yield);
        dest.writeStringList(this.dietLabels);
        dest.writeStringList(this.healthLabels);
        dest.writeStringList(this.cautions);
        dest.writeStringList(this.ingredientLines);
        dest.writeList(this.ingredients);
        dest.writeValue(this.calories);
        dest.writeValue(this.totalWeight);
        dest.writeValue(this.totalTime);
        dest.writeStringList(this.cuisineType);
        dest.writeStringList(this.mealType);
        dest.writeStringList(this.dishType);
    }

    public void readFromParcel(Parcel source) {
        this.uri = source.readString();
        this.label = source.readString();
        this.image = source.readString();
        this.source = source.readString();
        this.url = source.readString();
        this.shareAs = source.readString();
        this.yield = (Integer) source.readValue(Integer.class.getClassLoader());
        this.dietLabels = source.createStringArrayList();
        this.healthLabels = source.createStringArrayList();
        this.cautions = source.createStringArrayList();
        this.ingredientLines = source.createStringArrayList();
        this.ingredients = new ArrayList<Ingredient>();
        source.readList(this.ingredients, Ingredient.class.getClassLoader());
        this.calories = (Double) source.readValue(Double.class.getClassLoader());
        this.totalWeight = (Double) source.readValue(Double.class.getClassLoader());
        this.totalTime = (Integer) source.readValue(Integer.class.getClassLoader());
        this.cuisineType = source.createStringArrayList();
        this.mealType = source.createStringArrayList();
        this.dishType = source.createStringArrayList();
    }

    public Recipe() {
    }

    protected Recipe(Parcel in) {
        this.uri = in.readString();
        this.label = in.readString();
        this.image = in.readString();
        this.source = in.readString();
        this.url = in.readString();
        this.shareAs = in.readString();
        this.yield = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dietLabels = in.createStringArrayList();
        this.healthLabels = in.createStringArrayList();
        this.cautions = in.createStringArrayList();
        this.ingredientLines = in.createStringArrayList();
        this.ingredients = new ArrayList<Ingredient>();
        in.readList(this.ingredients, Ingredient.class.getClassLoader());
        this.calories = (Double) in.readValue(Double.class.getClassLoader());
        this.totalWeight = (Double) in.readValue(Double.class.getClassLoader());
        this.totalTime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cuisineType = in.createStringArrayList();
        this.mealType = in.createStringArrayList();
        this.dishType = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
