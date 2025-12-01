package com.HS.Topcity.ApiModels.FingerprintLogin;

import com.google.gson.annotations.SerializedName;

public class FingerprintLoginResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("UserInfo")
    public UserInfo userInfo;
    @SerializedName("Token")
    public String token;
    @SerializedName("TokenExpirationTimeInSeconds")
    public int tokenExpirationTimeInSeconds;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTokenExpirationTimeInSeconds() {
        return tokenExpirationTimeInSeconds;
    }

    public void setTokenExpirationTimeInSeconds(int tokenExpirationTimeInSeconds) {
        this.tokenExpirationTimeInSeconds = tokenExpirationTimeInSeconds;
    }
}
