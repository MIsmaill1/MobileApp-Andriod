package com.HS.Topcity.ApiModels.UserSharedAccountDetail;

import com.google.gson.annotations.SerializedName;

public class ComplainsDetailsModels {
    @SerializedName("ComplainId")
    public int ComplainId;

    @SerializedName("ComplainName")
    public String ComplainName;

    @SerializedName("ComplainNumber")
    public String ComplainNumber;

    @SerializedName("ComplainStatus")
    public String ComplainStatus;


    @SerializedName("PropertyName")
    public String PropertyName;


    @SerializedName("Image")
    public String Image;

    public int getComplainId() {
        return ComplainId;
    }

    public void setComplainId(int complainId) {
        ComplainId = complainId;
    }

    public String getComplainName() {
        return ComplainName;
    }

    public void setComplainName(String complainName) {
        ComplainName = complainName;
    }

    public String getComplainNumber() {
        return ComplainNumber;
    }

    public void setComplainNumber(String complainNumber) {
        ComplainNumber = complainNumber;
    }

    public String getComplainStatus() {
        return ComplainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        ComplainStatus = complainStatus;
    }

    public String getPropertyName() {
        return PropertyName;
    }

    public void setPropertyName(String propertyName) {
        PropertyName = propertyName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
