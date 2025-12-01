package com.HS.Topcity.ApiModels.SubFeature;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SubFeatureModel {

    @SerializedName("SubFeatureId")
    public int subFeatureId;
    @SerializedName("FeatureId")
    public int featureId;
    @SerializedName("Name")
    public String name;
    @SerializedName("Description")
    public String description;
    @SerializedName("Image")
    public ArrayList<String> image;

    public int getSubFeatureId() {
        return subFeatureId;
    }

    public void setSubFeatureId(int subFeatureId) {
        this.subFeatureId = subFeatureId;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
    }
}
