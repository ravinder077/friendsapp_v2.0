package com.example.ravinder077.friendsapp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Chugh on 8/3/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {


    private List<PageData> postlist;
    private Context mcontext;

    public ChatAdapter(List<PageData> postlist) {
        this.postlist = postlist;
    }


    public ChatAdapter(List<PageData> postlist, Context context) {

        this.mcontext=context;
        this.postlist = postlist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pagecard, parent, false);

        return new ChatAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final PageData wallData = postlist.get(position);

        Glide.with(holder.pagedp.getContext()).load(wallData.getPageprofileimg())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pagedp);

        holder.pagetext.setText(wallData.getPagename());
        holder.pagedesc.setText(wallData.getPagedesc());


    }



    @Override
    public int getItemCount() {
        return postlist.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {


        public ImageView pagedp;
        public TextView  pagetext;
        public TextView  pagedesc;
        public CircleImageView chatdp;
        public TextView chatname;
        public TextView chatlstmsg;
        public LinearLayout linearLayoutpage;
        public LinearLayout linearLayoutchat;


        public MyViewHolder(View view) {
            super(view);


            pagedp = (ImageView) view.findViewById(R.id.pagedp);
            pagetext = (TextView) view.findViewById(R.id.name2);
            pagedesc = (TextView) view.findViewById(R.id.lstmsg2);
            chatdp=(CircleImageView)view.findViewById(R.id.chatdp);
            chatname = (TextView) view.findViewById(R.id.chatname);
            chatlstmsg = (TextView) view.findViewById(R.id.chatlstmsg);
            linearLayoutpage=(LinearLayout)view.findViewById(R.id.linearLayoutpage);
            linearLayoutchat=(LinearLayout)view.findViewById(R.id.linearLayoutchat);
        }
    }

}

