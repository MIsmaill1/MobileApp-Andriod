package com.HS.Topcity.ApiModels.UserSharedAccounts;

import com.google.gson.annotations.SerializedName;

public class UserInfosModel {


    @SerializedName("Id")
    public int Id;


    @SerializedName("FullName")
    public String FullName;


    @SerializedName("TypeOfAccountId")
    public int TypeOfAccountId;

    @SerializedName("TypeOfAccount")
    public String TypeOfAccount;


    @SerializedName("StateOfAccount")
    public String StateOfAccount;


    @SerializedName("PropertyId")
    public int PropertyId;


    @SerializedName("PropertyName")
    public String PropertyName;

    @SerializedName("Image")
    public String Image;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getTypeOfAccountId() {
        return TypeOfAccountId;
    }

    public void setTypeOfAccountId(int typeOfAccountId) {
        TypeOfAccountId = typeOfAccountId;
    }

    public String getTypeOfAccount() {
        return TypeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        TypeOfAccount = typeOfAccount;
    }

    public String getStateOfAccount() {
        return StateOfAccount;
    }

    public void setStateOfAccount(String stateOfAccount) {
        StateOfAccount = stateOfAccount;
    }

    public int getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(int propertyId) {
        PropertyId = propertyId;
    }

    public String getPropertyName() {
        return PropertyName;
    }

    public void setPropertyName(String propertyName) {
        PropertyName = propertyName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
