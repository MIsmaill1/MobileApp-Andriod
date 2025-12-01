package com.HS.Topcity.ApiModels.Masterplans;

import com.google.gson.annotations.SerializedName;

public class MasterplansResponse {

    @SerializedName("Status")
    public Boolean status;

    @SerializedName("MasterPlan")
    public MasterPlansModel masterPlanModels;


}
