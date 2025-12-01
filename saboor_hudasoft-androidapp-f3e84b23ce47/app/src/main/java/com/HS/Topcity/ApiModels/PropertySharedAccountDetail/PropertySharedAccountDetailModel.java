package com.HS.Topcity.ApiModels.PropertySharedAccountDetail;

import com.google.gson.annotations.SerializedName;

public class PropertySharedAccountDetailModel {

    @SerializedName("Id")
    public int id;
    @SerializedName("FullName")
    public String fullName;
    @SerializedName("TypeOfAccountId")
    public int typeOfAccountId;
    @SerializedName("TypeOfAccount")
    public String typeOfAccount;
    @SerializedName("StateOfAccount")
    public String stateOfAccount;
    @SerializedName("PropertyId")
    public int propertyId;
    @SerializedName("PropertyName")
    public String propertyName;
    @SerializedName("Image")
    public String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getTypeOfAccountId() {
        return typeOfAccountId;
    }

    public void setTypeOfAccountId(int typeOfAccountId) {
        this.typeOfAccountId = typeOfAccountId;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getStateOfAccount() {
        return stateOfAccount;
    }

    public void setStateOfAccount(String stateOfAccount) {
        this.stateOfAccount = stateOfAccount;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
