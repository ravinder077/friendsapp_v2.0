package com.tuespotsolutions.ravinder077.friendsapp.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tuespotsolutions.ravinder077.friendsapp.R;
import com.tuespotsolutions.ravinder077.friendsapp.model.PostData;

import static com.tuespotsolutions.ravinder077.friendsapp.activity.Upload_Photo.IMAGE_CAPTURE;


/**
 * Created by Chugh on 8/4/2017.
 */

public class PostPhoto extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 0;
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.postphoto);


        // When User click on Take photo button
        // this code shows camera of Mobile
        // Take photo Start
        Button btntimg = (Button) findViewById(R.id.btntimg);

        btntimg.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    intent.putExtra("android.intent.extras.CAMERA_FACING", 1);

                    startActivityForResult(intent, IMAGE_CAPTURE);


                } catch (ActivityNotFoundException anfe) {
                    //display an error message
                    String errorMessage = "Whoops - your device doesn't support capturing images!";
                    Toast.makeText(PostPhoto.this, errorMessage, Toast.LENGTH_SHORT).show();
                }

            }


        });       // Take photo end

      // This code is used for taking the photo from gallary
        // Take photo from gallary start
        Button btncimg = (Button) findViewById(R.id.btncimg);
        btncimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent i = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    System.err.println("Image Data" + i.getDataString());
                }
                catch(Exception exp)
                {
                    String errorMessage = "Whoops - your device doesn't support Gallery Feature!";
                    Toast toast = Toast.makeText(PostPhoto.this, errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }); // Take photo from gallary end


        // Return to Post Screen start

        Button btnret=(Button) findViewById(R.id.btnback);
        btnret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iback=new Intent(PostPhoto.this,PostData.class);
                startActivity(iback);
            }
        });

        // Return to post screen end


    }



    //After taking photo from camera get the result back
    // photo Result start
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {


        if (resultCode == RESULT_OK) {

            Bitmap b;

            if (requestCode == IMAGE_CAPTURE || requestCode == RESULT_LOAD_IMAGE) {

                try {
                    if (data.getData() == null) {

                        b = (Bitmap) data.getExtras().get("data");
                    } else {

                        b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    }

                    ImageView picView = (ImageView) findViewById(R.id.imguser);
                    picView.setImageBitmap(b);
                    super.onActivityResult(requestCode, resultCode, data);

              /*   //  call to "saveToInternalStorage" function
                 String saveimg = saveToInternalStorage(b);

                 FileUpload fup = new FileUpload();
                    fup.execute(saveimg);
                   System.err.println("imageName1" + saveimg);


                    String fupget = fup.get();
                System.err.println("fupget" + fupget);

                   Intent intent = getIntent();

                    String mobno = intent.getStringExtra("mobno");
                    String uname = intent.getStringExtra("uname");


                    SQLiteDatabase mydata = openOrCreateDatabase("DM", MODE_PRIVATE, null);

                    mydata.execSQL("create table if not exists new_user(name varchar,mobile varchar,photo varchar);");

                    mydata.execSQL("insert into new_user values ('" + uname + "','" + mobno + "','" + b + "')");


                    OtpGen otpg = new OtpGen();


                    String numurl = "http://omtii.com/mile/savedata.php?uname=" + uname + "&umob=" + mobno + "&uimg=" + fupget;

                    System.err.println("save img" + saveimg);

                    otpg.execute(numurl);

                    System.err.println("numurl" + numurl);


                    // Printing start

                    Cursor resultSet = mydata.rawQuery("Select * from new_user", null);
                    resultSet.moveToFirst();

                    String username = resultSet.getString(0);
                    String mobileno = resultSet.getString(1);
                    String photo = resultSet.getString(2);

                    System.err.println("user name " + username);
                    System.err.println("mobileno" + mobileno);
                    System.err.println("photo" + photo);*/

                    //printing end
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }


        }
    }    // photo result end



}
