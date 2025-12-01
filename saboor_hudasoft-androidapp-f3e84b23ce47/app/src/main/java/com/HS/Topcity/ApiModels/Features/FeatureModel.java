package com.HS.Topcity.ApiModels.Features;

import com.google.gson.annotations.SerializedName;

public class FeatureModel {

    @SerializedName("FeatureId")
    public int FeatureId;
    @SerializedName("FeatureName")
    public String FeatureName;
    @SerializedName("FeatureImage")
    public String FeatureImage;

    public int getFeatureId() {
        return FeatureId;
    }

    public void setFeatureId(int featureId) {
        FeatureId = featureId;
    }

    public String getFeatureName() {
        return FeatureName;
    }

    public void setFeatureName(String featureName) {
        FeatureName = featureName;
    }

    public String getFeatureImage() {
        return FeatureImage;
    }

    public void setFeatureImage(String featureImage) {
        FeatureImage = featureImage;
    }
}
