package com.HS.Topcity.ApiModels.ComplaintType;

import com.google.gson.annotations.SerializedName;

public class ComplainSubTypesModel {

    @SerializedName("ComplaintSubTypeId")
    public int complaintSubTypeId;
    @SerializedName("ComplaintSubTypeName")
    public String complaintSubTypeName;

    public int getComplaintSubTypeId() {
        return complaintSubTypeId;
    }

    public void setComplaintSubTypeId(int complaintSubTypeId) {
        this.complaintSubTypeId = complaintSubTypeId;
    }

    public String getComplaintSubTypeName() {
        return complaintSubTypeName;
    }

    public void setComplaintSubTypeName(String complaintSubTypeName) {
        this.complaintSubTypeName = complaintSubTypeName;
    }
}
