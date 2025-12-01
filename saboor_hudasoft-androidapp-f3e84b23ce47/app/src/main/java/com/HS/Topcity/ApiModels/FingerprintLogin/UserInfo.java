package com.HS.Topcity.ApiModels.FingerprintLogin;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("ID")
    public int iD;
    @SerializedName("ContactNumber")
    public String contactNumber;
    @SerializedName("CNIC")
    public String cNIC;
    @SerializedName("Email")
    public String email;
    @SerializedName("FullName")
    public String fullName;
    @SerializedName("OTPVerifyed")
    public boolean oTPVerifyed;
    @SerializedName("Image")
    public String image;
    @SerializedName("Owner")
    public boolean owner;
    @SerializedName("FamilyMember")
    public boolean familyMember;
    @SerializedName("Tenant")
    public boolean tenant;
    @SerializedName("IsManagement")
    public boolean isManagement;

    public boolean isManagement() {
        return isManagement;
    }

    public void setManagement(boolean management) {
        isManagement = management;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getcNIC() {
        return cNIC;
    }

    public void setcNIC(String cNIC) {
        this.cNIC = cNIC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isoTPVerifyed() {
        return oTPVerifyed;
    }

    public void setoTPVerifyed(boolean oTPVerifyed) {
        this.oTPVerifyed = oTPVerifyed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(boolean familyMember) {
        this.familyMember = familyMember;
    }

    public boolean isTenant() {
        return tenant;
    }

    public void setTenant(boolean tenant) {
        this.tenant = tenant;
    }
}
