package com.HS.Topcity.ApiModels.NewsAndAnnouncements;

import com.HS.Topcity.ApiModels.Login.UserInfoModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsAndAnnouncementsResponse {

    @SerializedName("NewsAndAnnouncement")
    public ArrayList<NewsAndAnnouncementModel> newsAndAnnouncementModel;

    @SerializedName("Status")
    public Boolean status;

    @SerializedName("Message")
    public String message;


}
