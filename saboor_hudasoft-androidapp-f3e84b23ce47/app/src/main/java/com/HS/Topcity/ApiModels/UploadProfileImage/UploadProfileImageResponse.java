package com.HS.Topcity.ApiModels.UploadProfileImage;

import com.google.gson.annotations.SerializedName;

public class UploadProfileImageResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("Message")
    public String message;

    public boolean isStatus() {
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
