package com.HS.Topcity.ApiModels.EventDetails;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventDetailsResponse {
    @SerializedName("Events")
    public ArrayList<EventsDetailsModel> Eventsmodel;

    @SerializedName("Status")
    public boolean status;

    @SerializedName("Message")
    public String message;
}
