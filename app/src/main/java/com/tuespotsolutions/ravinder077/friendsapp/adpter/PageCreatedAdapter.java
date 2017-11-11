package com.tuespotsolutions.ravinder077.friendsapp.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuespotsolutions.ravinder077.friendsapp.R;
import com.tuespotsolutions.ravinder077.friendsapp.model.WallData;

import java.util.List;

/**
 * Created by SandahSaab on 8/22/2017.
 */

public class PageCreatedAdapter extends RecyclerView.Adapter<PageCreatedAdapter.MyViewHolder> {

    private List<WallData> mainimg;
    public PageCreatedAdapter(List<WallData> postlist) {
        mainimg = postlist;

        System.err.println("printing list"+mainimg);

    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView profileimg;
        public TextView profilename;
        public TextView timestamp;
        public TextView status;
        public ImageView mainimg;


        public MyViewHolder(View itemView) {
            super(itemView);

          /*  profileimg = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.pic);
            profilename = (TextView) itemView.findViewById(R.id.username);
            timestamp = (TextView) itemView.findViewById(R.id.time);*/
            status = (TextView) itemView.findViewById(R.id.status);
            mainimg = (ImageView) itemView.findViewById(R.id.mainimg);


        }
    }

    @Override
    public PageCreatedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pagecontents, parent, false);

        return new PageCreatedAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PageCreatedAdapter.MyViewHolder holder, int position) {
        WallData pagecreate = mainimg.get(position);


       /* holder.profileimg.setImageBitmap(wallclickdata.getProfileimgbit());
        holder.profilename.setText(wallclickdata.getProfilename());*/
       // holder.status.setText(pagecreate.getStatus());
      //  holder.timestamp.setText(wallclickdata.getTime());
     //   holder.mainimg.setImageResource(R.drawable.groupdp1);
     /*   holder.commenterimg.setImageResource(R.drawable.user);
        holder.commentername.setText(wallclickdata.getCommentername());
        holder.comment.setText(wallclickdata.getComment());
*/


    }

    @Override
    public int getItemCount() {
        return mainimg.size();
    }
}
