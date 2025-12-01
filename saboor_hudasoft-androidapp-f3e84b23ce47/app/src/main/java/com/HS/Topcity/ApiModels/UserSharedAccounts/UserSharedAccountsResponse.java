package com.HS.Topcity.ApiModels.UserSharedAccounts;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserSharedAccountsResponse {

    @SerializedName("Status")
    public Boolean status;

    @SerializedName("UserInfos")
    public ArrayList<UserInfosModel> userInfosModels;

    @SerializedName("Message")
    public String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<UserInfosModel> getUserInfosModels() {
        return userInfosModels;
    }

    public void setUserInfosModels(ArrayList<UserInfosModel> userInfosModels) {
        this.userInfosModels = userInfosModels;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
