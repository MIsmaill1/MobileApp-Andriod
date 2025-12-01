package com.HS.Topcity.ApiModels.Notifiation;

import com.google.gson.annotations.SerializedName;

public class NotificationRequest {



    @SerializedName("NotificationToId")
    public int NotificationToId;


    public int getNotificationToId() {
        return NotificationToId;
    }

    public void setNotificationToId(int notificationToId) {
        NotificationToId = notificationToId;
    }
}
