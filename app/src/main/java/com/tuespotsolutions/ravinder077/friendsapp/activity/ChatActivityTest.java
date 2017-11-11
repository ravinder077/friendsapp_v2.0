package com.tuespotsolutions.ravinder077.friendsapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.system.ErrnoException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.tuespotsolutions.ravinder077.friendsapp.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravinder077 on 02-10-2017.
 */

public class ChatActivityTest extends AppCompatActivity {




    ProgressBar progressBar=null;
    private Uri mCropImageUri;
    String mCurrentPhotoPath;
    File photoFile = null;
    ImageView pics=null;
    ImageView profilepics=null;
    String pathProfile=null;
    String pathHeader=null;
    private String  pathh=null;
    Bitmap bb=null;
    private final static int RESULT_SELECT_IMAGE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String TAG = "GalleryUtil";
    int flag=0;

    Uri imageURI=null;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.profile_page);



        pics =(ImageView)findViewById(R.id.header_cover_image);
        profilepics =(ImageView)findViewById(R.id.user_profile_photo);
       progressBar=(ProgressBar)findViewById(R.id.header_cover_image_progressbar);
        Button submitbtn=(Button)findViewById(R.id.submit_action);




       progressBar.setVisibility(View.INVISIBLE);


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(ChatActivityTest.this, "Header "+pathHeader, Toast.LENGTH_SHORT).show();
              //  Toast.makeText(ChatActivityTest.this, "Profile"+pathProfile, Toast.LENGTH_SHORT).show();
               // Toast.makeText(ChatActivityTest.this, "contiune video", Toast.LENGTH_SHORT).show();




                EditText editText =(EditText)findViewById(R.id.user_profile_name);
                SQLiteDatabase mydata = openOrCreateDatabase("DM", MODE_PRIVATE, null);
                Cursor resultSet = mydata.rawQuery("Select * from new_user", null);
                resultSet.moveToFirst();

                String username = resultSet.getString(0);
                String mobileno = resultSet.getString(1);
                String photo = resultSet.getString(2);

                System.err.println("user name " + username);
                System.err.println("mobileno" + mobileno);
                System.err.println("photo" + photo);


               // Toast.makeText(ChatActivityTest.this, "name is "+editText.getText().toString(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent (getApplicationContext(),Pagecatagory.class);
                i.putExtra("name",editText.getText().toString());
                i.putExtra("profile",pathProfile);
                i.putExtra("cover",pathHeader);



                System.err.println("name    "+editText.getText().toString());
                System.err.println("profile "+pathProfile);
                System.err.println("cover   "+pathHeader);
                startActivity(i);

              // FormDataSubmit formDataSubmit=new FormDataSubmit();

              //  String urlFile="savedataformpage.php";
              //  Map<String,String> map=new HashMap<String, String>();
               // map.put()

              //  formDataSubmit.formSubmit()
            }
        });



        pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




               // AsyncTask task = new ProgressDialogAsyncTask(ChatActivityTest.this).execute();
                //System.err.println(" Intent Clicked");
                flag=0;
                flag=200;
                startActivityForResult(getPickImageChooserIntent(), 200);

                //Toast.makeText(ChatActivityTest.this, "hello header "+pathh, Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.VISIBLE);
            }
        });

        profilepics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               // AsyncTask task = new ProgressDialogAsyncTask(ChatActivityTest.this).execute();
                //System.err.println(" Intent Clicked");
                flag=0;
                flag=210;
                startActivityForResult(getPickImageChooserIntent(), 210);

             //   Toast.makeText(ChatActivityTest.this, "hello profile "+pathh, Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.VISIBLE);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.err.println(" Now in Activity Result");
        if (resultCode == Activity.RESULT_OK) {

            System.err.println(" First If"+requestCode);

            Uri imageUri = getPickImageResultUri(data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage,
            // but we don't know if we need to for the URI so the simplest is to try open the stream and see if we get error.
            boolean requirePermissions = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    isUriRequiresPermissions(imageUri)) {
                System.err.println(" Storage Permission denied");

                // request permissions and handle the result in onRequestPermissionsResult()
                requirePermissions = true;
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }

            if (!requirePermissions) {
                System.err.println("ImageUri "+imageUri);



                 if(flag==200)
                 {
                     pics.setImageURI(imageUri);
                 }
                 else if(flag==210)
                 {
                     profilepics.setImageURI(imageUri);
                 }

                //myuri=getPath(imageUri);

                ProgressDialogAsyncTask postUpload=new ProgressDialogAsyncTask(ChatActivityTest.this);
                postUpload.execute(getPath(imageUri));
                String path=null;
               /* try {
                  //  path=  postUpload.get();
                    pathh=path;
                    System.err.println("path is"+path);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pics.setImageURI(mCropImageUri);
        } else {
            Toast.makeText(this, "Required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Create a chooser intent to select the source to get image from.<br/>
     * The source can be camera's (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br/>
     * All possible sources are added to the intent chooser.
     */
    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    /**
     * Get URI to image received from capture by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }

    /**
     * Get the URI of the selected image from {@link #getPickImageChooserIntent()}.<br/>
     * Will return the correct URI for camera and gallery image.
     *
     * @param data the returned data of the activity result
     */
    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    /**
     * Test if we can open the given Android URI to test if permission required error is thrown.<br>
     */
    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            ContentResolver resolver = getContentResolver();
            InputStream stream = resolver.openInputStream(uri);
            stream.close();
            return false;
        } catch (FileNotFoundException e) {
            if (e.getCause() instanceof ErrnoException) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public  class ProgressDialogAsyncTask extends AsyncTask<String, String, String> {
        private ProgressDialog dialog;
        private ChatActivityTest activity;
        public ProgressDialogAsyncTask(ChatActivityTest activity) {
            this.activity=activity;
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            // URL url=null;
            //String sourceFileUri = params[0];
            String st=null;
            int serverResponseCode=0;
            //String saveimg = saveToInternalStorage( params[0]);

            System.err.println("saveimg"+params[0]);


            String sourceFileUri=params[0].toString();

            String upLoadServerUri = "http://timepasstoday.com/upload.php";
            String fileName = sourceFileUri;

            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            File sourceFile = new File(sourceFileUri);
            if (!sourceFile.isFile()) {
                System.err.println("cannot not read source ");

            }
            try { // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);
                conn = (HttpURLConnection) url.openConnection(); // Open a HTTP  connection to  the URL
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/FormDataSubmit-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                //Content-Type:multipart/form-data; boundary=----WebKitFormBoundarydyqBJ1KtZqyUf74P
                conn.setRequestProperty("uploadedimage", fileName);
                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploadedimage\";filename=\""+ fileName + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                // Content-Disposition: form-data; name="uploaded_file"; filename="21430101_1980829775530106_412887049839991815_n.jpg"
                //Content-Type: image/jpeg


                bytesAvailable = fileInputStream.available(); // create a buffer of  maximum size

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into FormDataSubmit...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                // send multipart FormDataSubmit data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();
                //System.out.println();


                String json_response = "";
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String text = "";
                while ((text = br.readLine()) != null) {
                    json_response += text;
                    st=json_response;
                }

                System.err.println("uploadFile HTTP Response is :"+json_response);


                System.err.println("uploadFile HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
                //st=serverResponseMessage;
                if(serverResponseCode == 200){

                    System.err.println("serverResponseCode "+serverResponseCode);

                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

                ex.printStackTrace();

            } catch (Exception e) {

                e.printStackTrace();

            }


            if(flag==200)
            {
                pathHeader=st;
            }
            else if(flag==210)
            {
                pathProfile=st;
            }

            return st;
        }

        protected void onPostExecute(String result) {
            // do UI work here

            pathh=result;

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }



    private String getPath(Uri uri) {
        String[]  data = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(this, uri, data, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
