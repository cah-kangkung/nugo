package com.hafiz_1313617032_uas.nugo.Model.FoodNutrition;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hint implements Parcelable {
    @SerializedName("food")
    @Expose
    private Food food;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.food, flags);
    }

    public void readFromParcel(Parcel source) {
        this.food = source.readParcelable(Food.class.getClassLoader());
    }

    public Hint() {
    }

    protected Hint(Parcel in) {
        this.food = in.readParcelable(Food.class.getClassLoader());
    }

    public static final Parcelable.Creator<Hint> CREATOR = new Parcelable.Creator<Hint>() {
        @Override
        public Hint createFromParcel(Parcel source) {
            return new Hint(source);
        }

        @Override
        public Hint[] newArray(int size) {
            return new Hint[size];
        }
    };
}
