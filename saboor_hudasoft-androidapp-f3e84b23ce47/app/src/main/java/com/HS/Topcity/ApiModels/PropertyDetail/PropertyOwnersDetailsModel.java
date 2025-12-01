package com.HS.Topcity.ApiModels.PropertyDetail;

import com.google.gson.annotations.SerializedName;

public class PropertyOwnersDetailsModel {

    @SerializedName("PropertyOwnerId")
    public int propertyOwnerId;
    @SerializedName("PropertyOwnerName")
    public String propertyOwnerName;
    @SerializedName("PropertyOwnerContactNumber")
    public String propertyOwnerContactNumber;
    @SerializedName("PropertyOwnerImage")
    public String propertyOwnerImage;
    @SerializedName("PropertyOwnerCurrentAddress")
    public String propertyOwnerCurrentAddress;


    public int getPropertyOwnerId() {
        return propertyOwnerId;
    }

    public void setPropertyOwnerId(int propertyOwnerId) {
        this.propertyOwnerId = propertyOwnerId;
    }

    public String getPropertyOwnerName() {
        return propertyOwnerName;
    }

    public void setPropertyOwnerName(String propertyOwnerName) {
        this.propertyOwnerName = propertyOwnerName;
    }

    public String getPropertyOwnerContactNumber() {
        return propertyOwnerContactNumber;
    }

    public void setPropertyOwnerContactNumber(String propertyOwnerContactNumber) {
        this.propertyOwnerContactNumber = propertyOwnerContactNumber;
    }

    public String getPropertyOwnerImage() {
        return propertyOwnerImage;
    }

    public void setPropertyOwnerImage(String propertyOwnerImage) {
        this.propertyOwnerImage = propertyOwnerImage;
    }

    public String getPropertyOwnerCurrentAddress() {
        return propertyOwnerCurrentAddress;
    }

    public void setPropertyOwnerCurrentAddress(String propertyOwnerCurrentAddress) {
        this.propertyOwnerCurrentAddress = propertyOwnerCurrentAddress;
    }
}
