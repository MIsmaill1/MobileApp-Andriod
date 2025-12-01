package com.HS.Topcity.ApiModels.Development;

import com.google.gson.annotations.SerializedName;

public class DevelopmentResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("Development")
    public DevelopmentModel developmentModel;
}
