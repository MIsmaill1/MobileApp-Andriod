package com.HS.Topcity.Adapters;

import static com.HS.Topcity.Activity.Development.check_video;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.HS.Topcity.Fragsment.Development.ImagesFrag;
import com.HS.Topcity.Fragsment.Development.VideoFrag;

public class DevelopmentTabAdapter extends FragmentPagerAdapter {
    int tabcount;
    public DevelopmentTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super( fm, behavior );
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                check_video = false;
             //   item_list.setVisibility( View.GONE );
                return new ImagesFrag();
            case 1:
                check_video = true;
                return new VideoFrag();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

}

