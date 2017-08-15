package com.example.ravinder077.friendsapp;


import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

/**
 * Created by Chugh on 8/4/2017.
 */

public class PostData extends AppCompatActivity {


    Uri viduri;
   private static final int VIDEO_CAPTURE = 101;
private ImageView viewImage;
private Bitmap  myBitmap;
    private  String userid;
    private int MY_PERMISSIONS_REQUEST_LOCATION;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
            String mno = resultSet.getString(1);
            System.err.println("Name" + uname);
            username.setText(uname);
            userid=mno;
            Bitmap ppic= BitmapFactory.decodeFile(resultSet.getString(2));
            profilepic.setImageBitmap(ppic);


            System.err.println("Image Path"+resultSet.getString(2));
        }

        ImageView btnclick=(ImageView) findViewById(R.id.postimg);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EditText status=(EditText) findViewById(R.id.mindstatus);





              PostUpload postUpload=new PostUpload(userid,status.getText().toString(),getApplicationContext());
                postUpload.execute(myBitmap);


                Toast.makeText(PostData.this, "Post Submitted ", Toast.LENGTH_SHORT).show();
              /*  Intent intent=new Intent(getApplicationContext(),WallFragment.class);
                startActivity(intent);
*/
                WallFragment fragment =  new WallFragment();
                if (fragment != null) {
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                }
            }
        });

        //ClickListener on Checkin Layout Starts

        LinearLayout postcheckin = (LinearLayout) findViewById(R.id.checkin);
        postcheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create class object
               MyGpsTracker gps ;

                // Prompt for Location Permission start

                if (ContextCompat.checkSelfPermission(PostData.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    System.err.println("Grant Permission outer if");

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(PostData.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                        System.err.println("Grant Permission inner if");
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    }


                    else {

                        // No explanation needed, we can request the permission.
                        System.err.println("Grant Permission Else part");
                        ActivityCompat.requestPermissions(PostData.this,
                                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);


                    }
                }
                // Prompt for Location Permission end

                // create class object

                gps = new MyGpsTracker();

                // check if GPS enabled

            }
        });

        //ClickListener on Checkin Layout Ends


        //ClickListener on tagfriend Layout Starts
        LinearLayout tagfriend = (LinearLayout) findViewById(R.id.tagfriend);
        tagfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostData.this, "Under Working in PostData.java", Toast.LENGTH_SHORT).show();
            }
        });
        //ClickListener on Tagfriend Layout Ends

        //ClickListener on SharetoGroup Layout Starts
        LinearLayout sharetogroup = (LinearLayout) findViewById(R.id.sharetogroup);
        sharetogroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostData.this, "Under Working in PostData.java", Toast.LENGTH_SHORT).show();
            }
        });
        //ClickListener on SharetoGroup Layout Ends


        //ClickListener on Video Layout Starts

        LinearLayout postvideo=(LinearLayout) findViewById(R.id.postvideo);
        postvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VideoView postvid=(VideoView) findViewById(R.id.postvid);
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, VIDEO_CAPTURE);

                File mediaFile =
                        new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                + "/myvideo.mp4");

                Intent intent2 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                Uri videoUri = Uri.fromFile(mediaFile);
                viduri=videoUri;
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                startActivityForResult(intent2, VIDEO_CAPTURE);


            }

        });
        //ClickListener on Video Layout Ends


        //ClickListener on Photo Layout Starts

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

        //ClickListener on Photo Layout Ends


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
                    myBitmap=bitmap;
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
                myBitmap=thumbnail;
                viewImage.setImageBitmap(thumbnail);
              /*  FileUpload fup=new FileUpload(getApplicationContext());
                fup.execute(thumbnail);*/
            }

        }
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                VideoView postvid=(VideoView) findViewById(R.id.postvid);
                postvid.setVideoURI(viduri);
                postvid.start();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}

