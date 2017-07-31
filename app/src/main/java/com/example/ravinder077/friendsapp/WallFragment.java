package com.example.ravinder077.friendsapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SandahSaab on 7/31/2017.
 */

public class WallFragment extends android.support.v4.app.Fragment {
    RecyclerView MyRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wallcard, container, false);



        return view;
    }
    }

