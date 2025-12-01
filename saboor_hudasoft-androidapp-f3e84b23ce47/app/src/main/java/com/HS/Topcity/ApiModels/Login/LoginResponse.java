package com.HS.Topcity.ApiModels.Login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("UserInfo")
    public UserInfoModel userInfoModel;

    @SerializedName("Status")
    public boolean status;
    @SerializedName("Message")
    public String Message;

    @SerializedName("ModelState")
    public ModelState modelState;

    @SerializedName("Token")
    public String token;

    @SerializedName("TokenExpirationTimeInSeconds")
    public int TokenExpirationTimeInSeconds;

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ModelState getModelState() {
        return modelState;
    }

    public void setModelState(ModelState modelState) {
        this.modelState = modelState;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTokenExpirationTimeInSeconds() {
        return TokenExpirationTimeInSeconds;
    }

    public void setTokenExpirationTimeInSeconds(int tokenExpirationTimeInSeconds) {
        TokenExpirationTimeInSeconds = tokenExpirationTimeInSeconds;
    }
}
