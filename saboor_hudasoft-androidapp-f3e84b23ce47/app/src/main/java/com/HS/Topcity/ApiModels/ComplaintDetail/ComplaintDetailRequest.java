package com.HS.Topcity.ApiModels.ComplaintDetail;

import com.google.gson.annotations.SerializedName;

public class ComplaintDetailRequest {
    @SerializedName("ComplaintId")
    public int ComplaintId;

    public int getComplaintId() {
        return ComplaintId;
    }

    public void setComplaintId(int complaintId) {
        ComplaintId = complaintId;
    }
}
