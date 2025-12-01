package com.HS.Topcity.ApiModels.UserProperties;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserPropertiesResponse {

    @SerializedName("Properties")
    public ArrayList<Properties> properties;

    @SerializedName("Status")
    public Boolean status;

    @SerializedName("message")
    public String message;
}
