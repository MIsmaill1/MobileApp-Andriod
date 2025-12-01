package com.HS.Topcity.ApiModels.Login;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("Email")
    public String Email;

    @SerializedName("Password")
    public String Password;


    @SerializedName("DeviceId")
    public String deviceId;
    @SerializedName("DeviceFCMToken")
    public String deviceFCMToken;
    @SerializedName("IsIOSOrAndroid")
    public boolean isIOSOrAndroid;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceFCMToken() {
        return deviceFCMToken;
    }

    public void setDeviceFCMToken(String deviceFCMToken) {
        this.deviceFCMToken = deviceFCMToken;
    }

    public boolean isIOSOrAndroid() {
        return isIOSOrAndroid;
    }

    public void setIOSOrAndroid(boolean IOSOrAndroid) {
        isIOSOrAndroid = IOSOrAndroid;
    }
}
