package com.example.ravinder077.friendsapp;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Chugh on 8/3/2017.
 */

public class ChatCardAdapter extends RecyclerView.Adapter<ChatCardAdapter.MyViewHolder> {


    private List<ChatData> postlist;



    public ChatCardAdapter(List<ChatData> postlist) {
        this.postlist = postlist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallcard, parent, false);

        return new ChatCardAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ChatData ChatData = postlist.get(position);


        holder.profilepic.setImageBitmap(ChatData.getBitprofilepic());
        holder.profilename.setText(ChatData.getProfilename());

    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

       public de.hdodenhof.circleimageview.CircleImageView profilepic;
        public TextView profilename;
        public TextView groupname;
        public ImageView pagepic;
        public TextView pagename;

        public MyViewHolder(View view) {
            super(view);
            profilepic = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.dp);
            profilename = (TextView) view.findViewById(R.id.name);
            groupname = (TextView) view.findViewById(R.id.name1);
            pagepic=(ImageView) view.findViewById(R.id.pagedpoverlay);
            pagename=(TextView) view.findViewById(R.id.name2);

        }
    }

}

