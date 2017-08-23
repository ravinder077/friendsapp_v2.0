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

        MyRecyclerView = (RecyclerView) findViewById(R.id.recycle);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        MyRecyclerView.setLayoutManager(mLayoutManager);


        WallCardClickData wallCardClickData=new WallCardClickData();
        wallCardClickData.setCommentername("ajay");
        wallCardClickData.setComment("awsome");
        postlist.add(wallCardClickData);

        WallCardClickData wallCardClickData1=new WallCardClickData();
        wallCardClickData1.setCommentername("rahul");
        wallCardClickData1.setComment("great work");
        postlist.add(wallCardClickData1);

        WallCardClickData wallCardClickData2=new WallCardClickData();
        wallCardClickData2.setCommentername("kavi");
        wallCardClickData2.setComment("good");
        postlist.add(wallCardClickData2);

        System.err.println("calling adapter");
        adapter = new WallCardClickAdapter(postlist);
        MyRecyclerView.setAdapter(adapter);


    }

}
