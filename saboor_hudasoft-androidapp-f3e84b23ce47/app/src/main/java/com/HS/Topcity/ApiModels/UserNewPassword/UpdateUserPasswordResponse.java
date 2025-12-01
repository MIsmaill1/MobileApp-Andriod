package com.HS.Topcity.ApiModels.UserNewPassword;

import com.google.gson.annotations.SerializedName;

public class UpdateUserPasswordResponse {

    @SerializedName("Status")
    public Boolean status;

    @SerializedName("Message")
    public String message;

    @SerializedName("Token")
    public String Token;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
