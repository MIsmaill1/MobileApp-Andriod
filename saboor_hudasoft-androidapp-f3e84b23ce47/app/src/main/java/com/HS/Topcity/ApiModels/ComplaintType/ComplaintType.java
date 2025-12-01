package com.HS.Topcity.ApiModels.ComplaintType;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ComplaintType {

    @SerializedName("ComplaintTypeId")
    public int complaintTypeId;
    @SerializedName("ComplaintTypeName")
    public String complaintTypeName;
    @SerializedName("ComplaintTypeImage")
    public String complaintTypeImage;
    @SerializedName("ComplainSubTypesModels")
    public ArrayList<ComplainSubTypesModel> complainSubTypesModels;

    public int getComplaintTypeId() {
        return complaintTypeId;
    }

    public void setComplaintTypeId(int complaintTypeId) {
        this.complaintTypeId = complaintTypeId;
    }

    public String getComplaintTypeName() {
        return complaintTypeName;
    }

    public void setComplaintTypeName(String complaintTypeName) {
        this.complaintTypeName = complaintTypeName;
    }

    public String getComplaintTypeImage() {
        return complaintTypeImage;
    }

    public void setComplaintTypeImage(String complaintTypeImage) {
        this.complaintTypeImage = complaintTypeImage;
    }

    public ArrayList<ComplainSubTypesModel> getComplainSubTypesModels() {
        return complainSubTypesModels;
    }

    public void setComplainSubTypesModels(ArrayList<ComplainSubTypesModel> complainSubTypesModels) {
        this.complainSubTypesModels = complainSubTypesModels;
    }
}
