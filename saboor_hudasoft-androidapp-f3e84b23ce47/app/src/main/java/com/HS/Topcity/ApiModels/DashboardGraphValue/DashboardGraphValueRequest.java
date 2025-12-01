package com.HS.Topcity.ApiModels.DashboardGraphValue;

import com.google.gson.annotations.SerializedName;

public class DashboardGraphValueRequest {
    @SerializedName("ComplainTypeId")
    public int complainTypeId;
    @SerializedName("FromWhen")
    public int fromWhen;

    public int getComplainTypeId() {
        return complainTypeId;
    }

    public void setComplainTypeId(int complainTypeId) {
        this.complainTypeId = complainTypeId;
    }

    public int getFromWhen() {
        return fromWhen;
    }

    public void setFromWhen(int fromWhen) {
        this.fromWhen = fromWhen;
    }
}
