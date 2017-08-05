package com.example.ravinder077.friendsapp;

import android.graphics.Bitmap;

/**
 * Created by Chugh on 8/3/2017.
 */

public class WallData {

    private Bitmap bitpostpic;
    private String postid;
    private String profilepic;
    private String profilename;
    private String poststatus;
    private String postpic;
    private String postlikes;
    private String postcomments;
    private String postshare;
    private String posttime;
    private Bitmap bitprofilepic;
    private String postvdo;


    public String getPostvdo() {
        return postvdo;
    }

    public void setPostvdo(String postvdo) {
        this.postvdo = postvdo;
    }


    public Bitmap getBitprofilepic() {
        return bitprofilepic;
    }

    public void setBitprofilepic(Bitmap bitprofilepic) {
        this.bitprofilepic = bitprofilepic;
    }


    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }


    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }



    public Bitmap getBitpostpic() {
        return bitpostpic;
    }

    public void setBitpostpic(Bitmap bitpostpic) {
        this.bitpostpic = bitpostpic;
    }



   /* public WallData(int profilepic, String profilename, String poststatus,int postpic,String postlikes,String postcomments,String postshare) {
      this.profilepic=profilepic;
        this.profilename=profilename;
        this.poststatus=poststatus;
        this.postpic=postpic;
        this.postlikes=postlikes;
        this.postcomments=postcomments;
        this.postshare=postshare;

    }*/

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public String getPoststatus() {
        return poststatus;
    }

    public void setPoststatus(String poststatus) {
        this.poststatus = poststatus;
    }

    public String getPostpic() {
        return postpic;
    }

    public void setPostpic(String postpic) {
        this.postpic = postpic;
    }

    public String getPostlikes() {
        return postlikes;
    }

    public void setPostlikes(String postlikes) {
        this.postlikes = postlikes;
    }

    public String getPostcomments() {
        return postcomments;
    }

    public void setPostcomments(String postcomments) {
        this.postcomments = postcomments;
    }

    public String getPostshare() {
        return postshare;
    }

    public void setPostshare(String postshare) {
        this.postshare = postshare;
    }
}
