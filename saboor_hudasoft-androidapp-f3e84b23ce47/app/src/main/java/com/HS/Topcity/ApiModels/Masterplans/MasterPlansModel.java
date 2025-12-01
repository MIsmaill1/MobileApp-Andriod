package com.HS.Topcity.ApiModels.Masterplans;

import com.google.gson.annotations.SerializedName;

public class MasterPlansModel {

    @SerializedName("Image")
    public String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
