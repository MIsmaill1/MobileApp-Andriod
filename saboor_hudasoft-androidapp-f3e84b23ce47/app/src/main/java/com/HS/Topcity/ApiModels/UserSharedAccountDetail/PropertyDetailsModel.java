package com.HS.Topcity.ApiModels.UserSharedAccountDetail;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PropertyDetailsModel {

    @SerializedName("PropertyId")
    public int propertyId;
    @SerializedName("Block")
    public String block;
    @SerializedName("PlotNo")
    public String plotNo;
    @SerializedName("PropertyType")
    public String propertyType;
    @SerializedName("RegistrationNumber")
    public String registrationNumber;
    @SerializedName("PropertiesImages")
    public ArrayList<String> propertiesImages;


    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getPlotNo() {
        return plotNo;
    }

    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public ArrayList<String> getPropertiesImages() {
        return propertiesImages;
    }

    public void setPropertiesImages(ArrayList<String> propertiesImages) {
        this.propertiesImages = propertiesImages;
    }
}
