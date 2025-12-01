package com.HS.Topcity.ApiModels.DashboardGraphValue;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GraphResponseValues {
    @SerializedName("GraphValues")
    public ArrayList<GraphValue> graphValues;
    @SerializedName("ComplainName")
    public String complainName;
    @SerializedName("FromWhen")
    public String fromWhen;
    @SerializedName("DateString")
    public String dateString;

    public ArrayList<GraphValue> getGraphValues() {
        return graphValues;
    }

    public void setGraphValues(ArrayList<GraphValue> graphValues) {
        this.graphValues = graphValues;
    }

    public String getComplainName() {
        return complainName;
    }

    public void setComplainName(String complainName) {
        this.complainName = complainName;
    }

    public String getFromWhen() {
        return fromWhen;
    }

    public void setFromWhen(String fromWhen) {
        this.fromWhen = fromWhen;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
