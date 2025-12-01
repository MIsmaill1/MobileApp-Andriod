package com.HS.Topcity.ApiModels.EventDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsDetailsModel {

    @SerializedName("Id")
    public int id;

    @SerializedName("EventName")
    public String eventName;
    @SerializedName("Descriptions")
    public String descriptions;

    @SerializedName("EventDate")
    public String EventDate;

    @SerializedName("EventTime")
    public String EventTime;

    @SerializedName("Image")
    public List<String> image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public String getEventTime() {
        return EventTime;
    }

    public void setEventTime(String eventTime) {
        EventTime = eventTime;
    }

}
