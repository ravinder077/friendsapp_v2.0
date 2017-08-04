package com.example.ravinder077.friendsapp;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WallData wallData = postlist.get(position);


        holder.profilepic.setImageResource(wallData.getProfilepic());
        holder.profilename.setText(wallData.getProfilename());
        holder.poststatus.setText(wallData.getPoststatus());

        holder.postpic.setImageResource(wallData.getPostpic());
        holder.postlikes.setText(wallData.getPostlikes());
        holder.postcomments.setText(wallData.getPostcomments());
        holder.postshare.setText(wallData.getPostshare());
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
        public TextView postlikes;
        public TextView postcomments;
        public TextView postshare;
        public MyViewHolder(View view) {
            super(view);
            profilepic = (CircleImageView) view.findViewById(R.id.img);
            profilename = (TextView) view.findViewById(R.id.ProfileName);
            poststatus = (TextView) view.findViewById(R.id.status);
            postpic=(ImageView) view.findViewById(R.id.mainimg);
            postlikes=(TextView) view.findViewById(R.id.likecount);
            postcomments=(TextView) view.findViewById(R.id.commentcount);
            postshare=(TextView) view.findViewById(R.id.sharecount);
        }
    }

}

