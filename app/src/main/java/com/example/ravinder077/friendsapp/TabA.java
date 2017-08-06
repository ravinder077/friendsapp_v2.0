package com.example.ravinder077.friendsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by ravinder077 on 29-06-2017.
 */

public class TabA extends Fragment implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener, NavigationView.OnNavigationItemSelectedListener {

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

       // Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rfab, container, false);


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


        return view;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }



//rajan add floating action button ends










}
