package com.example.ravinder077.friendsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SandahSaab on 8/22/2017.
 */

public class WallCardClick extends AppCompatActivity {

    private List<WallCardClickData> postlist = new ArrayList<>();
    private RecyclerView MyRecyclerView;
    private WallCardClickAdapter adapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallcardclick);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallcardclick, container, false);

        MyRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);

        mLayoutManager = new LinearLayoutManager(this);

        MyRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new WallCardClickAdapter(postlist);
        MyRecyclerView.setAdapter(adapter);

        return view;
    }
}
