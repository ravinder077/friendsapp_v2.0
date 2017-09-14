package com.example.ravinder077.friendsapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SandahSaab on 8/30/2017.
 */

public class PageCreated extends AppCompatActivity {

    private List<PageCreatedData> postlist = new ArrayList<>();
    private RecyclerView MyRecyclerView;
    private PageCreatedAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagecreated);



        MyRecyclerView = (RecyclerView) findViewById(R.id.pagerecycler);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        MyRecyclerView.setLayoutManager(mLayoutManager);


        PageCreatedData pagecreateddata=new PageCreatedData();
        pagecreateddata.setStatus("Hello Dear");
        postlist.add(pagecreateddata);

        PageCreatedData pagecreateddata1=new PageCreatedData();
        pagecreateddata1.setStatus("Just Checking");
        postlist.add(pagecreateddata1);

        PageCreatedData pagecreateddata2=new PageCreatedData();
        pagecreateddata2.setStatus("Test my app");
        postlist.add(pagecreateddata2);

        System.err.println("calling adapter");
        adapter = new PageCreatedAdapter(postlist);
        MyRecyclerView.setAdapter(adapter);





        final Intent intent=getIntent();

        final String name=intent.getStringExtra("pagename");
        final String pageimg=intent.getStringExtra("pageimg");
        final String catagory=intent.getStringExtra("catagory");
        final String catagoryimg = intent.getStringExtra("catagoryimg");
        final String cover = intent.getStringExtra("cover");


        ImageView imageView = (ImageView) findViewById(R.id.pagedp);
        TextView textView = (TextView) findViewById(R.id.pagename);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.pagetitle);

        imageView.setImageURI(Uri.parse(pageimg));
        textView.setText(name);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PageDetail.class);
                i.putExtra("pagename",name);
                i.putExtra("pageimg",pageimg);
                i.putExtra("catagory",catagory);
                i.putExtra("cover",cover);
                i.putExtra("catagoryimg",catagoryimg);
                startActivity(i);
            }
        });

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



