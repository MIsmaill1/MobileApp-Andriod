package com.HS.Topcity.Models;

public class ManageDashboardModel {
    int id ;
    int image_active;
    int image_unactive;
    int text;


    public ManageDashboardModel(int id, int image_active, int image_unactive, int text) {
        this.id = id;
        this.image_active = image_active;
        this.image_unactive = image_unactive;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage_active() {
        return image_active;
    }

    public void setImage_active(int image_active) {
        this.image_active = image_active;
    }

    public int getImage_unactive() {
        return image_unactive;
    }

    public void setImage_unactive(int image_unactive) {
        this.image_unactive = image_unactive;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }
}
