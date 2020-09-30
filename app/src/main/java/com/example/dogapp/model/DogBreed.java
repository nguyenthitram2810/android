package com.example.dogapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DogBreed implements Parcelable {
    @SerializedName("id")
    public final int id;
    @SerializedName("name")
    public final String name;
    @SerializedName("origin")
    public final String origin;
    @SerializedName("life_span")
    public final String lifeSpan;
    @SerializedName("url")
    public final String url;
    @SerializedName("bred_for")
    public final String bredFor;
    @SerializedName("temperament")
    public final String temperament;

    public DogBreed(int id, String name, String origin, String lifeSpan, String url, String bredFor, String temperament, String breedGroup) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.lifeSpan = lifeSpan;
        this.url = url;
        this.bredFor = bredFor;
        this.temperament = temperament;
        this.breedGroup = breedGroup;
    }

    @SerializedName("breed_group")
    public final String breedGroup;

    protected DogBreed(Parcel in) {
        id = in.readInt();
        name = in.readString();
        origin = in.readString();
        lifeSpan = in.readString();
        url = in.readString();
        bredFor = in.readString();
        temperament = in.readString();
        breedGroup = in.readString();
    }

    public static final Creator<DogBreed> CREATOR = new Creator<DogBreed>() {
        @Override
        public DogBreed createFromParcel(Parcel in) {
            return new DogBreed(in);
        }

        @Override
        public DogBreed[] newArray(int size) {
            return new DogBreed[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(origin);
        dest.writeString(lifeSpan);
        dest.writeString(url);
        dest.writeString(bredFor);
        dest.writeString(temperament);
        dest.writeString(breedGroup);
    }
}
