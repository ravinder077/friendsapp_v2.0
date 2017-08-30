package com.example.ravinder077.friendsapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SandahSaab on 8/11/2017.
 */

public class PageDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagedescribe);

        Intent intent=getIntent();

        final String name=intent.getStringExtra("name");
        final String cover=intent.getStringExtra("cover");
        final String profile=intent.getStringExtra("profile");
        final String catagory=intent.getStringExtra("catagory");
        final String catagoryimg=intent.getStringExtra("catagoryimg");

        System.err.println("catagoryimg"+catagoryimg);

        de.hdodenhof.circleimageview.CircleImageView pageimg = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.user_profile_photo);
        TextView pagename = (TextView) findViewById(R.id.pagename);
        de.hdodenhof.circleimageview.CircleImageView catagoryimgset = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.catagoryimg);
        TextView catagoryset = (TextView) findViewById(R.id.catagory);
        EditText desc = (EditText) findViewById(R.id.description);
        Button finishbtn = (Button) findViewById(R.id.finishbtn);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainlayout);

        pageimg.setImageURI(Uri.parse(profile));
        pagename.setText(name);
        //linearLayout.setBackground(Drawable.createFromPath(cover));
        catagoryset.setText(catagory);
        catagoryimgset.setImageResource(Integer.parseInt(catagoryimg));

        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getApplicationContext(),PageCreated.class);
                i.putExtra("pagename",name);
                i.putExtra("pageimg",profile);
                i.putExtra("catagory",catagory);
                startActivity(i);
            }
        });

    }
}
