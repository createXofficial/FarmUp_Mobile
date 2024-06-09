package com.example.farm_up.model.entity;

import com.google.gson.annotations.SerializedName;

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


}