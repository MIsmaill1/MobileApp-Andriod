package com.HS.Topcity.Models;

public class FAQsModel {

    int question;
    int Ans;
    Boolean check = false;

    public FAQsModel() {
    }

    public FAQsModel(int question, int ans) {
        this.question = question;
        Ans = ans;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public void setAns(int ans) {
        Ans = ans;
    }

    public int getQuestion() {
        return question;
    }

    public int getAns() {
        return Ans;
    }
}
