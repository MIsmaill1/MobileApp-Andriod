package com.HS.Topcity.ApiModels.ComplaintDetail;

import com.google.gson.annotations.SerializedName;

public class ComplaintDetailResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("ComplaintModel")
    public ComplaintModel complaintModel;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ComplaintModel getComplaintModel() {
        return complaintModel;
    }

    public void setComplaintModel(ComplaintModel complaintModel) {
        this.complaintModel = complaintModel;
    }
}
