package com.HS.Topcity.ApiModels.ForgetPassword;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordRequest {

    @SerializedName("MobileNumber")
    public String MobileNumber;

    @SerializedName("Email")
    public String Email;

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
