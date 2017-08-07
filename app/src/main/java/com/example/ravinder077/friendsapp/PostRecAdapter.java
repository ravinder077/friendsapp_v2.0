package com.example.ravinder077.friendsapp;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URI;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Chugh on 8/3/2017.
 */

public class PostRecAdapter  extends RecyclerView.Adapter<PostRecAdapter.MyViewHolder> {


    private List<WallData> postlist;



    public PostRecAdapter(List<WallData> postlist) {
        this.postlist = postlist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallcard, parent, false);

        return new PostRecAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final WallData wallData = postlist.get(position);


        holder.profilepic.setImageBitmap(wallData.getBitprofilepic());
        holder.profilename.setText(wallData.getProfilename());
        holder.poststatus.setText(wallData.getPoststatus());

        System.err.println("Heeeeeeeeeeeeeeeeeeeellllllllllllloooooooooo"+wallData.getPostvdo());



        System.err.println("image and vodeo starts");

        System.err.println("video "+wallData.getPostvdo());
        System.err.println("image "+wallData.getPostpic());
        System.err.println("image and vodeo ends");


        if(wallData.getBitpostpic()!=null) {

            holder.postvdo.setVisibility(View.INVISIBLE);
            holder.postpic.setImageBitmap(wallData.getBitpostpic());

        }
        else if(wallData.getPostvdo()!=null && wallData.getPostvdo()!="")
        {
            holder.postpic.setVisibility(View.INVISIBLE);
            //Toast.makeText(holder.postvdo.getContext() , "hello", Toast.LENGTH_SHORT).show();

            MediaController mediacontroller = new MediaController(
                    holder.postvdo.getContext());
            mediacontroller.setAnchorView( holder.postvdo);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(wallData.getPostvdo());
            holder.postvdo.setMediaController(mediacontroller);
            holder.postvdo.setVideoURI(video);
        }
        else
        {
            holder.postvdo.setVisibility(View.INVISIBLE);
            holder.postpic.setVisibility(View.INVISIBLE);
        }

        holder.postlikes.setText(wallData.getPostlikes());
        holder.postcomments.setText(wallData.getPostcomments());
        holder.postshare.setText(wallData.getPostshare());
        holder.posttime.setText(wallData.getPosttime());





    }



    @Override
    public int getItemCount() {
        return postlist.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

       public CircleImageView profilepic;
        public TextView profilename;
        public TextView poststatus;
        public ImageView postpic;
        public VideoView postvdo;
        public TextView postlikes;
        public TextView postcomments;
        public TextView postshare;
        public TextView posttime;
        public MyViewHolder(View view) {
            super(view);
            profilepic = (CircleImageView) view.findViewById(R.id.img);
            profilename = (TextView) view.findViewById(R.id.ProfileName);
            poststatus = (TextView) view.findViewById(R.id.status);
            postpic=(ImageView) view.findViewById(R.id.mainimg);
            postvdo=(VideoView) view.findViewById(R.id.video);
            postlikes=(TextView) view.findViewById(R.id.likecount);
            postcomments=(TextView) view.findViewById(R.id.commentcount);
            postshare=(TextView) view.findViewById(R.id.sharecount);
            posttime=(TextView) view.findViewById(R.id.TimeStamp);
        }
    }

}

