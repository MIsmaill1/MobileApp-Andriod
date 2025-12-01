package com.HS.Topcity.ApiModels.ManagementDashboardCount;

import com.google.gson.annotations.SerializedName;

public class ManagementDashboardCountResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("ManagementResponeModel")
    public ManagementResponeModel managementResponeModel;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ManagementResponeModel getManagementResponeModel() {
        return managementResponeModel;
    }

    public void setManagementResponeModel(ManagementResponeModel managementResponeModel) {
        this.managementResponeModel = managementResponeModel;
    }
}
