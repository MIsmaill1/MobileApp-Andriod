package com.HS.Topcity.ApiModels.ManagementDashboardCount;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ManagementResponeModel {

    @SerializedName("Users")
    public int users;
    @SerializedName("Complains")
    public int complains;
    public ArrayList<UserandComplain> userandComplains;

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getComplains() {
        return complains;
    }

    public void setComplains(int complains) {
        this.complains = complains;
    }

    public ArrayList<UserandComplain> getUserandComplains() {
        return userandComplains;
    }

    public void setUserandComplains(ArrayList<UserandComplain> userandComplains) {
        this.userandComplains = userandComplains;
    }
}
