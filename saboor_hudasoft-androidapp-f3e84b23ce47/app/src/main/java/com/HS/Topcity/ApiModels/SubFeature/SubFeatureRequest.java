package com.HS.Topcity.ApiModels.SubFeature;

import com.google.gson.annotations.SerializedName;

public class SubFeatureRequest {

    @SerializedName("FeatureId")
    public int featureId;

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }
}
