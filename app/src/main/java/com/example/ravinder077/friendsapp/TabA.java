package com.example.ravinder077.friendsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravinder077 on 29-06-2017.
 */

public class TabA extends Fragment implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener, NavigationView.OnNavigationItemSelectedListener {

    //rajan add floating action button starts
    private RapidFloatingActionHelper rfabHelper;

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
        int positionIndex = 6 - position;
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
        int positionIndex = 6 - position;
    }
    //rajan add floating action button ends

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rfab, container, false);

      /*  RelativeLayout createnew = (RelativeLayout) view.findViewById(R.id.showpopup);
        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get a reference to the already created main layout
                RelativeLayout l1 = (RelativeLayout) view.findViewById(R.id.relnew);
                System.err.println("Entry in on click");

                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup, null);

                System.err.println("after inflate on click" + popupView);
                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


                // show the popup window
                popupWindow.showAtLocation(l1, Gravity.CENTER, 0, 0);


                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });*/

//rajan add floating action button starts
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
                .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("New Page")
                .setResId(R.drawable.page)
                .setIconNormalColor(0xFF55F207)
                .setIconPressedColor(0xFF3CE60E)
                .setWrapper(0)
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
