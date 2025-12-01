package com.HS.Topcity.ApiModels.UpdatePass;

import com.google.gson.annotations.SerializedName;

public class UpdatePassRequest {
    @SerializedName("NewPassword")
    public String NewPassword;

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }
}
