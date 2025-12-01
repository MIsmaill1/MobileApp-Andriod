package com.HS.Topcity.ApiModels.RecentlyAddedComplaint;

import com.google.gson.annotations.SerializedName;

public class ComplainList {

    @SerializedName("ComplainId")
    public int complainId;
    @SerializedName("ComplainName")
    public String complainName;
    @SerializedName("ComplainNumber")
    public String complainNumber;
    @SerializedName("ComplainStatus")
    public String complainStatus;
    @SerializedName("PropertyName")
    public String propertyName;
    @SerializedName("Image")
    public String image;


    public int getComplainId() {
        return complainId;
    }

    public void setComplainId(int complainId) {
        this.complainId = complainId;
    }

    public String getComplainName() {
        return complainName;
    }

    public void setComplainName(String complainName) {
        this.complainName = complainName;
    }

    public String getComplainNumber() {
        return complainNumber;
    }

    public void setComplainNumber(String complainNumber) {
        this.complainNumber = complainNumber;
    }

    public String getComplainStatus() {
        return complainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
