package com.HS.Topcity.ApiModels.PropertySharedAccountDetail;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PropertySharedAccountDetailsResponse {

    @SerializedName("Status")
    public boolean status;
    @SerializedName("PropertySharedAccountDetails")
    public ArrayList<PropertySharedAccountDetailModel> propertySharedAccountDetails;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<PropertySharedAccountDetailModel> getPropertySharedAccountDetails() {
        return propertySharedAccountDetails;
    }

    public void setPropertySharedAccountDetails(ArrayList<PropertySharedAccountDetailModel> propertySharedAccountDetails) {
        this.propertySharedAccountDetails = propertySharedAccountDetails;
    }
}
