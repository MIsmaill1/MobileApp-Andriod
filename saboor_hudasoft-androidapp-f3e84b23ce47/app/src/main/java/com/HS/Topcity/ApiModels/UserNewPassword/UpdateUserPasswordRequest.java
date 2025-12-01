package com.HS.Topcity.ApiModels.UserNewPassword;

import com.google.gson.annotations.SerializedName;

public class UpdateUserPasswordRequest {


    @SerializedName("MobileNumber")
    public String MobileNumber;

    @SerializedName("Email")
    public String Email;


    @SerializedName("Password")
    public String Password;


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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
