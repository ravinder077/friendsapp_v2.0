package com.example.ravinder077.friendsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WallFragment extends Fragment {
    private List<WallData> postlist = new ArrayList<>();
    private RecyclerView MyRecyclerView;
    private PostRecAdapter adapter;
    private LinearLayoutManager mLayoutManager;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wall_recycle, container, false);

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this.getActivity());

        MyRecyclerView.setLayoutManager(mLayoutManager);
        preparePostData();
        adapter = new PostRecAdapter(postlist);
        MyRecyclerView.setAdapter(adapter);

        return view;
    }
    private void preparePostData() {
        WallData post = new WallData(R.drawable.dp,"Rohit","Coming Soon",R.drawable.great_wall_of_china,"10","25","30");
        postlist.add(post);

        post = new WallData(R.drawable.invite,"Rohit","Coming Soon",R.drawable.cardimg2,"10","20","30");
        postlist.add(post);

        post = new WallData(R.drawable.dp,"Rohit","Coming Soon",R.drawable.dp,"85","90","80");
        postlist.add(post);

        post = new WallData(R.drawable.invite,"Rohit","Coming Soon",R.drawable.great_wall_of_china,"90","70","70");
        postlist.add(post);

        post = new WallData(R.drawable.invite,"Rohit","Coming Soon",R.drawable.great_wall_of_china,"15","27","34");
        postlist.add(post);




      //  adapter.notifyDataSetChanged();
    }
}

