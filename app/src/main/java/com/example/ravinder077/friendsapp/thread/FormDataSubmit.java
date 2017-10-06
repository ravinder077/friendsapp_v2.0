package com.example.ravinder077.friendsapp.thread;

import android.net.Uri;
import android.os.AsyncTask;
import android.net.Uri.Builder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ravinder077 on 30-09-2017.
 */

  public  class FormDataSubmit
{




    Map<String,String> map;


    public String formSubmit(String key,Map<String,String> map)
    {
        this.map=map;
        FormData formData=new FormData();

        String data=null;
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.omtii.com")
                .appendPath("mile")
                .appendPath(key);

           for(Map.Entry<String,String> ll:map.entrySet())
        {
            builder .appendQueryParameter(ll.getKey(), ll.getValue());
        }

        String myUrl = builder.build().toString();
        System.err.println("myUrl"+myUrl);
        try {
            formData.execute(myUrl);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            data=   formData.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return data;
    }


    class FormData extends  AsyncTask<String,String,String>
    {


        @Override
        protected String doInBackground(String... params) {


            System.err.print("java is "+params[0]);

            return "hello";
        }
    }


}
