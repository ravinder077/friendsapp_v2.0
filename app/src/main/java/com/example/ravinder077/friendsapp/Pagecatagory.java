package com.example.ravinder077.friendsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by SandahSaab on 8/8/2017.
 */

public class Pagecatagory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.pagecatagory);

        Intent intent=getIntent();

        final String name=intent.getStringExtra("name");
       /* Bitmap cover= BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("cover"),0,getIntent().getByteArrayExtra("cover").length);

        Bitmap profile= BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("profile"),0,getIntent().getByteArrayExtra("profile").length);
*/
       final String cover = intent.getStringExtra("cover");
        final String profile = intent.getStringExtra("profile");
        System.err.println("PageName = "+name+ "\n cover = " +cover+ " \nPageProfile = " +profile);


        LinearLayout personal = (LinearLayout) findViewById(R.id.cata1);
        LinearLayout business = (LinearLayout) findViewById(R.id.cata2);
        LinearLayout education = (LinearLayout) findViewById(R.id.cata3);
        LinearLayout entertainment = (LinearLayout) findViewById(R.id.cata4);
        LinearLayout brand = (LinearLayout) findViewById(R.id.cata5);
        LinearLayout other = (LinearLayout) findViewById(R.id.cata6);

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pagecatagory.this, "Personal", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (getApplicationContext(),PageDescription.class);
                i.putExtra("name",name);
                i.putExtra("cover",cover);
                i.putExtra("profile",profile);
                i.putExtra("catagory","Personal");
                i.putExtra("catagoryimg",R.drawable.pagecatapersonal+"");
                // Toast.makeText(Pagecatagory.this, "CatagoryImage"+r, Toast.LENGTH_SHORT).show();
                System.err.println("CatagoryImage "+R.drawable.pagecatapersonal);

                startActivity(i);
            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pagecatagory.this, "Business", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (getApplicationContext(),PageDescription.class);
                i.putExtra("name",name);
                i.putExtra("cover",cover);
                i.putExtra("profile",profile);
                i.putExtra("catagory","Business");
                i.putExtra("catagoryimg",R.drawable.pagecatabusiness+"");
                startActivity(i);
            }
        });
        education.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Pagecatagory.this, "Education", Toast.LENGTH_SHORT).show();
            Intent i = new Intent (getApplicationContext(),PageDescription.class);
            i.putExtra("name",name);
            i.putExtra("cover",cover);
            i.putExtra("profile",profile);
            i.putExtra("catagory","Education");
            i.putExtra("catagoryimg",R.drawable.pagecataeducation+"");
            startActivity(i);
             }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pagecatagory.this, "Entertainment", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (getApplicationContext(),PageDescription.class);
                i.putExtra("name",name);
                i.putExtra("cover",cover);
                i.putExtra("profile",profile);
                i.putExtra("catagory","Entertainment");
                i.putExtra("catagoryimg",R.drawable.pagecataentertainment+"");
                startActivity(i);
            }
        });
        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pagecatagory.this, "Brand", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (getApplicationContext(),PageDescription.class);
                i.putExtra("name",name);
                i.putExtra("cover",cover);
                i.putExtra("profile",profile);
                i.putExtra("catagory","Brand");
                i.putExtra("catagoryimg",R.drawable.pagecatabrand+"");
                startActivity(i);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pagecatagory.this, "Other", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (getApplicationContext(),PageDescription.class);
                i.putExtra("name",name);
                i.putExtra("cover",cover);
                i.putExtra("profile",profile);
                i.putExtra("catagory","Other");
                i.putExtra("catagoryimg",R.drawable.pagecataother+"");
                startActivity(i);
            }
        });

    }



}
