package com.HS.Topcity.ApiModels.ComplaintType;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ComplaintTypeResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("ComplaintType")
    public ArrayList<ComplaintType> complaintType;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<ComplaintType> getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(ArrayList<ComplaintType> complaintType) {
        this.complaintType = complaintType;
    }
}
