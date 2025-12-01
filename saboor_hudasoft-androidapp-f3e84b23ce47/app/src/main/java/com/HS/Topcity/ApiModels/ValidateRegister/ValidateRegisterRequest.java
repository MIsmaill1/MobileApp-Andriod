package com.HS.Topcity.ApiModels.ValidateRegister;

import com.google.gson.annotations.SerializedName;

public class ValidateRegisterRequest {

    @SerializedName("CNIC")
    public String CNIC;

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }
}
