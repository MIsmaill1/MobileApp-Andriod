package com.HS.Topcity.ApiModels.ComplaintDetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComplaintModel {
    @SerializedName("ComplaintId")
    public int complaintId;
    @SerializedName("ComplaintTypeId")
    public int complaintTypeId;
    @SerializedName("ComplainName")
    public String complainName;
    @SerializedName("ComplainNumber")
    public String complainNumber;
    @SerializedName("PropertyAddress")
    public String propertyAddress;
    @SerializedName("ComplaintSubject")
    public String complaintSubject;
    @SerializedName("ComplaintDetails")
    public String complaintDetails;
    @SerializedName("ComplainDateTime")
    public String ComplainDateTime;
    @SerializedName("ComplaintImages")
    public List<String> complaintImages;
    @SerializedName("ComplaintStatus")
    public String complaintStatus;
    @SerializedName("DaysAgo")
    public int daysAgo;
    @SerializedName("UserName")
    public String UserName;
    @SerializedName("ComplaintClosedRemarks")
    public String complaint_remarks;

    public String getComplaint_remarks() {
        return complaint_remarks;
    }

    public void setComplaint_remarks(String complaint_remarks) {
        this.complaint_remarks = complaint_remarks;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getComplainDateTime() {
        return ComplainDateTime;
    }

    public void setComplainDateTime(String complainDateTime) {
        ComplainDateTime = complainDateTime;
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

    public List<String> getComplaintImages() {
        return complaintImages;
    }

    public void setComplaintImages(List<String> complaintImages) {
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
