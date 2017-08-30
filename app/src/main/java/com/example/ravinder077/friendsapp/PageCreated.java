package com.example.ravinder077.friendsapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by SandahSaab on 8/30/2017.
 */

public class PageCreated extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagecreated);

        Intent intent=getIntent();

        final String name=intent.getStringExtra("pagename");
        final String pageimg=intent.getStringExtra("pageimg");
        final String catagory=intent.getStringExtra("catagory");


        ImageView imageView = (ImageView) findViewById(R.id.pagedp);
        TextView textView = (TextView) findViewById(R.id.pagename);

        imageView.setImageURI(Uri.parse(pageimg));
        textView.setText(name);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title);
        setSupportActionBar(toolbar);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menupage, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }


}



