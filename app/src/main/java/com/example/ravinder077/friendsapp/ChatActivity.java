package com.example.ravinder077.friendsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Chugh on 8/16/2017.
 */

public class ChatActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onCreate(Bundle b) {
        super.onCreate(b);


        setContentView(R.layout.chatactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarchat);
        toolbar.setTitle("DM");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView frdname=(TextView) findViewById(R.id.name);
        CircleImageView frdpic=(CircleImageView) findViewById(R.id.dp);

        Intent intent=getIntent();
        String fname=intent.getStringExtra("fname");
        String fpic=intent.getStringExtra("fpic");

        frdname.setText(fname);
        frdpic.setImageURI(Uri.parse(fpic));
    }
}
