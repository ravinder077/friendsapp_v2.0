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

        String name=intent.getStringExtra("name");
       /* Bitmap cover= BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("cover"),0,getIntent().getByteArrayExtra("cover").length);

        Bitmap profile= BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("profile"),0,getIntent().getByteArrayExtra("profile").length);
*/
       String cover = intent.getStringExtra("cover");
        String profile = intent.getStringExtra("profile");
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
            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pagecatagory.this, "Personal", Toast.LENGTH_SHORT).show();
            }
        });
        education.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Pagecatagory.this, "Education", Toast.LENGTH_SHORT).show();
             }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pagecatagory.this, "Entertainment", Toast.LENGTH_SHORT).show();
            }
        });
        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pagecatagory.this, "Brand", Toast.LENGTH_SHORT).show();
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pagecatagory.this, "Other", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
