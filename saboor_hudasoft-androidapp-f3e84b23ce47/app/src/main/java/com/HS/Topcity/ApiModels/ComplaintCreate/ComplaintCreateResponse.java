package com.HS.Topcity.ApiModels.ComplaintCreate;

import com.google.gson.annotations.SerializedName;

public class ComplaintCreateResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("Message")
    public String message;

    @SerializedName("ComplainNumber")
    public String ComplainNumber;

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

    public String getComplainNumber() {
        return ComplainNumber;
    }

    public void setComplainNumber(String complainNumber) {
        ComplainNumber = complainNumber;
    }
}
