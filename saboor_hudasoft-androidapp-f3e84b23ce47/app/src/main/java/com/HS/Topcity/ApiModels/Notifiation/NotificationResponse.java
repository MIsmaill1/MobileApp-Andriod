package com.HS.Topcity.ApiModels.Notifiation;

import com.google.gson.annotations.SerializedName;

public class NotificationResponse {

    @SerializedName("Status")
    public boolean status;
    @SerializedName("Notification")
    public NotificationModel notification;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public NotificationModel getNotification() {
        return notification;
    }

    public void setNotification(NotificationModel notification) {
        this.notification = notification;
    }
}
