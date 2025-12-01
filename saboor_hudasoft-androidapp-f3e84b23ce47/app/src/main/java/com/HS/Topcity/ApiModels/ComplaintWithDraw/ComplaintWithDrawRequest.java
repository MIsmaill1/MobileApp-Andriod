package com.HS.Topcity.ApiModels.ComplaintWithDraw;

import com.google.gson.annotations.SerializedName;

public class ComplaintWithDrawRequest {
    @SerializedName("ComplaintId")
    public int ComplaintId;

    public int getComplaintId() {
        return ComplaintId;
    }

    public void setComplaintId(int complaintId) {
        ComplaintId = complaintId;
    }
}
