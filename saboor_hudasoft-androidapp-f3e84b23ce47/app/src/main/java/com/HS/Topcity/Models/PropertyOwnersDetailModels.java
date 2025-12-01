package com.HS.Topcity.Models;

public class PropertyOwnersDetailModels {


    public int propertyOwnerId;

    public String propertyOwnerName;

    public String propertyOwnerContactNumber;

    public String propertyOwnerImage;

    public String propertyOwnerCurrentAddress;


    public PropertyOwnersDetailModels() {
    }

    public PropertyOwnersDetailModels(int propertyOwnerId, String propertyOwnerName, String propertyOwnerContactNumber, String propertyOwnerImage, String propertyOwnerCurrentAddress) {
        this.propertyOwnerId = propertyOwnerId;
        this.propertyOwnerName = propertyOwnerName;
        this.propertyOwnerContactNumber = propertyOwnerContactNumber;
        this.propertyOwnerImage = propertyOwnerImage;
        this.propertyOwnerCurrentAddress = propertyOwnerCurrentAddress;
    }

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
