package com.HS.Topcity.Models;

public class DetailViewModels {

    public String installmentPayable;

    public String totalBalance;

    public String currentOutStanding;

    public String surchangePayable;

    public String surchangePaid;

    public String surchangeWavied;

    public String surchangeBalance;

    public String netOutStanding;

    public String grossPayable;

    public DetailViewModels() {
    }

    public DetailViewModels(String installmentPayable, String totalBalance, String currentOutStanding, String surchangePayable, String surchangePaid, String surchangeWavied, String surchangeBalance, String netOutStanding, String grossPayable) {
        this.installmentPayable = installmentPayable;
        this.totalBalance = totalBalance;
        this.currentOutStanding = currentOutStanding;
        this.surchangePayable = surchangePayable;
        this.surchangePaid = surchangePaid;
        this.surchangeWavied = surchangeWavied;
        this.surchangeBalance = surchangeBalance;
        this.netOutStanding = netOutStanding;
        this.grossPayable = grossPayable;
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
