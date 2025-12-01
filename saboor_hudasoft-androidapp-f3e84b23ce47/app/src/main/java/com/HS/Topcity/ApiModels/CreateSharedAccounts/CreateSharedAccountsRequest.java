package com.HS.Topcity.ApiModels.CreateSharedAccounts;

import com.google.gson.annotations.SerializedName;

public class CreateSharedAccountsRequest {

    @SerializedName("PropertyId")
    public int propertyId;
    @SerializedName("Name")
    public String name;
    @SerializedName("ShareAccountType")
    public int shareAccountType;
    @SerializedName("CNICNumber")
    public String cNICNumber;
    @SerializedName("Email")
    public String email;
    @SerializedName("PhoneNumber")
    public String phoneNumber;

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShareAccountType() {
        return shareAccountType;
    }

    public void setShareAccountType(int shareAccountType) {
        this.shareAccountType = shareAccountType;
    }

    public String getcNICNumber() {
        return cNICNumber;
    }

    public void setcNICNumber(String cNICNumber) {
        this.cNICNumber = cNICNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
