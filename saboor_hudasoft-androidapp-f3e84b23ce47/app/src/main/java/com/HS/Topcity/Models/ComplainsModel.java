package com.HS.Topcity.Models;

public class ComplainsModel {

    int id;
    String number;
    String name;
    String Status;
    String Image;
    String propertyname;

    public ComplainsModel() {
    }

    public ComplainsModel(int id, String number, String name, String status, String image,String propertyname) {
        this.id = id;
        this.number = number;
        this.name = name;
        Status = status;
        Image = image;
        this.propertyname =  propertyname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getImage() {
        return Image;
    }

    public String getPropertyname() {
        return propertyname;
    }

    public void setPropertyname(String propertyname) {
        this.propertyname = propertyname;
    }

    public void setImage(String image) {
        Image = image;


    }
}
