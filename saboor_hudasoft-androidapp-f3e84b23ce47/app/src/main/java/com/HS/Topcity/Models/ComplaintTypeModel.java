package com.HS.Topcity.Models;

import java.util.ArrayList;

public class ComplaintTypeModel {


    public int complaintTypeId;

    public String complaintTypeName;

    public String complaintTypeImage;

    public ArrayList<ComplainSubTypesModels> complainSubTypesModels;


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

    public ArrayList<ComplainSubTypesModels> getComplainSubTypesModels() {
        return complainSubTypesModels;
    }

    public void setComplainSubTypesModels(ArrayList<ComplainSubTypesModels> complainSubTypesModels) {
        this.complainSubTypesModels = complainSubTypesModels;
    }
}
