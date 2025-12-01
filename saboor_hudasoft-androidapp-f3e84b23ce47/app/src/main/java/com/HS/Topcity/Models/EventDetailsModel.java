package com.HS.Topcity.Models;

import java.util.List;

public class EventDetailsModel {

    int Id;
    String EventName;
    String Descriptions;
    List<String> images;
    String EventDate;
    String EventTime;

    public EventDetailsModel() {
    }

    public EventDetailsModel(int id, String eventName, String descriptions, List<String> images) {
        Id = id;
        EventName = eventName;
        Descriptions = descriptions;
        this.images = images;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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
