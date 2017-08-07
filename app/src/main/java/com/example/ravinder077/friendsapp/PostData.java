package com.example.ravinder077.friendsapp;


import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

ImageView viewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpost);
        viewImage=(ImageView) findViewById(R.id.viewImage);
         System.err.println("Hello I m in POst Data");

        final TextView username=(TextView) findViewById(R.id.username);
        de.hdodenhof.circleimageview.CircleImageView profilepic=(de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.ppic);


        SQLiteDatabase mydata=openOrCreateDatabase("DM",MODE_PRIVATE,null);
        Cursor resultSet = mydata.rawQuery("Select * from new_user", null);
        if(resultSet.getCount()>0) {
            resultSet.moveToFirst();
            String uname = resultSet.getString(0);
            System.err.println("Name" + uname);
            username.setText(uname);

            Bitmap ppic= BitmapFactory.decodeFile(resultSet.getString(2));
            profilepic.setImageBitmap(ppic);


            System.err.println("Image Path"+resultSet.getString(2));
        }

        ImageView btnclick=(ImageView) findViewById(R.id.postimg);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(PostData.this, "Hello ", Toast.LENGTH_SHORT).show();


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
        System.out.println("before click listener");
        LinearLayout photo=(LinearLayout) findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               /* Intent i=new Intent(getApplicationContext(),PostPhoto.class);
                startActivity(i);*/
                selectImage();
            }
        });



    }
    public static String parseUrl(String surl) throws Exception
    {
        URL u = new URL(surl);
        return new URI(u.getProtocol(), u.getAuthority(), u.getPath(), u.getQuery(), u.getRef()).toString();
    }

    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(PostData.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                System.err.println("I m in if bitmap");
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    Bitmap  bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    viewImage.setImageBitmap(bitmap);
                    System.err.println("Bitmap" + bitmap);
                   /* FileUpload fup=new FileUpload(getApplicationContext());
                    fup.execute(bitmap);*/
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                           + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                      //  bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                System.err.println("I m in else bitmap");
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

                viewImage.setImageBitmap(thumbnail);
              /*  FileUpload fup=new FileUpload(getApplicationContext());
                fup.execute(thumbnail);*/
            }

        }
    }
}

