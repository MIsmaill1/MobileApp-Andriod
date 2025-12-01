package com.HS.Topcity.ApiModels.ProfileSetting;

import com.google.gson.annotations.SerializedName;

public class UserInfoModels {
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

    @SerializedName("FamilyMember")
    public boolean FamilyMember;
    @SerializedName("Owner")
    public boolean Owner;
    @SerializedName("Tenant")
    public boolean Tenant;
    @SerializedName("OTPVerifyed")
    public boolean OTPVerifyed;

    @SerializedName("Image")
    public String Image;

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

    public boolean isFamilyMember() {
        return FamilyMember;
    }

    public void setFamilyMember(boolean familyMember) {
        FamilyMember = familyMember;
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

    public boolean isOwner() {
        return Owner;
    }

    public void setOwner(boolean owner) {
        Owner = owner;
    }

    public boolean isTenant() {
        return Tenant;
    }

    public void setTenant(boolean tenant) {
        Tenant = tenant;
    }
}
