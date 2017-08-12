package com.example.ravinder077.friendsapp;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by Chugh on 7/12/2017.
 */

public class FilterHelper1 extends Filter {

    static ArrayList<FriendData> clist;
    static GroupCreate.MyAdapter adapter;
   

    public static FilterHelper1 newInstance(ArrayList<FriendData> clist, GroupCreate.MyAdapter adapter)
    {
        FilterHelper1.adapter=adapter;
        FilterHelper1.clist=clist;
        return new FilterHelper1();
    }
   



    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults=new FilterResults();
        if(constraint!=null && constraint.length()>0) {
            constraint=constraint.toString().toUpperCase();
            ArrayList<FriendData> foundfilter=new ArrayList<FriendData>();
            FriendData spacecraft;

            for(int i=0;i<clist.size();i++)
            {
                spacecraft=clist.get((i));
                if(spacecraft.getName().toUpperCase().contains(constraint)) {
                    foundfilter.add(spacecraft);

                }
            }
            filterResults.count=foundfilter.size();
            filterResults.values=foundfilter;
        }
        else
        {
            filterResults.count=clist.size();
            filterResults.values=clist;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setSpacecrafts((ArrayList<FriendData>) results.values);
        adapter.notifyDataSetChanged();
    }
}
