package com.hafiz_1313617032_uas.nugo.Model.FoodNutrition;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodNutrition implements Parcelable {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("parsed")
    @Expose
    private List<Parsed> parsed = null;
    @SerializedName("hints")
    @Expose
    private List<Hint> hints = null;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Parsed> getParsed() {
        return parsed;
    }

    public void setParsed(List<Parsed> parsed) {
        this.parsed = parsed;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeTypedList(this.parsed);
        dest.writeTypedList(this.hints);
    }

    public void readFromParcel(Parcel source) {
        this.text = source.readString();
        this.parsed = source.createTypedArrayList(Parsed.CREATOR);
        this.hints = source.createTypedArrayList(Hint.CREATOR);
    }

    public FoodNutrition() {
    }

    protected FoodNutrition(Parcel in) {
        this.text = in.readString();
        this.parsed = in.createTypedArrayList(Parsed.CREATOR);
        this.hints = in.createTypedArrayList(Hint.CREATOR);
    }

    public static final Creator<FoodNutrition> CREATOR = new Creator<FoodNutrition>() {
        @Override
        public FoodNutrition createFromParcel(Parcel source) {
            return new FoodNutrition(source);
        }

        @Override
        public FoodNutrition[] newArray(int size) {
            return new FoodNutrition[size];
        }
    };
}
