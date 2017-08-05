package com.example.ravinder077.friendsapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import static com.example.ravinder077.friendsapp.Upload_Photo.IMAGE_CAPTURE;

/**
 * Created by Chugh on 8/4/2017.
 */

public class PostData extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpost);

        ImageView btnclick=(ImageView) findViewById(R.id.postimg);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(PostData.this, "Hello ", Toast.LENGTH_SHORT).show();

                TextView username=(TextView) findViewById(R.id.username);
                EditText status=(EditText) findViewById(R.id.mindstatus);

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String datetime=dateFormat.format(date);

                OtpGen otpgen=new OtpGen();

             //  System.err.println("query" + "http://omtii.com/mile/postwalldata.php?profilename="+username.getText()+"&status="+status.getText()+"&datetime="+datetime);
            String url="http://omtii.com/mile/postwalldata.php?profilename="+username.getText()+"&status="+status.getText()+"&datetime="+datetime;
                try
                {
                    String url2=parseUrl(url);
                    otpgen.execute(url2);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }






            }
        });

        TextView photo=(TextView) findViewById(R.id.textView);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),PostPhoto.class);
                startActivity(i);
            }
        });



    }
    public static String parseUrl(String surl) throws Exception
    {
        URL u = new URL(surl);
        return new URI(u.getProtocol(), u.getAuthority(), u.getPath(), u.getQuery(), u.getRef()).toString();
    }
}
