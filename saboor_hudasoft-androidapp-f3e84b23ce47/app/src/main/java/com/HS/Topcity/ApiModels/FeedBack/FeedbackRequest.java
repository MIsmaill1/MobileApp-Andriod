package com.HS.Topcity.ApiModels.FeedBack;

import com.google.gson.annotations.SerializedName;

public class FeedbackRequest {
    @SerializedName("Name")
    public String name;
    @SerializedName("PhoneNumber")
    public String phoneNumber;
    @SerializedName("Email")
    public String email;
    @SerializedName("Subject")
    public String subject;
    @SerializedName("Message")
    public String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
