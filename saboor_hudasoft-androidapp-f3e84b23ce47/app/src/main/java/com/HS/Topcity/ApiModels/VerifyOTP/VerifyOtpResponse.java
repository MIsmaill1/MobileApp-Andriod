package com.HS.Topcity.ApiModels.VerifyOTP;

import com.google.gson.annotations.SerializedName;

public class VerifyOtpResponse {

    @SerializedName("Status")
    public boolean Status;

    @SerializedName("Message")
    public String Message;

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
