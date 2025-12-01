package com.HS.Topcity.ApiModels.GuestNotification;

import com.google.gson.annotations.SerializedName;

public class GuestNotificationRequest {

    @SerializedName("DeviceId")
    public String deviceId;
    @SerializedName("DeviceFCMToken")
    public String deviceFCMToken;
    @SerializedName("IsIOSorAndroid")
    public boolean isIOSorAndroid;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceIds(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceFCMToken() {
        return deviceFCMToken;
    }

    public void setDeviceFCMTokens(String deviceFCMToken) {
        this.deviceFCMToken = deviceFCMToken;
    }

    public boolean isIOSorAndroid() {
        return isIOSorAndroid;
    }

    public void setIOSorAndroid(boolean IOSorAndroid) {
        isIOSorAndroid = IOSorAndroid;
    }
}
