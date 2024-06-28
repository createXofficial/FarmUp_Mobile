package com.createx.farmup.model.entity;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Farmer extends BaseObservable {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("bio")
    @Expose
    private String bio;

    @SerializedName("images")
    @Expose
    private int image;

    @SerializedName("images_url")
    @Expose
    private String imageUrl;

//    @BindingAdapter({"imageUrl"})
//    public static void loadImage(ImageView imageView, String url) {
//        String imagePath = "";
//        Glide.with(imageView.getContext())
//                .load(imagePath)
//                .into(imageView);
//    }


    public Farmer() {}

    public Farmer(String name, String bio, int image) {
        this.id = 0;
        this.name = name;
        this.bio = bio;
        this.image = image;
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
        return image;
    }

    public void setImage(int image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    @NonNull
    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", image=" + image +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Farmer)) return false;
        Farmer farmer = (Farmer) o;
        return id == farmer.id
                && image == farmer.image
                && Objects.equals(name, farmer.name)
                && Objects.equals(bio, farmer.bio)
                && Objects.equals(imageUrl, farmer.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bio, image, imageUrl);
    }
}
