package com.HS.Topcity.ApiModels.ForgetPassword;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordResponse {

    @SerializedName("Status")
    public boolean  status;

    @SerializedName("Message")
    public String message;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
