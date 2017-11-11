package com.tuespotsolutions.ravinder077.friendsapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuespotsolutions.ravinder077.friendsapp.R;

/**
 * Created by SandahSaab on 8/31/2017.
 */

public class PageDetail extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.pagedetail);

        Intent intent=getIntent();
        final String name = intent.getStringExtra("pagename");
        final String pageimg = intent.getStringExtra("pageimg");
        final String catagory = intent.getStringExtra("catagory");
        final String cover = intent.getStringExtra("cover");
        final String catagoryimg = intent.getStringExtra("catagoryimg");

        System.err.println("catagoryimg "+catagoryimg );

        ImageView coverimg = (ImageView)findViewById(R.id.pagecover);
        de.hdodenhof.circleimageview.CircleImageView pagepic = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.pagepic);
        TextView pagename = (TextView) findViewById(R.id.pagename);
        ImageView catagorypic = (ImageView) findViewById(R.id.catagoryimg1);
        TextView catagoryname = (TextView)findViewById(R.id.catagoryname);

        coverimg.setImageURI(Uri.parse(cover));
        pagepic.setImageURI(Uri.parse(pageimg));
        pagename.setText(name);
        catagoryname.setText(catagory);
        catagorypic.setImageURI(Uri.parse(catagoryimg));

    }

}
