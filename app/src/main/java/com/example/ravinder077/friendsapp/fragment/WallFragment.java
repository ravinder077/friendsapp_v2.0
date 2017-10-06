package com.example.ravinder077.friendsapp.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ravinder077.friendsapp.R;
import com.example.ravinder077.friendsapp.adpter.PostRecAdapter;
import com.example.ravinder077.friendsapp.controller.ApplicationController;
import com.example.ravinder077.friendsapp.controller.EndlessRecyclerViewScrollListener;
import com.example.ravinder077.friendsapp.model.PostData;
import com.example.ravinder077.friendsapp.model.WallData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.wangjie.androidbucket.utils.ABThreadUtil.TAG;

public class WallFragment extends Fragment {
    private List<WallData> postlist = new ArrayList<WallData>();
    private RecyclerView MyRecyclerView;
    private PostRecAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    String mno = null;
    String jsonphoto = null;

    String mobileno=null;

    String dataoffset=null;
    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;

    com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton postbtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //new changess

       /* Toast.makeText(getContext(),"CardFragmentHome", Toast.LENGTH_SHORT).show();

        ArrayList<WallData> al = new ArrayList<WallData>();

        // WallData cdg=new WallData(); */

        SQLiteDatabase mydata=getActivity().openOrCreateDatabase("DM",MODE_PRIVATE,null);
        Cursor resultSet = mydata.rawQuery("Select * from new_user", null);
        if(resultSet.getCount()>0) {
            resultSet.moveToFirst();
            //String uname = resultSet.getString(0);
            mobileno=   mno = resultSet.getString(1);
            dataoffset="0";
        }


        /*OtpGen otpgen =new OtpGen();
        otpgen.execute("http://omtii.com/mile/text.php?mobno="+mno);
        try {
            System.err.println("Photo cc" + otpgen.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        try {
            jsonphoto= otpgen.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        try {
            ArrayList<WallData> photono=  jsonToMap(jsonphoto);
            initializeList(photono);
        } catch (JSONException e) {
            e.printStackTrace();
        }


*/


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wall_recycle, container, false);

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this.getActivity());

        MyRecyclerView.setLayoutManager(mLayoutManager);
        // preparePostData();


        adapter = new PostRecAdapter(postlist);




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



/*
        MyRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                *//*Toast.makeText(getContext(), "Scrolling", Toast.LENGTH_SHORT).show();
             OtpGen otpgen =new OtpGen();
               otpgen.execute("http://omtii.com/mile/nextrows.php?mobno="+mno);

               try {
                   System.err.println("Photo cc" + otpgen.get());
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (ExecutionException e) {
                   e.printStackTrace();
               }


               try {
                   jsonphoto= otpgen.get();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (ExecutionException e) {
                   e.printStackTrace();
               }


               try {
                   ArrayList<WallData> photono=  jsonToMap(jsonphoto);
                   initializeList(photono);

               } catch (JSONException e) {
                   e.printStackTrace();
               }
                adapter.notifyDataSetChanged();*//*

            }
        });*/

        postbtn = (com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton) view.findViewById(R.id.activity_main_rfab);

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PostData.class);
                startActivity(i);
            }
        });


        fetchStores();
        return view;
    }

  /*  private void preparePostData() {
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




      // adapter.notifyDataSetChanged();
    }*/




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


    public ArrayList<WallData> jsonToMap(String jsonStr) throws JSONException {


        HashMap<String, String> contact = new HashMap<>();

        ArrayList<WallData> al = new ArrayList<WallData>();

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

                 /*   GetBitmapfromUrlThread  getBitmapfromUrlThread=new GetBitmapfromUrlThread();
                    getBitmapfromUrlThread.execute(img);
                    Bitmap bb=getBitmapfromUrlThread.get();


                    cc.setBitpostpic(bb);





                    GetBitmapProfilefromUrlThread  getBitmapProfilefromUrlThread=new GetBitmapProfilefromUrlThread();

                    getBitmapProfilefromUrlThread.execute(profileimg);
                    Bitmap ccc=getBitmapProfilefromUrlThread.get();


                    cc.setBitprofilepic(ccc);
*/
                    al.add(cc);


                }


            } catch (Exception e) {
                e.printStackTrace();


            }
        }

        return al;
    }

    public void initializeList(ArrayList<WallData> al) {
        postlist.clear();

        for (WallData cd : al) {


            WallData item = new WallData();


            item.setPostid(cd.getPostid());
            item.setBitprofilepic(cd.getBitprofilepic());
            item.setProfilepic(cd.getProfilepic());
            item.setPostlikes(cd.getPostlikes());
            item.setPostshare(cd.getPostshare());
            item.setPostcomments(cd.getPostcomments());
            item.setBitpostpic(cd.getBitpostpic());
            item.setPostpic(cd.getPostpic());
            item.setPosttime(cd.getPosttime());
            item.setProfilename(cd.getProfilename());
            item.setPoststatus(cd.getPoststatus());
            item.setPostvdo(cd.getPostvdo());

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
        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://omtii.com/mile/text.php?mobno="+mobileno+"&dataoffset="+dataoffset, null, new Response.Listener<JSONObject>() {
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

