package com.HS.Topcity.ApiModels.UpdateUserProfile;

import com.google.gson.annotations.SerializedName;

public class UpdateUserInfoRequest {


    @SerializedName("Email")
    public String email;

    @SerializedName("Name")
    public String Name;


    @SerializedName("ContactNumber")
    public String ContactNumber;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
}
