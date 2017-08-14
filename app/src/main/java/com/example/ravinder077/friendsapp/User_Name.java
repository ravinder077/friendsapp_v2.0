package com.example.ravinder077.friendsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Chugh on 7/1/2017.
 */

public class User_Name extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_READ_WRITE_EXTERNAL_STORAGE;
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.user_name);

        Intent intent=getIntent();
        String mobno=intent.getStringExtra("mobno");
        System.err.println("mobile" +  mobno);

        // create table for contacts start


        ContactInsert contactInsert=new ContactInsert();
        contactInsert.execute(mobno);


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            }


            else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_READ_WRITE_EXTERNAL_STORAGE);


            }
        }

        // Prompt for  external storage Permission end


        final EditText etname=(EditText) findViewById(R.id.etname);
        final Button btnncont = (Button) findViewById(R.id.btnncont);
        btnncont.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                if(etname.getText().toString().length()==0)
                {
                    etname.setError("Name is Required");
                }
                else
                 {
                     Intent intent=getIntent();
                     String mobno=intent.getStringExtra("mobno");
                     String uname=etname.getText().toString();
                    Intent i = new Intent(v.getContext(), Upload_Photo.class);
                     i.putExtra("uname",uname);
                     i.putExtra("mobno",mobno);
                    startActivity(i);
                }
            }
        });
    }


    public class ContactInsert extends AsyncTask<String,String,String>
    {


        @Override
        protected String doInBackground(String... params) {

            String mobno=params[0];

            SQLiteDatabase mydata=openOrCreateDatabase("DM",MODE_PRIVATE,null);

            mydata.execSQL("create table if not exists contact(pid varchar,cname varchar,cnumber varchar,cphoto varchar);");


            // create table for contacts end

            // Access Contact start
            System.err.println("before loop before");
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            System.err.println("before loop");
            ArrayList<UserContact> list = new ArrayList<UserContact>();
            while (phones.moveToNext()) {

                // System.err.println("in loop");
                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

           System.err.println("name " + name);
           // System.err.println("number" + phoneNumber);*/

               // replaceAll("[-+.^:,]","")

                //rajan modify for error special char start
                name=name.replaceAll("[-+.^:,']","");
                phoneNumber=phoneNumber.replaceAll("[-+.^:,']","");
                mydata.execSQL("insert or replace into contact values ('"+mobno+"','"+name.replaceAll("\\s","")+"','"+phoneNumber.replaceAll("\\s","")+"','null')");
                //rajan adding for error special char ends
          /*  OtpGen otpg=new OtpGen();
            String numurl="http://omtii.com/mile/contact_app.php?pid="+mobno+"&cname="+name.replaceAll("\\s","")+"&mobile="+phoneNumber.replaceAll("\\s","");

            System.err.println("numurl"+numurl);

            otpg.execute(numurl);*/


                UserContact userContact=new UserContact();

                userContact.setPid(mobno);
                userContact.setName(name);
                userContact.setMobilel(phoneNumber);
                list.add(userContact);

            }
            mydata.close();
            phones.close();


            String request = new Gson().toJson(list);

            System.err.println("printing json"+request);

           // String JsonResponse = null;
          //  String JsonDATA = "mystring="+json;
           // System.err.println("printing json2"+JsonDATA);

            HttpURLConnection urlConnection = null;

            URL url=null;
            HttpURLConnection client=null;
            String json=null;
            try {
                url = new URL("http://omtii.com/mile/updatecontact.php");
                client = (HttpURLConnection) url.openConnection();
                client.setDoOutput(true);
                client.setDoInput(true);
                client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                client.setRequestMethod("POST");
                //client.setFixedLengthStreamingMode(request.toString().getBytes("UTF-8").length);
                client.connect();



                OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
                String output = request.toString();
                writer.write(output);
                writer.flush();
                writer.close();

                InputStream input = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                System.out.println(result);



            } catch (Exception e){
                e.printStackTrace();

            } finally {
                client.disconnect();
            }


            //System.err.println(JsonResponse);
            return json;

        }
    }

}
