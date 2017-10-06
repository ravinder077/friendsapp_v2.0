package com.example.ravinder077.friendsapp.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ravinder077.friendsapp.fragment.CardFriendFragment;
import com.example.ravinder077.friendsapp.fragment.ChatFragment;
import com.example.ravinder077.friendsapp.fragment.TabA;
import com.example.ravinder077.friendsapp.fragment.WallFragment;

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