package com.HS.Topcity.ApiModels.ContactUs;

import com.google.gson.annotations.SerializedName;

public class ContactUsResponse {

    @SerializedName("Status")
    public boolean status;
    @SerializedName("ContactUs")
    public ContactUs contactUs;
}
