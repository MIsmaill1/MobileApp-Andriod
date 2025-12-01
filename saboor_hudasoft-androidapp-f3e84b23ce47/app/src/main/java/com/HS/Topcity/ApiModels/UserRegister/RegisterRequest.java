package com.HS.Topcity.ApiModels.UserRegister;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("FullName")
    public String FullName;

    @SerializedName("CNIC")
    public String CNIC;

    @SerializedName("Email")
    public String Email;

    @SerializedName("MobileNumber")
    public String MobileNumber;

    @SerializedName("Password")
    public String Password;

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
