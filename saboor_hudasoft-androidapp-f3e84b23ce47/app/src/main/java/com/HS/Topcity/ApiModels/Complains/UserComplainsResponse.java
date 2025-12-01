package com.HS.Topcity.ApiModels.Complains;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserComplainsResponse {

    @SerializedName("ComplainList")
    public ArrayList<ComplainListModel> complainListModels;

    @SerializedName("Status")
    public boolean status;


}
