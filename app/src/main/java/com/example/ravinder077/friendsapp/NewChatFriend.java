package com.example.ravinder077.friendsapp;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

/**
 * Created by SandahSaab on 8/12/2017.
 */

public class NewChatFriend extends AppCompatActivity {



    TextView nf;
    String name;
    RecyclerView MyRecyclerView;
    static NewChatFriend.MyAdapter adapter;
    ArrayList<FriendData> listitems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newchatfriend);


        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null


        //toolbar.setTitle(R.string.title);
        //setSupportActionBar(toolbar);

        Toast.makeText(this, "CardGroupFragment", Toast.LENGTH_SHORT).show();

        ArrayList<FriendData> al = new ArrayList<>();


        Cursor phones = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        SQLiteDatabase mydata = this.openOrCreateDatabase("DM", MODE_PRIVATE, null);




        Cursor resultSet = mydata.rawQuery("Select * from contact group by cnumber order by cname", null);
        resultSet.moveToFirst();
        while (resultSet.moveToNext()) {
            FriendData fd = new FriendData();
            name = resultSet.getString(1);
            String imgUrl=resultSet.getString(3);

            String mobile = resultSet.getString(2);
            String cphoto=resultSet.getString(3);
            System.err.println("cphoto" + cphoto);

            fd.setImage(cphoto);
            fd.setName(name);
        fd.setContact(mobile.replaceAll("\\s",""));

            fd.setImage(imgUrl);


            System.err.println("Imgurl" + imgUrl);
            //  fd.setContact(mobile);
            //  fd.setImg(R.drawable.great_wall_of_china);

            al.add(fd);


        }
        mydata.close();
        phones.close();
        initializeList(al);



        MyRecyclerView = (RecyclerView) findViewById(R.id.addnewchat);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(this);
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter= new NewChatFriend.MyAdapter(listitems);

        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(adapter);
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);




    }



    public void myFilter(String d)
    {
        adapter.getFilter().filter(d);

        //new CardFriendFragment.MyAdapter(listitems).getFilter().filter(d);
    }







    public class MyAdapter extends RecyclerView.Adapter<NewChatFriend.MyViewHolder> implements Filterable {
        private ArrayList<FriendData> list;
        private ArrayList<FriendData> list2;

        public MyAdapter(ArrayList<FriendData> Data) {
            list = Data;
            list2=Data;
        }

        @Override
        public NewChatFriend.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items_friend, parent, false);
            NewChatFriend.MyViewHolder holder = new NewChatFriend.MyViewHolder(view);
            //  nf =(TextView) view.findViewById(R.id.txtnfirst);

            return holder;
        }

        @Override
        public void onBindViewHolder(final NewChatFriend.MyViewHolder holder, int position) {

            holder.titleTextView.setText(list.get(position).getName());

            // holder.coverImageView.setImageResource(R.drawable.great_wall_of_china);
            //  holder.coverImageView.setImageResource(R.drawable.user2);

            //   holder.coverImageView.setTag(list.get(position).getImageResourceId());

            // nf.setText(list.get(position).getName().substring(0,1));
            //  if (list.get(position).getImage() == null) {
             /*  holder.ImgTextView.setText(list.get(position).getName().substring(0, 1));

           // } else {
               // holder.ImgTextView.setVisibility(View.INVISIBLE);
                //holder.coverImageView.setImageBitmap(list.get(position).getBimg());
                   File file=new File(list.get(position).getImage());

                System.err.println("list.get(position).getImage()"+list.get(position).getImage());
                  //holder.coverImageView.setImageBitmap(list.get(position).getBimg());



                              System.err.println("file.getAbsolutePath()"+file.getAbsolutePath());
                              Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                              holder.coverImageView.setImageBitmap(bitmap);







           // }*/





            File imgFile = new  File(list.get(position).getImage());

            System.err.println("list.get(position).getImage()"+list.get(position).getImage());
            if(imgFile.exists())
            {

                System.err.println("i m if");
                Toast.makeText(NewChatFriend.this, "Friend", Toast.LENGTH_SHORT).show();
                System.err.println("imgFile.getAbsolutePath()"+imgFile.getAbsolutePath());
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                // ImageView myImage = (ImageView) findViewById(R.id.imageviewTest);

                //  myImage.setImageBitmap(myBitmap);
                holder.coverImageView.setVisibility(View.VISIBLE);
                holder.ImgTextView.setVisibility(View.INVISIBLE);
                holder.coverImageView.setImageBitmap(myBitmap);
                holder.chatIcon.setImageResource(R.drawable.chat1);

            }
           /* else
            {
                System.err.println("i m not friends part");
                holder.ImgTextView.setVisibility(View.VISIBLE);
                holder.coverImageView.setVisibility(View.INVISIBLE);
                holder.ImgTextView.setText(list.get(position).getName().substring(0, 1));
                holder.chatIcon.setImageResource(0);


            }
*/
        }




        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public Filter getFilter() {
            return NewChatContactFilter.newInstance(list2,this);
        }
        public void setSpacecrafts(ArrayList<FriendData> fd)
        {
            this.list=fd;
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;
        public TextView ImgTextView;
        public  ImageView chatIcon;
        public LinearLayout cardViewGroup;
        public android.support.v7.widget.CardView view2;

        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.grouptxt);
            coverImageView = (ImageView) v.findViewById(R.id.groupimg);
            ImgTextView=(TextView) v.findViewById(R.id.txtnfirst);
            chatIcon=(ImageView) v.findViewById(R.id.chaticon1);
            cardViewGroup = (LinearLayout)v.findViewById(R.id.cardViewGroup);
            view2 = (android.support.v7.widget.CardView) v.findViewById(R.id.view2);

            chatIcon.setImageResource(0);

            coverImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(NewChatFriend.this, titleTextView.getText(), Toast.LENGTH_SHORT).show();
                }
            });

        /*    MyRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener (getApplicationContext(), MyRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {




                }

                @Override
                public void onItemLongClick(View view, int position) {
                    // ...
                }
            }));*/




        }
    }


    public void initializeList( ArrayList<FriendData> al) {
        listitems.clear();

        for(FriendData cd:al)
        {
                if(!cd.getImage().equals("null")) {
                    FriendData item = new FriendData();

                    item.setId(cd.getId());
                    item.setImg(cd.getImg());
                    item.setBimg(cd.getBimg());
                    item.setImage(cd.getImage());
                    Toast.makeText(this, cd.getImage(), Toast.LENGTH_SHORT).show();
                    System.err.println("cd.getImage()" + cd.getImage());
                    // item.setImgg(cd.getImgg());
                    item.setName(cd.getName());
                    listitems.add(item);

                }

        }




    }


    public class ConnDBPhoto extends AsyncTask<ArrayList<FriendData>,String,ArrayList<FriendData>>

    {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(NewChatFriend.this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected ArrayList<FriendData> doInBackground(ArrayList<FriendData>... params) {

            ArrayList<FriendData> ls=new ArrayList<FriendData>();
            ArrayList<FriendData> ls1=params[0];
            String st=null;







            for(FriendData cd:ls1)
            {
                String id=  cd.getId();
                String name=    cd.getName();
                String no=cd.getContact();
                Bitmap imgg=null;
                String img=cd.getImage();
                //  String img=    cd.getImg();

                //  Bitmap imgg=  cd.getImgg();
           /*String path="http://omtii.com/mile/selectnum.php?id="+no;
                      try {
                              URL url = new URL(path);
                               System.err.println("url" + url);
                             HttpURLConnection con = (HttpURLConnection) url.openConnection();
                          con.setRequestProperty("User-Agent", "");
                          con.setRequestMethod("GET");

                          con.connect();

                          InputStream in = con.getInputStream();
                          BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                          StringBuilder result = new StringBuilder();
                          String line;
                          while ((line = reader.readLine()) != null) {
                              result.append(line);
                          }

                          st = result.toString();
                      }
                      catch(Exception ee)
                      {
                          ee.printStackTrace();

                      }
                     imgg=  getBitmapfromUrl(st);*/


                FriendData cc=new FriendData();
                cc.setId(id);
                cc.setName(name);
                cc.setBimg(imgg);

                cc.setImage(img);

                //  cc.setImg(img);
                //  cc.setImgg(imgg);

                ls.add(cc);

            }



            return ls;

        }


        protected  Bitmap getBitmapfromUrl(String imageUrl)
        {
            try
            {
                URL url = new URL(imageUrl);

                System.out.println("loading img  "+imageUrl);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                return bitmap;

            } catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;

            }
        }

        protected void onPostExecute(ArrayList<FriendData> result) {


            // super.onPostExecute(result);
            //done(result);
            // Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show();
            // progressDialog.hide();


        }



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

                NewChatFriend cardFriendFragment=new NewChatFriend();

                cardFriendFragment.myFilter(newText);
                return false;

            }
        });


        return true;
    }



         /*   searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //run query to the server
                    String searchtext=searchEditText.getText().toString();
                    Log.e("onClick: ", "-- " + searchtext);
                    Toast.makeText(MainActivity.this, searchtext, Toast.LENGTH_SHORT).show();

                    SQLiteDatabase mydata = openOrCreateDatabase("DM", MODE_PRIVATE, null);


                    Cursor resultSet2 = mydata.rawQuery("Select * from contact where cname like '"+searchtext+"%'",null);
                    resultSet2.moveToFirst();
                    while(resultSet2.moveToNext()) {

                        String cname = resultSet2.getString(1);
                        Log.e("contact number  : ", "-- " + cname);

                    }
                }
                return false;
            }
        });*/




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }
}

