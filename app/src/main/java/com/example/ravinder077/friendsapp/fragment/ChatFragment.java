package com.example.ravinder077.friendsapp.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ravinder077.friendsapp.controller.EndlessRecyclerViewScrollListener;
import com.example.ravinder077.friendsapp.activity.GroupCreate;
import com.example.ravinder077.friendsapp.activity.NewChatFriend;
import com.example.ravinder077.friendsapp.activity.ProfilePage;
import com.example.ravinder077.friendsapp.R;
import com.example.ravinder077.friendsapp.adpter.ChatAdapter;
import com.example.ravinder077.friendsapp.controller.ApplicationController;
import com.example.ravinder077.friendsapp.model.PageData;
import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.wangjie.androidbucket.utils.ABThreadUtil.TAG;

public class ChatFragment extends Fragment implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener, NavigationView.OnNavigationItemSelectedListener{
    private List<PageData> postlist = new ArrayList<PageData>();
    private RecyclerView MyRecyclerView;
    private ChatAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    String mno = null;
    String jsonphoto = null;

    String mobileno=null;

    String dataoffset=null;
    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;

    //rajan add floating action button starts
    private RapidFloatingActionHelper rfabHelper;

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
        item.getLabel();

        int positionIndex = 6 - position;
        //Toast.makeText(getActivity(),  item.getLabel(), Toast.LENGTH_SHORT).show();
        if(item.getLabel().toString().equals("New Page")) {
            Intent i = new Intent(getContext(), ProfilePage.class);
            startActivity(i);
        }

        else if(item.getLabel().toString().equals("New Group")) {
            Intent intent = new Intent(getContext(), GroupCreate.class);
            startActivity(intent);
        }

        else if(item.getLabel().toString().equals("New Chat")) {
            Intent intent = new Intent(getContext(), NewChatFriend.class);
            startActivity(intent);
        }

    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();

        // Toast.makeText(getActivity(), item.getLabel().toString(), Toast.LENGTH_SHORT).show();
        int positionIndex = 6 - position;

        if(item.getLabel().toString().equals("New Page")) {
            Intent i = new Intent(getContext(), ProfilePage.class);
            startActivity(i);
        }
        else if(item.getLabel().toString().equals("New Group")) {
            Intent intent = new Intent(getContext(), GroupCreate.class);
            startActivity(intent);
        }
        else if(item.getLabel().toString().equals("New Chat")) {
            Intent intent = new Intent(getContext(), NewChatFriend.class);
            startActivity(intent);
        }

        // Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabase mydata=getActivity().openOrCreateDatabase("DM",MODE_PRIVATE,null);
        Cursor resultSet = mydata.rawQuery("Select * from new_user", null);
        if(resultSet.getCount()>0) {
            resultSet.moveToFirst();
            //String uname = resultSet.getString(0);
            mobileno=   mno = resultSet.getString(1);
            dataoffset="0";
        }

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerchat, container, false);


        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(getContext());

        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                .setLabel("New Chat")
                .setResId(R.drawable.chat)
                .setIconNormalColor(0xFFffb022)
                .setIconPressedColor(0xFFffb022)
                .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("New Group")
                .setResId(R.drawable.group)
                .setIconNormalColor(0xFF0ee5ff)
                .setIconPressedColor(0xFF0ee5ff)
                .setWrapper(1)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("New Page")
                .setResId(R.drawable.page)
                .setIconNormalColor(0xFF55F207)
                .setIconPressedColor(0xFF3CE60E)
                .setWrapper(2)
        );
        rfaContent
                .setItems(items)
                .setIconShadowRadius(ABTextUtil.dip2px(view.getContext(), 5))
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(ABTextUtil.dip2px(view.getContext(), 5));
        rfabHelper = new RapidFloatingActionHelper(
                view.getContext(),
                (RapidFloatingActionLayout) view.findViewById(R.id.activity_main_rfal),
                (RapidFloatingActionButton) view.findViewById(R.id.activity_main_rfab),
                rfaContent
        ).build();

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.chatpagegrouprecycle);

        mLayoutManager = new LinearLayoutManager(this.getActivity());

        MyRecyclerView.setLayoutManager(mLayoutManager);
        // preparePostData();


        adapter = new ChatAdapter(postlist);




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

        // MyRecyclerView.setAdapter(adapter);




        fetchStores();
        return view;
    }



    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        Toast.makeText(getActivity(), "new data loading", Toast.LENGTH_SHORT).show();

        final Integer count =Integer.parseInt(dataoffset)+10;
        dataoffset=count.toString();


        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://omtii.com/mile/fetchpagegroupchat.php?mobno="+mobileno+"&dataoffset="+count.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "more data query : " + "http://omtii.com/mile/fetchpagegroupchat.php?mobno="+mobileno+"&dataoffset="+count.toString());

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


    public ArrayList<PageData> jsonToMap(String jsonStr) throws JSONException {


        HashMap<String, String> contact = new HashMap<>();

        ArrayList<PageData> al = new ArrayList<PageData>();

        if (jsonStr != null) {

            System.err.print("josn strin is");

            System.err.print(jsonStr);

            System.err.print("josn strin ends");


            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("tasks");

                // looping through All Contacts
                JSONArray c = jsonObj.getJSONArray("tasks");
                for (int i = 0; i < c.length(); i++) {
                    JSONObject obj = c.getJSONObject(i);
                    String pageid = obj.getString("pageid");
                    String pagename=obj.getString("pagename");
                    String pagedesc = obj.getString("pagedesc");
                    String pagecoverimg = obj.getString("pagecoverimg");
                    String pageprofileimg = obj.getString("pageprofileimg");
                    String pagecatagory = obj.getString("pagecatagory");
                    String pageadmin = obj.getString("pageadmin");
                    String pagedatetime11 = obj.getString("pagedatetime");

                    PageData cc = new PageData();

                      cc.setPageid(pageid);
                      cc.setPagename(pagename);
                      cc.setPagedesc(pagedesc);
                      cc.setPagecoverimg(pagecoverimg);
                      cc.setPageprofileimg(pageprofileimg);
                      cc.setPagecategroies(pagecatagory);
                      cc.setPagecreatedBy(pageadmin);
                      cc.setPagecoverimg(pagedatetime11);

                }


            } catch (Exception e) {
                e.printStackTrace();


            }
        }

        return al;
    }

    public void initializeList(ArrayList<PageData> al) {
        postlist.clear();

        for (PageData cd : al) {


            PageData item = new PageData();



            item.setPageid(cd.getPageid());
            item.setPagename(cd.getPagename());
            item.setPagedesc(cd.getPagedesc());
            item.setPagecoverimg(cd.getPagecoverimg());
            item.setPageprofileimg(cd.getPageprofileimg());
            item.setPagecategroies(cd.getPagecategroies());
            item.setPagecreatedBy(cd.getPagecreatedBy());
            item.setPagecreatedTime(cd.getPagecreatedTime());

            //item.setPostVideo(cd.getPostVideo());

            postlist.add(item);




        }


        System.err.println("data added in list "+postlist);


    }







   /* private void fetchStores() {


        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, API.GET_STORES, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Fetch Stores: " + response);
                showStores(response);
                shopsRecyclerView.setAdapter(storeAdapter);
                storeAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Fetch Stores Error: " + error.getMessage());
            }
        });
        ApplicationController.getInstance().addToRequestQueue(fetchAllStores);
    }*/


    private void fetchStores() {
        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://omtii.com/mile/fetchpagegroupchat.php?mobno="+mobileno+"&dataoffset="+dataoffset, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "Data query chat : " + "http://omtii.com/mile/fetchpagegroupchat.php?mobno="+mobileno+"&dataoffset="+dataoffset);

                Log.d(TAG, "Fetch Stores chat: " + response);
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
                String pageid = obj.getString("pageid");
                String pagename=obj.getString("pagename");
                String pagedesc = obj.getString("pagedesc");
                String pagecoverimg = obj.getString("pagecoverimg");
                String pageprofileimg = obj.getString("pageprofileimg");
                String pagecatagory = obj.getString("pagecatagory");
                String pageadmin = obj.getString("pageadmin");
                String pagedatetime11 = obj.getString("pagedatetime");

                PageData cc = new PageData();

                cc.setPageid(pageid);
                cc.setPagename(pagename);
                cc.setPagedesc(pagedesc);
                cc.setPagecoverimg(pagecoverimg);
                cc.setPageprofileimg(pageprofileimg);
                cc.setPagecategroies(pagecatagory);
                cc.setPagecreatedBy(pageadmin);
                cc.setPagecoverimg(pagedatetime11);
                postlist.add(cc);
            }
        } catch (JSONException e) {
            Log.d(TAG, "Show Stores: " + e.getMessage());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

