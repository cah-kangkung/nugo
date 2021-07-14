package com.hafiz_1313617032_uas.nugo.Model.DailyRecipe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Next implements Parcelable {
    @SerializedName("href")
    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.href);
    }

    public void readFromParcel(Parcel source) {
        this.href = source.readString();
    }

    public Next() {
    }

    protected Next(Parcel in) {
        this.href = in.readString();
    }

    public static final Parcelable.Creator<Next> CREATOR = new Parcelable.Creator<Next>() {
        @Override
        public Next createFromParcel(Parcel source) {
            return new Next(source);
        }

        @Override
        public Next[] newArray(int size) {
            return new Next[size];
        }
    };
}
