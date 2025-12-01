package com.HS.Topcity.ApiModels.Features;

import com.HS.Topcity.ApiModels.Complains.ComplainListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeaturesResponse {

    @SerializedName("Feature")
    public ArrayList<FeatureModel> featureModels;

    @SerializedName("Status")
    public String status;

    @SerializedName("Message")
    public String message;
}
