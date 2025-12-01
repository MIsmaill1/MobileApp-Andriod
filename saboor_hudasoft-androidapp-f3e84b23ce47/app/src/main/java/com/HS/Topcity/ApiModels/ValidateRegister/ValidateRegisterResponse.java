package com.HS.Topcity.ApiModels.ValidateRegister;

import com.google.gson.annotations.SerializedName;

public class ValidateRegisterResponse {

    @SerializedName("Status")
    public boolean status;
    @SerializedName("MobileRegistrationStatusDetail")
    public String mobileRegistrationStatusDetail;
    @SerializedName("OTPSentStatus")
    public boolean oTPSentStatus;
    @SerializedName("Message")
    public String message;
    @SerializedName("Name")
    public String name;
    @SerializedName("CNIC")
    public String cNIC;
    @SerializedName("PhoneNumber")
    public String phoneNumber;
    @SerializedName("Email")
    public String  email;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMobileRegistrationStatusDetail() {
        return mobileRegistrationStatusDetail;
    }

    public void setMobileRegistrationStatusDetail(String mobileRegistrationStatusDetail) {
        this.mobileRegistrationStatusDetail = mobileRegistrationStatusDetail;
    }

    public boolean isoTPSentStatus() {
        return oTPSentStatus;
    }

    public void setoTPSentStatus(boolean oTPSentStatus) {
        this.oTPSentStatus = oTPSentStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcNIC() {
        return cNIC;
    }

    public void setcNIC(String cNIC) {
        this.cNIC = cNIC;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
