package com.HS.Topcity.ApiModels.Development;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DevelopmentModel {
    @SerializedName("Images")
    public ArrayList<String> images;
    @SerializedName("Videos")
    public ArrayList<String> videos;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<String> videos) {
        this.videos = videos;
    }
}
