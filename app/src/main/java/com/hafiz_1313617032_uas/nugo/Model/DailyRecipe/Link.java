package com.hafiz_1313617032_uas.nugo.Model.DailyRecipe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link implements Parcelable {
    @SerializedName("next")
    @Expose
    private Next next;

    public Next getNext() {
        return next;
    }

    public void setNext(Next next) {
        this.next = next;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.next, flags);
    }

    public void readFromParcel(Parcel source) {
        this.next = source.readParcelable(Next.class.getClassLoader());
    }

    public Link() {
    }

    protected Link(Parcel in) {
        this.next = in.readParcelable(Next.class.getClassLoader());
    }

    public static final Parcelable.Creator<Link> CREATOR = new Parcelable.Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel source) {
            return new Link(source);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };
}
