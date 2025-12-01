package com.HS.Topcity.ApiModels.PropertySharedAccountDetail;

import com.google.gson.annotations.SerializedName;

public class PropertySharedAccountDetailsRequest {

    @SerializedName("PropertyId")
    public int PropertyId;


    public int getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(int propertyId) {
        PropertyId = propertyId;
    }
}
