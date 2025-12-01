package com.HS.Topcity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.HS.Topcity.Models.NewsAnnouncementModel;

import java.util.ArrayList;

public class MyCustomPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<NewsAnnouncementModel> newsAndAnnouncementModels;
    LayoutInflater layoutInflater;


    public MyCustomPagerAdapter(Context context,  ArrayList<NewsAnnouncementModel> newsAndAnnouncementModels) {
        this.context = context;
        this.newsAndAnnouncementModels = newsAndAnnouncementModels;
    }

    @Override
    public int getCount() {
        if(newsAndAnnouncementModels.size() >5)
        {
            return 5;
        }
        else {
            return newsAndAnnouncementModels.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
      //  View itemView = layoutInflater.inflate( R.layout.item, container, false);

        return null;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
