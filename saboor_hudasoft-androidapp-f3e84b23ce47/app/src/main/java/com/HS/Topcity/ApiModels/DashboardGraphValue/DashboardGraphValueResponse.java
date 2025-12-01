package com.HS.Topcity.ApiModels.DashboardGraphValue;

import com.google.gson.annotations.SerializedName;

public class DashboardGraphValueResponse {

    @SerializedName("Status")
    public boolean status;
    @SerializedName("GraphResponseValues")
    public GraphResponseValues graphResponseValues;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public GraphResponseValues getGraphResponseValues() {
        return graphResponseValues;
    }

    public void setGraphResponseValues(GraphResponseValues graphResponseValues) {
        this.graphResponseValues = graphResponseValues;
    }
}
