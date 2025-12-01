package com.HS.Topcity.ApiModels.PropertyDetail;

import com.google.gson.annotations.SerializedName;

public class PropertyDetailsResponse {

    @SerializedName("Status")
    public Boolean status;

    @SerializedName("PropertyDetails")
    public PropertyDetailsmodel propertyDetailsmodels;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public PropertyDetailsmodel getPropertyDetailsmodels() {
        return propertyDetailsmodels;
    }

    public void setPropertyDetailsmodels(PropertyDetailsmodel propertyDetailsmodels) {
        this.propertyDetailsmodels = propertyDetailsmodels;
    }
}
