package com.HS.Topcity.ApiModels.VerifyOTP;

import com.google.gson.annotations.SerializedName;

public class VerifyOtpRequest {

    @SerializedName("Email")
    public String Email;

    @SerializedName("MobileNumber")
    public String MobileNumber;

    @SerializedName("OTP")
    public String OTP;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}
