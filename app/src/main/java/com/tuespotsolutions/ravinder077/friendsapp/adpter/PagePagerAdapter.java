package com.tuespotsolutions.ravinder077.friendsapp.adpter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tuespotsolutions.ravinder077.friendsapp.fragment.PageMediaFragemnt;


public class PagePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private  Bundle fragmentBundle;
    public PagePagerAdapter(FragmentManager fm, int NumOfTabs,Bundle data) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.fragmentBundle = data;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                PageMediaFragemnt f = new PageMediaFragemnt();
                f.setArguments(fragmentBundle);
                return f;

            case 1:
                PageMediaFragemnt tab2 = new PageMediaFragemnt();
                tab2.setArguments(fragmentBundle);
                return tab2;

            case 2:
                PageMediaFragemnt tab3 = new PageMediaFragemnt();
                tab3.setArguments(fragmentBundle);
                return tab3;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}