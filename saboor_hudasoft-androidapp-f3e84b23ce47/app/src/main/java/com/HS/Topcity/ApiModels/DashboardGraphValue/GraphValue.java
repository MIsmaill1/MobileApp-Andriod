package com.HS.Topcity.ApiModels.DashboardGraphValue;

import com.google.gson.annotations.SerializedName;

public class GraphValue {

    @SerializedName("Title")
    public String title;
    @SerializedName("Count")
    public int count;
    @SerializedName("ColorCode")
    public String ColorCode;

    public String getColorCode() {
        return ColorCode;
    }

    public void setColorCode(String colorCode) {
        ColorCode = colorCode;
    }

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
}
