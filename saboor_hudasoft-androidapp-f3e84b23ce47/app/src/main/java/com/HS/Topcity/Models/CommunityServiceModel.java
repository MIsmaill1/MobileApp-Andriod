package com.HS.Topcity.Models;

public class CommunityServiceModel {

    int id;
    String name;
    String image;
    int Images;

    public CommunityServiceModel(int id, String name, int images) {
        this.id = id;
        this.name = name;
        this.Images = images;
    }

    public CommunityServiceModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImages() {
        return Images;
    }

    public void setImages(int images) {
        Images = images;
    }
}
