package com.HS.Topcity.ApiModels.UserSharedAccountRemove;

import com.google.gson.annotations.SerializedName;

public class UserSharedAccountRemoveRequest {

    @SerializedName("SharedAccountId")
    public int SharedAccountId;

    @SerializedName("TypeOfAccountId")
    public int TypeOfAccountId;

    @SerializedName("PropertyId")
    public int PropertyId;


    public int getTypeOfAccountId() {
        return TypeOfAccountId;
    }

    public void setTypeOfAccountId(int typeOfAccountId) {
        TypeOfAccountId = typeOfAccountId;
    }

    public int getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(int propertyId) {
        PropertyId = propertyId;
    }

    public int getSharedAccountId() {
        return SharedAccountId;
    }

    public void setSharedAccountId(int sharedAccountId) {
        SharedAccountId = sharedAccountId;
    }
}
