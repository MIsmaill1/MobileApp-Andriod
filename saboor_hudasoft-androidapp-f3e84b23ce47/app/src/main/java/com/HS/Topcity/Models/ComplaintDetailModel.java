package com.HS.Topcity.Models;

import java.util.ArrayList;

public class ComplaintDetailModel {

    public int complaintId;

    public int complaintTypeId;

    public String complainName;
    public String complainNumber;

    public String propertyAddress;

    public String complaintSubject;
    public String date;

    public String complaintDetails;

    public ArrayList<String> complaintImages;

    public String complaintStatus;

    public int daysAgo;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public int getComplaintTypeId() {
        return complaintTypeId;
    }

    public void setComplaintTypeId(int complaintTypeId) {
        this.complaintTypeId = complaintTypeId;
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

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getComplaintSubject() {
        return complaintSubject;
    }

    public void setComplaintSubject(String complaintSubject) {
        this.complaintSubject = complaintSubject;
    }

    public String getComplaintDetails() {
        return complaintDetails;
    }

    public void setComplaintDetails(String complaintDetails) {
        this.complaintDetails = complaintDetails;
    }

    public ArrayList<String> getComplaintImages() {
        return complaintImages;
    }

    public void setComplaintImages(ArrayList<String> complaintImages) {
        this.complaintImages = complaintImages;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public int getDaysAgo() {
        return daysAgo;
    }

    public void setDaysAgo(int daysAgo) {
        this.daysAgo = daysAgo;
    }
}
