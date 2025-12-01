package com.HS.Topcity.ApiModels.ProfileSetting;

import com.HS.Topcity.ApiModels.NewsAndAnnouncements.NewsAndAnnouncementModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserInfoDetailsResponse {

    @SerializedName("UserInfo")
    public UserInfoModels userInfo;

    @SerializedName("Status")
    public Boolean status;

    @SerializedName("Message")
    public String message;
}
