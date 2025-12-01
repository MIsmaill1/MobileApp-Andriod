package com.HS.Topcity.ApiModels.FeedBack;

import com.google.gson.annotations.SerializedName;

public class FeedbackResponse {
    @SerializedName("Status")
    public boolean  status;

    @SerializedName("Message")
    public String message;
}
