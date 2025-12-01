package com.HS.Topcity.Models;

public class SmartViewModels {


    public String totlaPaid;

    public String installmentPayable;

    public String totalBalance;

    public String currentOutStanding;

    public String surchangePayable;

    public String surchangePaid;

    public String surchangeWavied;

    public String surchangeBalance;

    public String netOutStanding;


    public SmartViewModels() {
    }

    public SmartViewModels(String totlaPaid, String installmentPayable, String totalBalance, String currentOutStanding, String surchangePayable, String surchangePaid, String surchangeWavied, String surchangeBalance, String netOutStanding) {
        this.totlaPaid = totlaPaid;
        this.installmentPayable = installmentPayable;
        this.totalBalance = totalBalance;
        this.currentOutStanding = currentOutStanding;
        this.surchangePayable = surchangePayable;
        this.surchangePaid = surchangePaid;
        this.surchangeWavied = surchangeWavied;
        this.surchangeBalance = surchangeBalance;
        this.netOutStanding = netOutStanding;
    }

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
}
