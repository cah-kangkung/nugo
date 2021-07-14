package com.hafiz_1313617032_uas.nugo.Model.DailyRecipe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DailyRecipe implements Parcelable {
    @SerializedName("from")
    @Expose
    private String from;

    @SerializedName("to")
    @Expose
    private String to;

    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;

    @SerializedName("_links")
    @Expose
    private Link link;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.from);
        dest.writeString(this.to);
        dest.writeList(this.hits);
        dest.writeParcelable(this.link, flags);
    }

    public void readFromParcel(Parcel source) {
        this.from = source.readString();
        this.to = source.readString();
        this.hits = new ArrayList<Hit>();
        source.readList(this.hits, Hit.class.getClassLoader());
        this.link = source.readParcelable(Link.class.getClassLoader());
    }

    public DailyRecipe() {
    }

    protected DailyRecipe(Parcel in) {
        this.from = in.readString();
        this.to = in.readString();
        this.hits = new ArrayList<Hit>();
        in.readList(this.hits, Hit.class.getClassLoader());
        this.link = in.readParcelable(Link.class.getClassLoader());
    }

    public static final Creator<DailyRecipe> CREATOR = new Creator<DailyRecipe>() {
        @Override
        public DailyRecipe createFromParcel(Parcel source) {
            return new DailyRecipe(source);
        }

        @Override
        public DailyRecipe[] newArray(int size) {
            return new DailyRecipe[size];
        }
    };
}
