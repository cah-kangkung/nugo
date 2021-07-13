package com.hafiz_1313617032_uas.nugo.Model.DailyRecipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hit {
    @SerializedName("recipe")
    @Expose
    private Recipe recipe = null;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
