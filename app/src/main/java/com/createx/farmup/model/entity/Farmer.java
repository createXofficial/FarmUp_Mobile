package com.createx.farmup.model.entity;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Farmer extends BaseObservable {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;

    @SerializedName("bio")
    private String bio;

    @SerializedName("images")
    private int images;

    @SerializedName("images_url")
    private String imageUrl;

    public Farmer() {}

    public Farmer(String name, String bio, int images) {
        this.id = 0;
        this.name = name;
        this.bio = bio;
        this.images = images;
    }

    // getters and setters
    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
        notifyPropertyChanged(BR.bio);
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @Bindable
    public int getImage() {
        return images;
    }

    public void setImage(int images) {
        this.images = images;
        notifyPropertyChanged(BR.image);
    }

    @NonNull
    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
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
        return id == farmer.id
                && images == farmer.images
                && Objects.equals(name, farmer.name)
                && Objects.equals(bio, farmer.bio)
                && Objects.equals(imageUrl, farmer.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bio, images, imageUrl);
    }
}
