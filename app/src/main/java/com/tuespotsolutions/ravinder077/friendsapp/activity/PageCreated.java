package com.tuespotsolutions.ravinder077.friendsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tuespotsolutions.ravinder077.friendsapp.R;
import com.tuespotsolutions.ravinder077.friendsapp.adpter.PageCreatedAdapter;
import com.tuespotsolutions.ravinder077.friendsapp.controller.ApplicationController;
import com.tuespotsolutions.ravinder077.friendsapp.controller.EndlessRecyclerViewScrollListener;
import com.tuespotsolutions.ravinder077.friendsapp.model.WallData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.wangjie.androidbucket.utils.ABThreadUtil.TAG;

/**
 * Created by SandahSaab on 8/30/2017.
 */

public class PageCreated extends AppCompatActivity {
    String dataoffset=null;
    private List<WallData> postlist = new ArrayList<>();
    private RecyclerView MyRecyclerView;
    private PageCreatedAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private String mobileno=null;
    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagecreated);
        dataoffset="0";


        MyRecyclerView = (RecyclerView) findViewById(R.id.pagerecycler);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        MyRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new PageCreatedAdapter(postlist);
        MyRecyclerView.setAdapter(adapter);



        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        MyRecyclerView.addOnScrollListener(scrollListener);
        fetchStores();

        final Intent intent=getIntent();

        final String name=intent.getStringExtra("pagename");
        final String pageimg=intent.getStringExtra("pageimg");
        final String catagory=intent.getStringExtra("catagory");
        final String catagoryimg = intent.getStringExtra("catagoryimg");
        final String cover = intent.getStringExtra("cover");


        ImageView imageView = (ImageView) findViewById(R.id.pagedp);
        TextView textView = (TextView) findViewById(R.id.pagename);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.pagetitle);
        Glide.with(imageView.getContext()).load(pageimg)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        textView.setText(name);

        textView.setText(name);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PageDetail.class);
                i.putExtra("pagename",name);
                i.putExtra("pageimg",pageimg);
                i.putExtra("catagory",catagory);
                i.putExtra("cover",cover);
                i.putExtra("catagoryimg",catagoryimg);
                startActivity(i);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title);
        setSupportActionBar(toolbar);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menupage, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        Toast.makeText(this, "new data loading", Toast.LENGTH_SHORT).show();

        final Integer count =Integer.parseInt(dataoffset)+10;
        dataoffset=count.toString();


        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://omtii.com/mile/text.php?mobno="+mobileno+"&dataoffset="+count.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "more data query : " + "http://omtii.com/mile/text.php?mobno="+mobileno+"&dataoffset="+count.toString());

                Log.d(TAG, "Fetch Stores: " + response);
                showStores(response);
                //MyRecyclerView.setAdapter(adapter);
                System.err.println("adpter attached");
                adapter.notifyDataSetChanged();
                System.err.println("data set changed attached");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Fetch Stores Error: " + error.getMessage());
            }
        });
        ApplicationController.getInstance().addToRequestQueue(fetchAllStores);
    }
    private void fetchStores() {
        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://omtii.com/mile/fetchpagedata.php?pageid="+mobileno+"&dataoffset="+dataoffset, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "Data query : " + "http://omtii.com/mile/text.php?mobno="+mobileno+"&dataoffset="+dataoffset);

                Log.d(TAG, "Fetch Stores: " + response);
                showStores(response);
                MyRecyclerView.setAdapter(adapter);
                System.err.println("adpter attached");
                adapter.notifyDataSetChanged();
                System.err.println("data set changed attached");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Fetch Stores Error: " + error.getMessage());
            }
        });
        ApplicationController.getInstance().addToRequestQueue(fetchAllStores);
    }

    private void showStores(JSONObject response) {
        try {
            JSONArray contacts = response.getJSONArray("tasks");

            // looping through All Contacts
            JSONArray c = response.getJSONArray("tasks");
            for (int i = 0; i < c.length(); i++) {
                JSONObject obj = c.getJSONObject(i);
                String id = obj.getString("id");

                String name = obj.getString("name");

                String profileimg = obj.getString("profileimg");
                String datetime = obj.getString("datetime");
                String desc = obj.getString("desc");
                String img = obj.getString("img");
                String video = obj.getString("video");
                String like = obj.getString("like");
                String share = obj.getString("share");
                String comment = obj.getString("comment");


                WallData cc = new WallData();

                cc.setPostid(id);
                cc.setProfilepic(profileimg);
                cc.setProfilename(name);
                cc.setPosttime(datetime);
                cc.setPoststatus(desc);
                cc.setPostpic(img);
                cc.setPostvdo(video);
                cc.setPostlikes(like);
                cc.setPostshare(share);
                cc.setPostcomments(comment);
                postlist.add(cc);
            }
        } catch (JSONException e) {
            Log.d(TAG, "Show Stores: " + e.getMessage());
        }
    }
}



