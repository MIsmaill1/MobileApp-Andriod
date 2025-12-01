package com.HS.Topcity.ApiModels.UserSharedAccountDetail;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShareAccountUserInfoModel {
    @SerializedName("UserInfoId")
    public int userInfoId;
    @SerializedName("UserImage")
    public String userImage;
    @SerializedName("StateOfAccount")
    public String stateOfAccount;
    @SerializedName("Name")
    public String name;
    @SerializedName("CNIC")
    public String cNIC;
    @SerializedName("TypeOfAccountId")
    public int typeOfAccountId;
    @SerializedName("TypeOfAccount")
    public String typeOfAccount;
    @SerializedName("ContactNumber")
    public String contactNumber;
    @SerializedName("Email")
    public String email;
    @SerializedName("PropertyDetailsModel")
    public PropertyDetailsModel propertyDetailsModel;
    @SerializedName("ComplainsDetailsModels")
    public ArrayList<ComplainsDetailsModels> complainsDetailsModels;
    @SerializedName("CanRemove")
    public boolean canRemove;


    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getStateOfAccount() {
        return stateOfAccount;
    }

    public void setStateOfAccount(String stateOfAccount) {
        this.stateOfAccount = stateOfAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcNIC() {
        return cNIC;
    }

    public void setcNIC(String cNIC) {
        this.cNIC = cNIC;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PropertyDetailsModel getPropertyDetailsModel() {
        return propertyDetailsModel;
    }

    public void setPropertyDetailsModel(PropertyDetailsModel propertyDetailsModel) {
        this.propertyDetailsModel = propertyDetailsModel;
    }

    public ArrayList<ComplainsDetailsModels> getComplainsDetailsModels() {
        return complainsDetailsModels;
    }

    public void setComplainsDetailsModels(ArrayList<ComplainsDetailsModels> complainsDetailsModels) {
        this.complainsDetailsModels = complainsDetailsModels;
    }

    public boolean isCanRemove() {
        return canRemove;
    }

    public void setCanRemove(boolean canRemove) {
        this.canRemove = canRemove;
    }
}
