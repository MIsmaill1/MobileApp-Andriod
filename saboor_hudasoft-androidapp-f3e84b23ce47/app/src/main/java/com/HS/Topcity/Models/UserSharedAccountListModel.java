package com.HS.Topcity.Models;

public class UserSharedAccountListModel {
    int id;
    String fullName;
    int typeAccountId;
    String typeOfAccount;
    String stateOfAccount;
    int propertyId;
    String propertyName;
    String image;


    public UserSharedAccountListModel(int id, String fullName, int typeAccountId, String typeOfAccount, String stateOfAccount, int propertyId, String propertyName, String image) {
        this.id = id;
        this.fullName = fullName;
        this.typeAccountId = typeAccountId;
        this.typeOfAccount = typeOfAccount;
        this.stateOfAccount = stateOfAccount;
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.image = image;
    }

    public UserSharedAccountListModel() {
    }

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

    public int getTypeAccountId() {
        return typeAccountId;
    }

    public void setTypeAccountId(int typeAccountId) {
        this.typeAccountId = typeAccountId;
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
