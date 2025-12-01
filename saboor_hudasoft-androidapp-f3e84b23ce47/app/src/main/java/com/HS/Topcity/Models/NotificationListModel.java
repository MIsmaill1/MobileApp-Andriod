package com.HS.Topcity.Models;

public class NotificationListModel {

    public int notificationToId;
    public String notificationSubject;
    public String notificationDetails;
    public boolean isNotificationView;
    public String NotificationImage;
    public int DaysAgo;


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
