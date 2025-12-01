package com.HS.Topcity.ApiModels.Logout;

import com.google.gson.annotations.SerializedName;

public class LogoutRequest {
    @SerializedName("DeviceId")
    public String DeviceId;
    @SerializedName("IsIOSorAndroid")
    public Boolean IsIOSorAndroid;


    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public Boolean getIOSorAndroid() {
        return IsIOSorAndroid;
    }

    public void setIOSorAndroid(Boolean IOSorAndroid) {
        IsIOSorAndroid = IOSorAndroid;
    }
}
