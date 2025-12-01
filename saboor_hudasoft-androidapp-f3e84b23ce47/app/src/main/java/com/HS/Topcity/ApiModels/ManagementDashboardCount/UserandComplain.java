package com.HS.Topcity.ApiModels.ManagementDashboardCount;

import com.google.gson.annotations.SerializedName;

public class UserandComplain {
    @SerializedName("Title")
    public String title;
    @SerializedName("Count")
    public int count;
    @SerializedName("ColorCode")
    public String colorCode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
