package com.HS.Topcity.ApiModels.PaymentDetail;

import com.google.gson.annotations.SerializedName;

public class PaymentDetailRequest {
    @SerializedName("Token")
    public String Token;

    @SerializedName("RegistrationNumber")
    public String RegistrationNumber;
    @SerializedName("Plot_No")
    public String Plot_No;

    @SerializedName("PlotSize")
    public String PlotSize;

    @SerializedName("TypeOfPlot")
    public String TypeOfPlot;

    @SerializedName("Rupees")
    public String Rupees;

    @SerializedName("Email")
    public String Email;

    @SerializedName("Block")
    public String Block;

    @SerializedName("TransactionId")
    public String TransactionId;

    @SerializedName("AmountInWords")
    public String AmountInWords;

    @SerializedName("CNIC")
    public String CNIC;

    @SerializedName("ReceivedWith")
    public String ReceivedWith;

    public String getReceivedWith() {
        return ReceivedWith;
    }

    public void setReceivedWith(String receivedWith) {
        ReceivedWith = receivedWith;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getRegistrationNumber() {
        return RegistrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        RegistrationNumber = registrationNumber;
    }

    public String getPlot_No() {
        return Plot_No;
    }

    public void setPlot_No(String plot_No) {
        Plot_No = plot_No;
    }

    public String getPlotSize() {
        return PlotSize;
    }

    public void setPlotSize(String plotSize) {
        PlotSize = plotSize;
    }

    public String getTypeOfPlot() {
        return TypeOfPlot;
    }

    public void setTypeOfPlot(String typeOfPlot) {
        TypeOfPlot = typeOfPlot;
    }

    public String getRupees() {
        return Rupees;
    }

    public void setRupees(String rupees) {
        Rupees = rupees;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBlock() {
        return Block;
    }

    public void setBlock(String block) {
        Block = block;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public String getAmountInWords() {
        return AmountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        AmountInWords = amountInWords;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }
}
