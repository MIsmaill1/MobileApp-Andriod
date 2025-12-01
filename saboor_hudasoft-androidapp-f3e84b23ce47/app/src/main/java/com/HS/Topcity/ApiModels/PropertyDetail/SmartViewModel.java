package com.HS.Topcity.ApiModels.PropertyDetail;

import com.google.gson.annotations.SerializedName;

public class SmartViewModel {


    @SerializedName("TotlaPaid")
    public String totlaPaid;
    @SerializedName("InstallmentPayable")
    public String installmentPayable;
    @SerializedName("TotalBalance")
    public String totalBalance;
    @SerializedName("CurrentOutStanding")
    public String currentOutStanding;
    @SerializedName("SurchangePayable")
    public String surchangePayable;
    @SerializedName("SurchangePaid")
    public String surchangePaid;
    @SerializedName("SurchangeWavied")
    public String surchangeWavied;
    @SerializedName("SurchangeBalance")
    public String surchangeBalance;
    @SerializedName("NetOutStanding")
    public String netOutStanding;
    @SerializedName("GrossPayable")
    public String grossPayable;


    public String getTotlaPaid() {
        return totlaPaid;
    }

    public void setTotlaPaid(String totlaPaid) {
        this.totlaPaid = totlaPaid;
    }

    public String getInstallmentPayable() {
        return installmentPayable;
    }

    public void setInstallmentPayable(String installmentPayable) {
        this.installmentPayable = installmentPayable;
    }

    public String getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getCurrentOutStanding() {
        return currentOutStanding;
    }

    public void setCurrentOutStanding(String currentOutStanding) {
        this.currentOutStanding = currentOutStanding;
    }

    public String getSurchangePayable() {
        return surchangePayable;
    }

    public void setSurchangePayable(String surchangePayable) {
        this.surchangePayable = surchangePayable;
    }

    public String getSurchangePaid() {
        return surchangePaid;
    }

    public void setSurchangePaid(String surchangePaid) {
        this.surchangePaid = surchangePaid;
    }

    public String getSurchangeWavied() {
        return surchangeWavied;
    }

    public void setSurchangeWavied(String surchangeWavied) {
        this.surchangeWavied = surchangeWavied;
    }

    public String getSurchangeBalance() {
        return surchangeBalance;
    }

    public void setSurchangeBalance(String surchangeBalance) {
        this.surchangeBalance = surchangeBalance;
    }

    public String getNetOutStanding() {
        return netOutStanding;
    }

    public void setNetOutStanding(String netOutStanding) {
        this.netOutStanding = netOutStanding;
    }

    public String getGrossPayable() {
        return grossPayable;
    }

    public void setGrossPayable(String grossPayable) {
        this.grossPayable = grossPayable;
    }
}
