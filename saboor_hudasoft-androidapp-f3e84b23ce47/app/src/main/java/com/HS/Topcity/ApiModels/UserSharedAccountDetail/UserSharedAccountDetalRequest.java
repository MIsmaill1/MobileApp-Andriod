package com.HS.Topcity.ApiModels.UserSharedAccountDetail;

import com.google.gson.annotations.SerializedName;

public class UserSharedAccountDetalRequest {

    @SerializedName("SharedAccountId")
    public int sharedAccountId;
    @SerializedName("TypeOfAccountId")
    public int typeOfAccountId;
    @SerializedName("TypeOfAccount")
    public String typeOfAccount;
    @SerializedName("PropertyId")
    public int propertyId;


    public int getSharedAccountId() {
        return sharedAccountId;
    }

    public void setSharedAccountId(int sharedAccountId) {
        this.sharedAccountId = sharedAccountId;
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

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
}
