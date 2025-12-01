package com.HS.Topcity.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.HS.Topcity.Fragsment.Contact.ContactUsFrag;
import com.HS.Topcity.Fragsment.Contact.FeedbackFrag;

public class ContactTabAdapter extends FragmentPagerAdapter {
    int tabcount;
    public ContactTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super( fm, behavior );
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                return new ContactUsFrag();
            case 1:
                return new FeedbackFrag();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

}
