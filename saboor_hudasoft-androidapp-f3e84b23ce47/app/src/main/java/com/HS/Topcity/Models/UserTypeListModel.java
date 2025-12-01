package com.HS.Topcity.Models;

public class UserTypeListModel {

    String name;
    int id;

    public UserTypeListModel(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public UserTypeListModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
