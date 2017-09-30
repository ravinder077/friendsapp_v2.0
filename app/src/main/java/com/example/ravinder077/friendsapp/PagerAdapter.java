package com.example.ravinder077.friendsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                WallFragment tab22 = new WallFragment();
                return tab22;

            case 1:
                ChatFragment tab2 = new ChatFragment();
                return tab2;
            case 2:
                CardFriendFragment cff=new CardFriendFragment();
                return cff;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}