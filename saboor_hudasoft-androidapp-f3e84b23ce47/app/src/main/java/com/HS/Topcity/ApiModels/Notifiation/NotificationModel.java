package com.HS.Topcity.ApiModels.Notifiation;

import com.google.gson.annotations.SerializedName;

public class NotificationModel {
    @SerializedName("NotificationToId")
    public int notificationToId;
    @SerializedName("NotificationSubject")
    public String notificationSubject;
    @SerializedName("NotificationDetails")
    public String notificationDetails;
    @SerializedName("IsNotificationView")
    public boolean isNotificationView;

    public int getNotificationToId() {
        return notificationToId;
    }

    public void setNotificationToId(int notificationToId) {
        this.notificationToId = notificationToId;
    }

    public String getNotificationSubject() {
        return notificationSubject;
    }

    public void setNotificationSubject(String notificationSubject) {
        this.notificationSubject = notificationSubject;
    }

    public String getNotificationDetails() {
        return notificationDetails;
    }

    public void setNotificationDetails(String notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    public boolean isNotificationView() {
        return isNotificationView;
    }

    public void setNotificationView(boolean notificationView) {
        isNotificationView = notificationView;
    }
}
