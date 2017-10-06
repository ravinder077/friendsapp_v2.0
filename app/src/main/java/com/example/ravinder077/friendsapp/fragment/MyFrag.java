package com.example.ravinder077.friendsapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ravinder077.friendsapp.R;

/**
 * Created by Chugh on 8/15/2017.
 */

public class MyFrag extends Fragment {



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wall_recycle, container, false);
        return view;
    }

}
