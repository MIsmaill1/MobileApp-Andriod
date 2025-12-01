package com.HS.Topcity.ApiModels.NotificationList;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NotificationListResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("NotificationBadgeCount")
    public int NotificationBadgeCount;
    @SerializedName("NotificationsList")
    public ArrayList<NotificationsList> notificationsList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<NotificationsList> getNotificationsList() {
        return notificationsList;
    }

    public void setNotificationsList(ArrayList<NotificationsList> notificationsList) {
        this.notificationsList = notificationsList;
    }

    public int getNotificationBadgeCount() {
        return NotificationBadgeCount;
    }

    public void setNotificationBadgeCount(int notificationBadgeCount) {
        NotificationBadgeCount = notificationBadgeCount;
    }
}
