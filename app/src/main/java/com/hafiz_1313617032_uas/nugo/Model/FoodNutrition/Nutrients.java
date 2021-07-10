package com.hafiz_1313617032_uas.nugo.Model.FoodNutrition;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nutrients implements Parcelable {
    @SerializedName("ENERC_KCAL")
    @Expose
    private Double enercKcal;
    @SerializedName("PROCNT")
    @Expose
    private Double procnt;
    @SerializedName("FAT")
    @Expose
    private Double fat;
    @SerializedName("CHOCDF")
    @Expose
    private Double chocdf;
    @SerializedName("FIBTG")
    @Expose
    private Double fibtg;

    public Double getEnercKcal() {
        return enercKcal;
    }

    public void setEnercKcal(Double enercKcal) {
        this.enercKcal = enercKcal;
    }

    public Double getProcnt() {
        return procnt;
    }

    public void setProcnt(Double procnt) {
        this.procnt = procnt;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getChocdf() {
        return chocdf;
    }

    public void setChocdf(Double chocdf) {
        this.chocdf = chocdf;
    }

    public Double getFibtg() {
        return fibtg;
    }

    public void setFibtg(Double fibtg) {
        this.fibtg = fibtg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.enercKcal);
        dest.writeValue(this.procnt);
        dest.writeValue(this.fat);
        dest.writeValue(this.chocdf);
        dest.writeValue(this.fibtg);
    }

    public void readFromParcel(Parcel source) {
        this.enercKcal = (Double) source.readValue(Double.class.getClassLoader());
        this.procnt = (Double) source.readValue(Double.class.getClassLoader());
        this.fat = (Double) source.readValue(Double.class.getClassLoader());
        this.chocdf = (Double) source.readValue(Double.class.getClassLoader());
        this.fibtg = (Double) source.readValue(Double.class.getClassLoader());
    }

    public Nutrients() {
    }

    protected Nutrients(Parcel in) {
        this.enercKcal = (Double) in.readValue(Double.class.getClassLoader());
        this.procnt = (Double) in.readValue(Double.class.getClassLoader());
        this.fat = (Double) in.readValue(Double.class.getClassLoader());
        this.chocdf = (Double) in.readValue(Double.class.getClassLoader());
        this.fibtg = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Nutrients> CREATOR = new Parcelable.Creator<Nutrients>() {
        @Override
        public Nutrients createFromParcel(Parcel source) {
            return new Nutrients(source);
        }

        @Override
        public Nutrients[] newArray(int size) {
            return new Nutrients[size];
        }
    };
}
