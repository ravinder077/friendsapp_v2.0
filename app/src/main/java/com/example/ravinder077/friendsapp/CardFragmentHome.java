package com.example.ravinder077.friendsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by ravinder077 on 16-07-2017.
 */

public class CardFragmentHome extends Fragment {

    RecyclerView MyRecyclerView;
    ArrayList<CardDataPost> listitems = new ArrayList<CardDataPost>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //new changess

        Toast.makeText(getContext(), "CardFragmentHome", Toast.LENGTH_SHORT).show();

        ArrayList<CardDataPost> al = new ArrayList<CardDataPost>();

       // CardDataPost cdg=new CardDataPost();

        OtpGen otpgen =new OtpGen();
        otpgen.execute("http://omtii.com/mile/text.php");
        try {
            System.err.println("Photo cc" + otpgen.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String jsonphoto=null;
        try {
            jsonphoto= otpgen.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        try {
            ArrayList<CardDataPost> photono=  jsonToMap(jsonphoto);
            initializeList(photono);
        } catch (JSONException e) {
            e.printStackTrace();
        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);





        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardViewHome);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new CardFragmentHome.MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }




    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<CardDataPost> list;

        public MyAdapter(ArrayList<CardDataPost> Data) {
            list = Data;
        }

        @Override
        public CardFragmentHome.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items_fbbvidimg, parent, false);
            CardFragmentHome.MyViewHolder holder = new CardFragmentHome.MyViewHolder(view);

            return holder;
        }



        @Override

        public void onBindViewHolder(final CardFragmentHome.MyViewHolder holder, int position) {





          //   holder.titleTextView.setText(list.get(position).getProfilePostName());
             holder.profileimg.setImageBitmap(list.get(position).getBitmapProfileImg());
             holder.profileName.setText(list.get(position).getProfilePostName());
             holder.postDateTime.setText(list.get(position).getPostTime());
             holder.postDesc.setText(list.get(position).getPostdesc());

            GetBitmapProfilefromUrlThread  getBitmapProfilefromUrlThread=new GetBitmapProfilefromUrlThread();

              getBitmapProfilefromUrlThread.execute(list.get(position).getPostImg());
            try {
                holder.postImage.setImageBitmap(getBitmapProfilefromUrlThread.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            holder.postVideo.setVisibility(View.INVISIBLE);

            //holder.titleTextView.setText(list.get(position).getName());

            // holder.coverImageView.setImageResource(R.drawable.great_wall_of_china);
            //  holder.coverImageView.setImageResource(R.drawable.user2);

            //   holder.coverImageView.setTag(list.get(position).getImageResourceId());

            // nf.setText(list.get(position).getName().substring(0,1));
            /*if (list.get(position).getBimg() == null) {
                holder.ImgTextView.setText(list.get(position).getName().substring(0, 1));

            } else {
                holder.ImgTextView.setVisibility(View.INVISIBLE);
                holder.coverImageView.setImageBitmap(list.get(position).getBimg());
            }*/


           // File imgFile = new  File(list.get(position).getImage());

           /* System.err.println("list.get(position).getImage()"+list.get(position).getImage());
            if(imgFile.exists()){

                System.err.println("i m if");
                System.err.println("imgFile.getAbsolutePath()"+imgFile.getAbsolutePath());
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                // ImageView myImage = (ImageView) findViewById(R.id.imageviewTest);

                //  myImage.setImageBitmap(myBitmap);
                holder.coverImageView.setVisibility(View.VISIBLE);
                holder.ImgTextView.setVisibility(View.INVISIBLE);
                holder.coverImageView.setImageBitmap(myBitmap);*/
/*
            }
            else
            {
                System.err.println("i m else");
                holder.ImgTextView.setVisibility(View.VISIBLE);
                holder.coverImageView.setVisibility(View.INVISIBLE);
                holder.ImgTextView.setText(list.get(position).getName().substring(0, 1));
            }*/

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {



        public ImageView profileimg;
        public TextView  profileName;
        public TextView  postDateTime;
        public TextView  postDesc;
        public ImageView postImage;
        public VideoView postVideo;



        public MyViewHolder(View v) {
            super(v);

            //setting up post profile image
            profileimg=(ImageView)v.findViewById(R.id.img);

            //mapping up the post orofilename
            profileName = (TextView) v.findViewById(R.id.tv1);


            //setting post imagage
            postDateTime = (TextView) v.findViewById(R.id.tv2);


            //setting post video

            postDesc=(TextView)v.findViewById(R.id.tv3) ;



            postImage=(ImageView) v.findViewById(R.id.imageView1);

            postVideo=(VideoView) v.findViewById(R.id.videoView1);
       /*     coverImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getContext(), titleTextView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
*/




        }



    }




    public ArrayList<CardDataPost> jsonToMap(String jsonStr) throws JSONException {


        HashMap<String, String> contact = new HashMap<>();

        ArrayList<CardDataPost> al=new ArrayList<CardDataPost>();

        if (jsonStr != null) {

            System.err.print("josn strin is");

            System.err.print(jsonStr);

            System.err.print("josn strin ends");



            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("tasks");

                // looping through All Contacts
                JSONArray c = jsonObj.getJSONArray("tasks");
                for (int i = 0 ; i < c.length(); i++) {
                    JSONObject obj = c.getJSONObject(i);
                    String id = obj.getString("id");

                    String name = obj.getString("name");
                    String profileimg = obj.getString("profileimg");
                    String datetime = obj.getString("datetime");
                    String desc = obj.getString("desc");
                    String img = obj.getString("img");
                    String video = obj.getString("video");
                    String like = obj.getString("like");
                    String share = obj.getString("share");
                    String comment = obj.getString("comment");


                    CardDataPost cc=new CardDataPost();

                    cc.setPostId(id);
                    cc.setProfilePostImg(profileimg);
                    cc.setProfilePostName(name);
                    cc.setPostTime(datetime);
                    cc.setPostdesc(desc);
                    cc.setPostImg(img);
                    cc.setPostVideo(video);
                    cc.setPostLike(like);
                    cc.setPostShare(share);
                    cc.setPostComment(comment);

                  //  GetBitmapfromUrlThread  getBitmapfromUrlThread=new GetBitmapfromUrlThread();
                   // getBitmapfromUrlThread.execute(img);
                   // Bitmap bb=getBitmapfromUrlThread.get();


                    cc.setBitmapImg(null);





                   // GetBitmapProfilefromUrlThread  getBitmapProfilefromUrlThread=new GetBitmapProfilefromUrlThread();

                   // getBitmapProfilefromUrlThread.execute(img);
                   // Bitmap ccc=getBitmapProfilefromUrlThread.get();


                    cc.setBitmapProfileImg(null);

                    al.add(cc);



                }





            } catch (Exception e) {
                e.printStackTrace();


            }
        }

        return  al;
    }

    public void initializeList( ArrayList<CardDataPost> al) {
        listitems.clear();

        for(CardDataPost cd:al)
        {


            CardDataPost item = new CardDataPost();


            item.setPostId(cd.getPostId());

            item.setBitmapProfileImg(cd.getBitmapProfileImg());
            item.setPostLike(cd.getPostLike());
            item.setPostShare(cd.getPostShare());
            item.setPostComment(cd.getPostComment());
            item.setBitmapImg(cd.getBitmapImg());
            item.setPostImg(cd.getPostImg());
            item.setPostTime(cd.getPostTime());
            item.setProfilePostName(cd.getProfilePostName());
            item.setPostdesc(cd.getPostdesc());
            item.setPostVideo(cd.getPostVideo());

            listitems.add(item);

        }




    }



}