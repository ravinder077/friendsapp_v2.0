package com.example.ravinder077.friendsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by ravinder077 on 29-06-2017.
 */

public class TabA extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     final View view= inflater.inflate(R.layout.taba, container, false);

        RelativeLayout createnew=(RelativeLayout) view.findViewById(R.id.showpopup);
        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get a reference to the already created main layout
                RelativeLayout l1 = (RelativeLayout) view.findViewById(R.id.relnew);
System.err.println("Entry in on click");

                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup, null);

                System.err.println("after inflate on click"+popupView);
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
        });


        return  view;
    }
}