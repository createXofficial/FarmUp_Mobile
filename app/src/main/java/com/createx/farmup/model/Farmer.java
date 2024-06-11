package com.createx.farmup.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Farmer {
    @SerializedName("name")
    private String name;

    @SerializedName("bio")
    private String bio;

    @SerializedName("images")
    private int images;

    @SerializedName("images_url")
    private String imageUrl;

    public Farmer(String name, String bio, int images) {
        this.name = name;
        this.bio = bio;
        this.images = images;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImage() {
        return images;
    }

    public void setImage(int images) {
        this.images = images;
    }

    @NonNull
    @Override
    public String toString() {
        return "Farmer{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", images=" + images +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Farmer)) return false;
        Farmer farmer = (Farmer) o;
        return images == farmer.images && Objects.equals(name, farmer.name) && Objects.equals(bio, farmer.bio) && Objects.equals(imageUrl, farmer.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bio, images, imageUrl);
    }
}
