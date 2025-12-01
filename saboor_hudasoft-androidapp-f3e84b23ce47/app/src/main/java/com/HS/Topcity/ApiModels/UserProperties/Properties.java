package com.HS.Topcity.ApiModels.UserProperties;

import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("Id")
    public int Id;

    @SerializedName("Address")
    public String Address;

    @SerializedName("LinkToProperty")
    public String LinkToProperty;

    @SerializedName("IsDetail")
    public Boolean IsDetail;

    @SerializedName("IsOwner")
    public Boolean IsOwner;
    @SerializedName("Image")
    public String Image;


    public Boolean getOwner() {
        return IsOwner;
    }

    public void setOwner(Boolean owner) {
        IsOwner = owner;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAddress() {
        return Address;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLinkToProperty() {
        return LinkToProperty;
    }

    public void setLinkToProperty(String linkToProperty) {
        LinkToProperty = linkToProperty;
    }

    public Boolean getDetail() {
        return IsDetail;
    }

    public void setDetail(Boolean detail) {
        IsDetail = detail;
    }
}
