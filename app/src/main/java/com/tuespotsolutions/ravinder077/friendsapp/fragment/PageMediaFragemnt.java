package com.tuespotsolutions.ravinder077.friendsapp.fragment;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tuespotsolutions.ravinder077.friendsapp.R;
import com.tuespotsolutions.ravinder077.friendsapp.adpter.GallaryAdapter;
import com.tuespotsolutions.ravinder077.friendsapp.adpter.GalleryAdapter;
import com.tuespotsolutions.ravinder077.friendsapp.controller.ApplicationController;
import com.tuespotsolutions.ravinder077.friendsapp.controller.EndlessRecyclerViewScrollListener;
import com.tuespotsolutions.ravinder077.friendsapp.model.GallaryData;
import com.tuespotsolutions.ravinder077.friendsapp.model.PostData;
import com.tuespotsolutions.ravinder077.friendsapp.service.PhotoServiceFriends;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static com.wangjie.androidbucket.utils.ABThreadUtil.TAG;
public class PageMediaFragemnt extends Fragment {


    private List<GallaryData> postlist = new ArrayList<GallaryData>();
    private RecyclerView MyRecyclerView;
    private GallaryAdapter adapter;
    private GridLayoutManager mLayoutManager;
    String dataoffset=null;
    FrameLayout frameLayout=null;
    private ProgressDialog pDialog;
    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        inflater.inflate(R.menu.pagemediamenu,menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        final EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        searchEditText.setHint("Search");


        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                CardFriendFragment cardFriendFragment=new CardFriendFragment();

                cardFriendFragment.myFilter(newText);
                return false;

            }
        });


        // return true;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.d("onOptionsItemSelected","yes");
        switch (item.getItemId()) {
            case R.id.refresh:
                Toast.makeText(getActivity(), "Friends Updated Started", Toast.LENGTH_SHORT).show();
                getActivity().startService(new Intent(getContext(), PhotoServiceFriends.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
      final Bundle args = getArguments();


       final String  pageid = args.getString("pageid");
        final String  pagename = args.getString("pagename");


        System.err.println("Fragement pageid"+pageid);
        System.err.println("Fragement pagename"+pagename);




        View v = inflater.inflate(R.layout.gallaryrecycle, container, false);
        setHasOptionsMenu(true);
        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        MyRecyclerView = (RecyclerView) v.findViewById(R.id.gallaryrec);
        pDialog = new ProgressDialog(getActivity());
        ImageView attachment=(ImageView) v.findViewById(R.id.idattachment);

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PostData.class);
                i.putExtra("pageid",pageid);
                i.putExtra("pagename",pagename);
                startActivity(i);

                Toast.makeText(getActivity(), "i m attachment", Toast.LENGTH_SHORT).show();
            }
        });


                dataoffset="0";
        mLayoutManager = new GridLayoutManager(getActivity(),2);

        MyRecyclerView.setLayoutManager(mLayoutManager);
        // preparePostData();


        adapter = new GallaryAdapter(postlist);

        frameLayout=(FrameLayout)v.findViewById(R.id.gallaryframe);



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


        MyRecyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getContext(), MyRecyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", (Serializable) postlist);
                bundle.putInt("position", position);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));








           return v;
    }


    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        //Toast.makeText(this, "new data loading", Toast.LENGTH_SHORT).show();
        pDialog.setMessage("Please Wait...");
        pDialog.show();
        final Integer count =Integer.parseInt(dataoffset)+10;
        dataoffset=count.toString();


        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://timepasstoday.com/fetchgallary.php?dataoffset="+count.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "more data query gallary : " + "http://timepasstoday.com/fetchgallary.php?dataoffset="+count.toString());
                pDialog.hide();
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
    public ArrayList<GallaryData> jsonToMap(String jsonStr) throws JSONException {


        HashMap<String, String> contact = new HashMap<>();

        ArrayList<GallaryData> al = new ArrayList<GallaryData>();

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

                    String img = obj.getString("img");
                    String date = obj.getString("date");



                    GallaryData cc = new GallaryData();

                    cc.setId(id);
                    cc.setName(name);
                    cc.setImg(img);
                    cc.setDatetime(date);


                    al.add(cc);


                }


            } catch (Exception e) {
                e.printStackTrace();


            }
        }

        return al;
    }
    public void initializeList(ArrayList<GallaryData> al) {
        postlist.clear();

        for (GallaryData cd : al) {


            GallaryData item = new GallaryData();


            item.setId(cd.getId());
            item.setName(cd.getName());
            item.setImg(cd.getImg());
            item.setDatetime(cd.getDatetime());

            //item.setPostVideo(cd.getPostVideo());

            postlist.add(item);
        }


        System.err.println("data added in list "+postlist);


    }
    private void fetchStores() {

        Log.d(TAG, "Data query : " + "http://timepasstoday.com/fetchgallary.php?dataoffset="+dataoffset);
        pDialog.setMessage("Please Wait...");
        pDialog.show();

        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://timepasstoday.com/fetchgallary.php?dataoffset="+dataoffset, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "Data query gallary: " + "http://timepasstoday.com/fetchgallary.php?dataoffset="+dataoffset);
                pDialog.hide();
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
                Log.d(TAG, "Fetch Stores Error fetchStores: " + error.getMessage());
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

                String img = obj.getString("img");
                String date = obj.getString("datetime");


                GallaryData cc = new GallaryData();

                cc.setId(id);
                cc.setName(name);
                cc.setImg(img);
                cc.setDatetime(date);
                postlist.add(cc);
            }
        } catch (JSONException e) {
            Log.d(TAG, "Show Stores: " + e.getMessage());
        }
    }
}

