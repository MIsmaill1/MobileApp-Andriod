package com.HS.Topcity.ApiModels.FingerprintLogin;

import com.google.gson.annotations.SerializedName;

public class FingerprintLoginRequest {

    @SerializedName("Email")
    public String email;
    @SerializedName("CNIC")
    public String cNIC;
    @SerializedName("DeviceId")
    public String deviceId;
    @SerializedName("DeviceFCMToken")
    public String deviceFCMToken;
    @SerializedName("IsIOSOrAndroid")
    public boolean isIOSOrAndroid;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getcNIC() {
        return cNIC;
    }

    public void setcNIC(String cNIC) {
        this.cNIC = cNIC;
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
