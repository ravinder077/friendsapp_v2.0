package com.tuespotsolutions.ravinder077.friendsapp.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tuespotsolutions.ravinder077.friendsapp.R;
import com.tuespotsolutions.ravinder077.friendsapp.fragment.CardFriendFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ravinder077 on 03-08-2017.
 */

public class ProfilePage extends AppCompatActivity

{
    protected static final int IMAGE_CAPTURE = 102;
    private static final int RESULT_LOAD_IMAGE = 0;
    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;
    private int PROFILE_PIC_COUNT = 0;
    private Bitmap coverPhoto=null;
    private Bitmap profilePhoto=null;

     private String pageName;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null


        toolbar.setTitle(R.string.title);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.header_cover_image);

        CircleImageView circleImageView=(CircleImageView)findViewById(R.id.user_profile_photo);

        Button button=(Button)findViewById(R.id.submit_action);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(ProfilePage.this, "contiune video", Toast.LENGTH_SHORT).show();
                SQLiteDatabase mydata = openOrCreateDatabase("DM", MODE_PRIVATE, null);
                Cursor resultSet = mydata.rawQuery("Select * from new_user", null);
                resultSet.moveToFirst();

                String username = resultSet.getString(0);
                String mobileno = resultSet.getString(1);
                String photo = resultSet.getString(2);

                System.err.println("user name " + username);
                System.err.println("mobileno" + mobileno);
                System.err.println("photo" + photo);



                EditText editText1=(EditText)findViewById(R.id.user_profile_name);
                ByteArrayOutputStream bs=new ByteArrayOutputStream();
                profilePhoto.compress(Bitmap.CompressFormat.PNG,100,bs);


                Intent i = new Intent (getApplicationContext(),Pagecatagory.class);
                i.putExtra("name",editText1.getText().toString());
                i.putExtra("profile",saveToInternalStorage(profilePhoto));
                coverPhoto.compress(Bitmap.CompressFormat.PNG,100,bs);
                i.putExtra("cover",saveToInternalStorage(coverPhoto));
                startActivity(i);


                //EditText editText2=(EditText)findViewById(R.id.user_profile_short_bio);
                //EditText editText3=(EditText)findViewById(R.id.btype);
                //EditText editText4=(EditText)findViewById(R.id.bname);
                //EditText editText5=(EditText)findViewById(R.id.burl);






             /*   String name= editText1.getText().toString();
                String desc=editText2.getText().toString();
                String photo1=null;
                String photo2=null;
                String btype=editText3.getText().toString();
                String bname=editText4.getText().toString();
                String burl=editText4.getText().toString();
                String mno=mobileno;
*/
              //  mydata.execSQL("insert into user_pages values ('"+mno+"','"+name+"','"+desc+"','"+photo1+"','"+photo2+"','"+btype+"','"+bname+"','"+burl+"')");


/*
                Cursor resultSet1 = mydata.rawQuery("Select * from user_pages", null);
                resultSet1.moveToFirst();

                System.out.println(resultSet1.getString(0));
                System.out.println(resultSet1.getString(1));
                System.out.println(resultSet1.getString(2));
                System.out.println(resultSet1.getString(3));
                System.out.println(resultSet1.getString(4));
                System.out.println(resultSet1.getString(5));

                System.out.println(resultSet1.getString(6));
                System.out.println(resultSet1.getString(7));


                System.out.println("");
                Bundle bundle = new Bundle();
                bundle.putString("edttext", "From Activity");
                  // set Fragmentclass Arguments
                TabA fragobj = new TabA();
                fragobj.setArguments(bundle);*/
            }
        });


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ProfilePage.this, "hello", Toast.LENGTH_SHORT).show();

                final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProfilePage.this);
                builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        if (items[item].equals("Take Photo")) {
                            PROFILE_PIC_COUNT = 1;
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQUEST_CAMERA);

                        } else if (items[item].equals("Choose from Library")) {
                            PROFILE_PIC_COUNT = 1;
                            Intent intent = new Intent(
                                    Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, SELECT_FILE);
                        } else if (items[item].equals("Cancel")) {
                            PROFILE_PIC_COUNT = 0;
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();

            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(ProfilePage.this, "hello", Toast.LENGTH_SHORT).show();

                final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProfilePage.this);
                builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        if (items[item].equals("Take Photo")) {
                            PROFILE_PIC_COUNT = 2;
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQUEST_CAMERA);
                        } else if (items[item].equals("Choose from Library")) {
                            PROFILE_PIC_COUNT = 2;
                            Intent intent = new Intent(
                                    Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, SELECT_FILE);
                        } else if (items[item].equals("Cancel")) {
                            PROFILE_PIC_COUNT = 0;
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();

            }
        });
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);


      //  Toast.makeText(this, "requestCode "+requestCode, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, "resultCode "+resultCode, Toast.LENGTH_SHORT).show();

        System.out.println("requestCode "+requestCode);

        System.out.println("resultCode "+resultCode);




        if (resultCode == RESULT_OK) {


            System.out.println("inside RESULT_OK "+resultCode);
            Bitmap b;

            if(requestCode == IMAGE_CAPTURE || requestCode==RESULT_LOAD_IMAGE || requestCode==2 || requestCode==1 ) {


                System.out.println("inside IMAGE_CAPTURE   RESULT_LOAD_IMAGE "+requestCode);

                try
                {
                    if(data.getData()==null)
                    {

                        System.out.println("if data is not null ");


                        b=(Bitmap) data.getExtras().get("data");
                    }
                    else
                    {

                        System.out.println("if data is null ");
                        b=MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getData());
                    }

                    System.out.println("image"+b);

                    if(PROFILE_PIC_COUNT==2) {
                        ImageView picView = (ImageView) findViewById(R.id.header_cover_image);

                        coverPhoto=b;
                        picView.setImageBitmap(b);





                        //coverPhoto=
                    }
                    else if(PROFILE_PIC_COUNT==1)
                    {
                        profilePhoto=b;
                        CircleImageView circleImageView=(CircleImageView)findViewById(R.id.user_profile_photo);
                        circleImageView.setImageBitmap(b);
                        ProgressBar progressBar=(ProgressBar)findViewById(R.id.header_cover_image_progressbar);

                    }

                    // call to "saveToInternalStorage" function
                   // String saveimg = saveToInternalStorage(b);

                  //  CircleImageView imageButton=(CircleImageView)findViewById(R.id.user_profile_photo);
                   // System.out.println("imageButton"+imageButton);

                   // URL url = new URL(saveimg);

                  //  URI uri=new URI(saveimg);
                   // Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                   // imageButton.setImageBitmap(bmp);

                   // Bitmap bitmap = BitmapFactory.decodeFile(saveimg);
                   // imageButton.setImageBitmap(bitmap);


                    //printing end
                }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                }
            }




        }
    }

   /* private String saveToInternalStorage(Bitmap bitmapImage){
        System.err.println("I m in save Internal storage function");
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        String imgname="upic.jpg";
        File mypath=new File(directory,imgname);

        FileOutputStream fos = null;
        try {
            System.err.println("I m in save Internal storage function try block");
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            System.err.println("I m in save Internal storage function catch block");
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath()+"/"+imgname;
    }
*/


    private String saveToInternalStorage(Bitmap bitmapImage){
        System.err.println("I m in save Internal storage function");
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        String imgname=Math.random()+"upic.jpg";
        File mypath=new File(directory,imgname);

        FileOutputStream fos = null;
        try {
            System.err.println("I m in save Internal storage function try block");
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            System.err.println("I m in save Internal storage function catch block");
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath()+"/"+imgname;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu, menu);
        getMenuInflater().inflate(R.menu.menu, menu);


        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        final EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        searchEditText.setHint("Search");


        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                CardFriendFragment cardFriendFragment=new CardFriendFragment();

                cardFriendFragment.myFilter(newText);
                return false;

            }
        });


        return true;
    }


}
