package com.HS.Topcity.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ComplainSubTypesModels  implements Parcelable {


    public int complaintSubTypeId;
    public String complaintSubTypeName;

    public ComplainSubTypesModels() {
    }

    public int getComplaintSubTypeId() {
        return complaintSubTypeId;
    }

    public void setComplaintSubTypeId(int complaintSubTypeId) {
        this.complaintSubTypeId = complaintSubTypeId;
    }

    public String getComplaintSubTypeName() {
        return complaintSubTypeName;
    }

    public void setComplaintSubTypeName(String complaintSubTypeName) {
        this.complaintSubTypeName = complaintSubTypeName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( complaintSubTypeId );
        dest.writeString( complaintSubTypeName );
    }

    public ComplainSubTypesModels(Parcel in) {
        complaintSubTypeId = in.readInt();
        complaintSubTypeName = in.readString();
    }

    public static final Parcelable.Creator<ComplainSubTypesModels> CREATOR = new Parcelable.Creator<ComplainSubTypesModels>() {
        public ComplainSubTypesModels createFromParcel(Parcel in) {
            return new ComplainSubTypesModels( in );
        }

        public ComplainSubTypesModels[] newArray(int size) {
            return new ComplainSubTypesModels[size];
        }
    };
}
