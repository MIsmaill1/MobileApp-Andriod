package com.HS.Topcity.ApiModels.NotificationList;

import com.google.gson.annotations.SerializedName;

public class NotificationsList {

    @SerializedName("NotificationToId")
    public int notificationToId;
    @SerializedName("NotificationSubject")
    public String notificationSubject;
    @SerializedName("NotificationDetails")
    public String notificationDetails;
    @SerializedName("DaysAgo")
    public int DaysAgo;
    @SerializedName("IsNotificationView")
    public boolean isNotificationView;
    @SerializedName("NotificationImage")
    public String NotificationImage;


    public String getNotificationImage() {
        return NotificationImage;
    }

    public void setNotificationImage(String notificationImage) {
        NotificationImage = notificationImage;
    }

    public int getDaysAgo() {
        return DaysAgo;
    }

    public void setDaysAgo(int daysAgo) {
        DaysAgo = daysAgo;
    }

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
