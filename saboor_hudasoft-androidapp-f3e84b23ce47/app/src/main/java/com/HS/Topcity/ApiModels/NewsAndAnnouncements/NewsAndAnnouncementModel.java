package com.HS.Topcity.ApiModels.NewsAndAnnouncements;

import com.google.gson.annotations.SerializedName;

public class NewsAndAnnouncementModel {

    @SerializedName("Id")
    public int id;
    @SerializedName("NewsName")
    public String newsName;
    @SerializedName("DateOfNews")
    public String DateofNews;
    @SerializedName("TimeOfNews")
    public  String TimeOfNews;
    @SerializedName("Descriptions")
    public  String   Descriptions;
    @SerializedName("Note")
    public String notes;
    @SerializedName("Image")
    public String    Image;


}
