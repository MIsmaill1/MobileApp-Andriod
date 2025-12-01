package com.HS.Topcity.ApiModels.UserRegister;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("Status")
    public boolean Status;

    @SerializedName("StatusDetail")
    public String StatusDetail;

    @SerializedName("Token")
    public String Token;

    @SerializedName("Message")
    public String Message;

    @SerializedName("ID")
    public String ID;

    @SerializedName("ContactNumber")
    public String ContactNumber;

    @SerializedName("CNIC")
    public String CNIC;

    @SerializedName("Email")
    public String Email;

    @SerializedName("FullName")
    public String FullName;

    @SerializedName("FamilyMember")
    public boolean FamilyMember;

    @SerializedName("OTPVerify")
    public boolean OTPVerify;

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getStatusDetail() {
        return StatusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        StatusDetail = statusDetail;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
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

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public boolean isFamilyMember() {
        return FamilyMember;
    }

    public void setFamilyMember(boolean familyMember) {
        FamilyMember = familyMember;
    }

    public boolean isOTPVerify() {
        return OTPVerify;
    }

    public void setOTPVerify(boolean OTPVerify) {
        this.OTPVerify = OTPVerify;
    }
}
