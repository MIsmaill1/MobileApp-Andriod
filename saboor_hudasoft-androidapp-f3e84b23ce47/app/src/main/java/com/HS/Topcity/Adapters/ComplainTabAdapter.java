package com.HS.Topcity.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.HS.Topcity.Fragsment.Complaints.AddNewFrag;
import com.HS.Topcity.Fragsment.Complaints.RecentlyComplaintfrag;

public class ComplainTabAdapter extends FragmentPagerAdapter {
    int tabcount;
    public ComplainTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super( fm, behavior );
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                return new AddNewFrag();
            case 1:
                return new RecentlyComplaintfrag();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }


}
