package com.tuespotsolutions.ravinder077.friendsapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tuespotsolutions.ravinder077.friendsapp.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

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
        final EditText desc = (EditText) findViewById(R.id.description);
        Button finishbtn = (Button) findViewById(R.id.finishbtn);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainlayout);

       // pageimg.setImageURI(Uri.parse(profile));

        Glide.with(pageimg.getContext()).load(profile)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(pageimg);
        pagename.setText(name);
        //linearLayout.setBackground(Drawable.createFromPath(cover));
        catagoryset.setText(catagory);
        catagoryimgset.setImageResource(Integer.parseInt(catagoryimg));

        final  EditText info =(EditText)findViewById(R.id.description);

        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getApplicationContext(),PageMainActivity.class);
                i.putExtra("pagename",name);
                i.putExtra("pageimg",profile);
                i.putExtra("catagory",catagory);
                i.putExtra("cover",cover);
                i.putExtra("catagoryimg",catagoryimg);

                System.err.println("name "+name);
                System.err.println("pageimg "+profile);
                System.err.println("." +
                        " "+catagory);
                System.err.println("cover "+cover);
                System.err.println("catagoryimg "+catagoryimg);

                System.err.println("info "+info.getText());
                String mnum=null;

                SQLiteDatabase mydata=openOrCreateDatabase("DM",MODE_PRIVATE,null);
                mydata.execSQL("create table if not exists new_user(name varchar,mobile varchar,photo blob);");
                Cursor resultSet = mydata.rawQuery("Select * from new_user", null);
                if(resultSet.getCount()>0) {
                    resultSet.moveToFirst();

                     mnum = resultSet.getString(1);
                }

                MyTask nn=new MyTask();
                nn.execute(mnum,name,profile,catagory,catagoryimg,cover,info.getText().toString());

                startActivity(i);
            }
        });

    }


    class MyTask extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(PageDescription.this);
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String abc=null;
            String mytype="page";
            System.err.println("Pgae starts");
            System.err.println("mnum"+params[0]);
            System.err.println("name"+params[1]);
            System.err.println("profile"+params[2]);
            System.err.println("catagory"+params[3]);
            System.err.println("catagoryimg"+params[4]);
            System.err.println("cover images"+params[5]);
            System.err.println("info"+params[6]);
            System.err.println("Pgae ends");
          String coverimg =params[5];
            String profileimg =params[2];

            System.err.println("coverimg ends"+coverimg);
            System.err.println("profileimg ends"+profileimg);




           // String responseServer=postdata();

         //   System.err.println(responseServer);

           String urls="http://omtii.com/mile/savedatafrom.php?name="+params[1]+"&desc="+params[6]+"&coverimg="+coverimg+"&profileimg="+profileimg+"&catagory="+params[3]+"&admin="+params[0]+"&mytype="+mytype;


            Log.d("ravinder url", urls);

            String numurl= null;
            try {
                numurl = parseUrl(urls);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("ravinder url", numurl);
            abc=postdata(numurl);



            return abc;
        }

        public  String parseUrl(String surl) throws Exception
        {
            URL u = new URL(surl);
            return new URI(u.getProtocol(), u.getAuthority(), u.getPath(), u.getQuery(), u.getRef()).toString();
        }
        private String postdata(String urls)
        {


            URL url;

            String st = null;
            try
            {

            url=new URL(urls);
            System.err.println("url"+url);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent","");
            con.setRequestMethod("GET");

            con.connect();

            InputStream in= con.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            StringBuilder result=new StringBuilder();
            String line;
            while((line=reader.readLine())!=null)
            {
                result.append(line);
            }

            st=result.toString();

        }

        catch (Exception e) {
            e.printStackTrace();
        }
         return st;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd != null)
            {
                pd.dismiss();
            }
        }
    }
}
