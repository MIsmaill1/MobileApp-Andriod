package com.HS.Topcity.ApiModels.ComplaintCreate;

import com.google.gson.annotations.SerializedName;

public class ComplaintCreateRequest {

    @SerializedName("ComplaintTypeId")
    public int ComplaintTypeId;
    @SerializedName("PropertyId")
    public int PropertyId;

    @SerializedName("ComplaintSubTypeId")
    public int ComplaintSubTypeId;

    @SerializedName("ComplaintSubject")
    public String ComplaintSubject;
    @SerializedName("ComplaintDetails")
    public String ComplaintDetails;

    @SerializedName("images")
    public String images;

}
