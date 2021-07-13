package com.hafiz_1313617032_uas.nugo.Model.DailyRecipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyRecipe {
    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }
}
