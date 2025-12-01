package com.HS.Topcity.Models;

import java.util.ArrayList;

public class SubFeaturesModels {


    public int subFeatureId;

    public int featureId;

    public String name;

    public String description;

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
