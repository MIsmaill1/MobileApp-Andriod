package com.HS.Topcity.ApiModels.RecentlyAddedComplaint;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecentlyAddedComplaintResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("ComplainList")
    public ArrayList<ComplainList> complainList;
}
