package com.HS.Topcity.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsAnnouncementModel implements Parcelable {

    int id;
    String newsName;
    String DateofNews;
    String TimeOfNews;
    String   Descriptions;
    String notes;
    String    Image;


    public NewsAnnouncementModel() {
    }

    public NewsAnnouncementModel(int id, String newsName, String dateofNews, String timeOfNews, String descriptions, String notes, String image) {
        this.id = id;
        this.newsName = newsName;
        DateofNews = dateofNews;
        TimeOfNews = timeOfNews;
        Descriptions = descriptions;
        this.notes = notes;
        Image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getDateofNews() {
        return DateofNews;
    }

    public void setDateofNews(String dateofNews) {
        DateofNews = dateofNews;
    }

    public String getTimeOfNews() {
        return TimeOfNews;
    }

    public void setTimeOfNews(String timeOfNews) {
        TimeOfNews = timeOfNews;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( id );
        dest.writeString( newsName );
        dest.writeString( DateofNews );
        dest.writeString( TimeOfNews );
        dest.writeString( Descriptions );
        dest.writeString( notes );
        dest.writeString( Image );

    }

    public NewsAnnouncementModel(Parcel in) {
        id = in.readInt();
        newsName = in.readString();
        DateofNews = in.readString();
        TimeOfNews = in.readString();
        Descriptions = in.readString();
        notes = in.readString();
        Image = in.readString();


    }

    public static final Parcelable.Creator<NewsAnnouncementModel> CREATOR = new Parcelable.Creator<NewsAnnouncementModel>() {
        public NewsAnnouncementModel createFromParcel(Parcel in) {
            return new NewsAnnouncementModel( in );
        }

        public NewsAnnouncementModel[] newArray(int size) {
            return new NewsAnnouncementModel[size];
        }
    };
}
