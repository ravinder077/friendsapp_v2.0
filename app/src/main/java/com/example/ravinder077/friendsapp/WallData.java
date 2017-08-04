package com.example.ravinder077.friendsapp;

/**
 * Created by Chugh on 8/3/2017.
 */

public class WallData {

    private int profilepic;
    private String profilename;
    private String poststatus;
    private int postpic;
    private String postlikes;
    private String postcomments;
    private String postshare;

    public WallData(int profilepic, String profilename, String poststatus,int postpic,String postlikes,String postcomments,String postshare) {
      this.profilepic=profilepic;
        this.profilename=profilename;
        this.poststatus=poststatus;
        this.postpic=postpic;
        this.postlikes=postlikes;
        this.postcomments=postcomments;
        this.postshare=postshare;

    }

    public int getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(int profilepic) {
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

    public int getPostpic() {
        return postpic;
    }

    public void setPostpic(int postpic) {
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
