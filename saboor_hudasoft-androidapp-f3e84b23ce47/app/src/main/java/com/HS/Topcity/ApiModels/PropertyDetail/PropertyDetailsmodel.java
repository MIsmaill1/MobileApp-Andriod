package com.HS.Topcity.ApiModels.PropertyDetail;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PropertyDetailsmodel {

    @SerializedName("PropertyId")
    public int propertyId;
    @SerializedName("PropertyAddress")
    public String propertyAddress;
    @SerializedName("PropertyImages")
    public ArrayList<String> propertyImages;
    @SerializedName("Block")
    public String block;
    @SerializedName("PlotNumber")
    public String plotNumber;
    @SerializedName("PropertyType")
    public String propertyType;
    @SerializedName("RegistrationNumber")
    public String registrationNumber;
    public ArrayList<PropertyOwnersDetailsModel> propertyOwnersDetailsModels;
    @SerializedName("DetailsViewModel")
    public DetailsViewModel detailsViewModel;
    @SerializedName("SmartViewModel")
    public SmartViewModel smartViewModel;
    @SerializedName("SharedAccountImages")
    public ArrayList<String> sharedAccountImages;
    @SerializedName("SharedAccountCount")
    public int sharedAccountCount;



    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public ArrayList<String> getPropertyImages() {
        return propertyImages;
    }

    public void setPropertyImages(ArrayList<String> propertyImages) {
        this.propertyImages = propertyImages;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
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

    public ArrayList<PropertyOwnersDetailsModel> getPropertyOwnersDetailsModels() {
        return propertyOwnersDetailsModels;
    }

    public void setPropertyOwnersDetailsModels(ArrayList<PropertyOwnersDetailsModel> propertyOwnersDetailsModels) {
        this.propertyOwnersDetailsModels = propertyOwnersDetailsModels;
    }

    public DetailsViewModel getDetailsViewModel() {
        return detailsViewModel;
    }

    public void setDetailsViewModel(DetailsViewModel detailsViewModel) {
        this.detailsViewModel = detailsViewModel;
    }

    public SmartViewModel getSmartViewModel() {
        return smartViewModel;
    }

    public void setSmartViewModel(SmartViewModel smartViewModel) {
        this.smartViewModel = smartViewModel;
    }

    public ArrayList<String> getSharedAccountImages() {
        return sharedAccountImages;
    }

    public void setSharedAccountImages(ArrayList<String> sharedAccountImages) {
        this.sharedAccountImages = sharedAccountImages;
    }

    public int getSharedAccountCount() {
        return sharedAccountCount;
    }

    public void setSharedAccountCount(int sharedAccountCount) {
        this.sharedAccountCount = sharedAccountCount;
    }
}
