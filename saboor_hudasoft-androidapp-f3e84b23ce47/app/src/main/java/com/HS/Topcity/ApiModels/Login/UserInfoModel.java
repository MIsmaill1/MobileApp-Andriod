package com.HS.Topcity.ApiModels.Login;

import com.google.gson.annotations.SerializedName;

public class UserInfoModel {

    @SerializedName("ID")
    public int ID;

    @SerializedName("ContactNumber")
    public String ContactNumber;

    @SerializedName("CNIC")
    public String CNIC;

    @SerializedName("Email")
    public String Email;

    @SerializedName("FullName")
    public String FullName;

    @SerializedName("Owner")
    public boolean Owner;

    @SerializedName("FamilyMember")
    public boolean FamilyMember;


    @SerializedName("Tenant")
    public boolean Tenant;


    @SerializedName("IsManagement")
    public boolean IsManagement;

    @SerializedName("OTPVerifyed")
    public boolean OTPVerifyed;

    @SerializedName("Image")
    public String Image;

    @SerializedName("Token")
    public String Token;

    @SerializedName("TokenExpirationTimeInSeconds")
    public int TokenExpirationTimeInSeconds;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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

    public boolean isOwner() {
        return Owner;
    }

    public void setOwner(boolean owner) {
        Owner = owner;
    }

    public boolean isFamilyMember() {
        return FamilyMember;
    }

    public void setFamilyMember(boolean familyMember) {
        FamilyMember = familyMember;
    }

    public boolean isTenant() {
        return Tenant;
    }

    public void setTenant(boolean tenant) {
        Tenant = tenant;
    }

    public boolean isManagement() {
        return IsManagement;
    }

    public void setManagement(boolean management) {
        IsManagement = management;
    }

    public boolean isOTPVerifyed() {
        return OTPVerifyed;
    }

    public void setOTPVerifyed(boolean OTPVerifyed) {
        this.OTPVerifyed = OTPVerifyed;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getTokenExpirationTimeInSeconds() {
        return TokenExpirationTimeInSeconds;
    }

    public void setTokenExpirationTimeInSeconds(int tokenExpirationTimeInSeconds) {
        TokenExpirationTimeInSeconds = tokenExpirationTimeInSeconds;
    }
}
