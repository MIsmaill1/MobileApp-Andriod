package com.HS.Topcity.Models;

public class PropertiesModel {

    int id;
    String address;
    String Image;
    String LinkToProperty;
    Boolean IsOwner;
    Boolean IsDetail;
    public PropertiesModel() {
    }

    public PropertiesModel(int id, String address, String image) {
        this.id = id;
        this.address = address;
        Image = image;
    }

    public String getLinkToProperty() {
        return LinkToProperty;
    }

    public void setLinkToProperty(String linkToProperty) {
        LinkToProperty = linkToProperty;
    }

    public Boolean getOwner() {
        return IsOwner;
    }

    public void setOwner(Boolean owner) {
        IsOwner = owner;
    }

    public Boolean getDetail() {
        return IsDetail;
    }

    public void setDetail(Boolean detail) {
        IsDetail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
