package com.HS.Topcity.ApiModels.PropertyDetail;

import com.google.gson.annotations.SerializedName;

public class PropertyDetailsRequest {

    @SerializedName("PropertyId")
    public int PropertyId;

    public int getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(int propertyId) {
        PropertyId = propertyId;
    }
}
