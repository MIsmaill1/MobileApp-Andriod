package com.HS.Topcity.ApiModels.Event;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventResponse {

    @SerializedName("EventsImages")
    public List<String> image;

    @SerializedName("Status")
    public boolean status;

    @SerializedName("Message")
    public String message;
}
