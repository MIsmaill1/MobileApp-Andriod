package com.HS.Topcity.ApiModels.SubFeature;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SubFeatureResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("SubFeatures")
    public ArrayList<SubFeatureModel> subFeatures;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<SubFeatureModel> getSubFeatures() {
        return subFeatures;
    }

    public void setSubFeatures(ArrayList<SubFeatureModel> subFeatures) {
        this.subFeatures = subFeatures;
    }
}
