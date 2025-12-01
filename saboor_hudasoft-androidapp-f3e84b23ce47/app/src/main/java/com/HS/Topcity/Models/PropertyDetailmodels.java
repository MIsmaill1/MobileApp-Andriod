package com.HS.Topcity.Models;

import java.util.ArrayList;

public class PropertyDetailmodels {
    public int propertyId;
    public String propertyAddress;
    public ArrayList<String> propertyImages;
    public String block;
    public String plotNumber;
    public String propertyType;
    public String registrationNumber;
    public ArrayList<String> sharedAccountImages;
    public int sharedAccountCount;

    public PropertyDetailmodels() {
    }

    public PropertyDetailmodels(int propertyId, String propertyAddress, ArrayList<String> propertyImages, String block, String plotNumber, String propertyType, String registrationNumber, ArrayList<String> sharedAccountImages, int sharedAccountCount) {
        this.propertyId = propertyId;
        this.propertyAddress = propertyAddress;
        this.propertyImages = propertyImages;
        this.block = block;
        this.plotNumber = plotNumber;
        this.propertyType = propertyType;
        this.registrationNumber = registrationNumber;
        this.sharedAccountImages = sharedAccountImages;
        this.sharedAccountCount = sharedAccountCount;
    }

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
