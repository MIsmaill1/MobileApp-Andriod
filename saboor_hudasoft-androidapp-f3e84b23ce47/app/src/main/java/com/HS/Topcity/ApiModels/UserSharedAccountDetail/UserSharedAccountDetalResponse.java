package com.HS.Topcity.ApiModels.UserSharedAccountDetail;

import com.google.gson.annotations.SerializedName;

public class UserSharedAccountDetalResponse {
    @SerializedName("Status")
    public boolean status;
    @SerializedName("ShareAccountUserInfo")
    public ShareAccountUserInfoModel shareAccountUserInfoModel;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ShareAccountUserInfoModel getShareAccountUserInfoModel() {
        return shareAccountUserInfoModel;
    }

    public void setShareAccountUserInfoModel(ShareAccountUserInfoModel shareAccountUserInfoModel) {
        this.shareAccountUserInfoModel = shareAccountUserInfoModel;
    }
}
