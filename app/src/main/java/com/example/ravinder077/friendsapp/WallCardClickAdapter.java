package com.example.ravinder077.friendsapp;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by SandahSaab on 8/22/2017.
 */

public class WallCardClickAdapter extends RecyclerView.Adapter<WallCardClickAdapter.MyViewHolder> {

    private List<WallCardClickData> comments;

    public WallCardClickAdapter(List<WallCardClickData> postlist) {
        comments = postlist;
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public  de.hdodenhof.circleimageview.CircleImageView profileimg;
        public TextView profilename;
        public TextView timestamp;
        public TextView status;
        public ImageView mainimg;
        public de.hdodenhof.circleimageview.CircleImageView commenterimg;
        public TextView commentername;
        public TextView comment;


        public MyViewHolder(View itemView) {
            super(itemView);

            profileimg = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.pic);
            profilename = (TextView) itemView.findViewById(R.id.username);
            timestamp = (TextView) itemView.findViewById(R.id.time);
            status = (TextView) itemView.findViewById(R.id.status);
            mainimg = (ImageView) itemView.findViewById(R.id.mainimg);
            commenterimg = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.commenterdp);
            commentername = (TextView) itemView.findViewById(R.id.commenter);
            comment = (TextView) itemView.findViewById(R.id.comment);

        }
    }

    @Override
    public WallCardClickAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallcardclickitem, parent, false);

        return new WallCardClickAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WallCardClickAdapter.MyViewHolder holder, int position) {
        final WallCardClickData wallclickdata = comments.get(position);


        holder.profileimg.setImageBitmap(wallclickdata.getProfileimgbit());
        holder.profilename.setText(wallclickdata.getProfilename());
        holder.status.setText(wallclickdata.getStatus());
        holder.timestamp.setText(wallclickdata.getTime());
        holder.mainimg.setImageBitmap(wallclickdata.getMainimgbit());
        holder.commenterimg.setImageBitmap(wallclickdata.getCommenterimgbit());
        holder.commentername.setText(wallclickdata.getCommentername());
        holder.comment.setText(wallclickdata.getComment());



    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
