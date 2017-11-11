package com.tuespotsolutions.ravinder077.friendsapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tuespotsolutions.ravinder077.friendsapp.R;
import com.tuespotsolutions.ravinder077.friendsapp.activity.GroupCreate;
import com.tuespotsolutions.ravinder077.friendsapp.activity.NewChatFriend;
import com.tuespotsolutions.ravinder077.friendsapp.activity.ProfilePage;
import com.tuespotsolutions.ravinder077.friendsapp.thread.FormDataSubmit;
import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

/**
 * Created by SandahSaab on 8/17/2017.
 */

public class TabA extends Fragment implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener, NavigationView.OnNavigationItemSelectedListener
{

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rfab, container, false);
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

       final RingProgressBar  mRingProgressBar = (RingProgressBar) view.findViewById(R.id.progress_bar_1);
        Button  button=(Button)view.findViewById(R.id.btn1);

        // Set the progress bar's progress
        mRingProgressBar.setProgress(0);
        mRingProgressBar.setRingProgressColor(2);
       final ProgressBar progressBar=(ProgressBar)view.findViewById(R.id.dd);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
            }
        });

        mRingProgressBar.setOnProgressListener(new RingProgressBar.OnProgressListener()
        {

            @Override
            public void progressToComplete()
            {
                // Progress reaches the maximum callback default Max value is 100
                Toast.makeText(getActivity(), "complete", Toast.LENGTH_SHORT).show();
            }
        });

        String urlfile="savedata.php";
        Map<String,String> map=new HashMap<String,String>();
        map.put("first","1111");
        map.put("second","2222");
        map.put("third","3333");
        FormDataSubmit ff=new FormDataSubmit();
         ff.formSubmit(urlfile,map);
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}


//rajan add floating action button ends

