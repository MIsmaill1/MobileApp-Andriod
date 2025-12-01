package com.HS.Topcity.ApiModels.UpdatePass;

import com.google.gson.annotations.SerializedName;

public class UpdatePassResponse {
    @SerializedName("Status")
    public Boolean status;

    @SerializedName("Message")
    public String message;


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


}
