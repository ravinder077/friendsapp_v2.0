package com.example.ravinder077.friendsapp.model;

import android.graphics.Bitmap;

/**
 * Created by ravinder077 on 16-07-2017.
 */

public class CardDataPost {


    String  postId;
    String profilePostImg;
    String profilePostName;
    String postTime;
    String postdesc;
    String postImg;
    String postVideo;
    String postLike;
    String postComment;
    String postShare;
    Bitmap bitmapImg;

    public Bitmap getBitmapProfileImg() {
        return bitmapProfileImg;
    }

    public void setBitmapProfileImg(Bitmap bitmapProfileImg) {
        this.bitmapProfileImg = bitmapProfileImg;
    }

    Bitmap bitmapProfileImg;
    public Bitmap getBitmapImg() {
        return bitmapImg;
    }

    public void setBitmapImg(Bitmap bitmapImg) {
        this.bitmapImg = bitmapImg;
    }



    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getProfilePostImg() {
        return profilePostImg;
    }

    public void setProfilePostImg(String profilePostImg) {
        this.profilePostImg = profilePostImg;
    }

    public String getProfilePostName() {
        return profilePostName;
    }

    public void setProfilePostName(String profilePostName) {
        this.profilePostName = profilePostName;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostdesc() {
        return postdesc;
    }

    public void setPostdesc(String postdesc) {
        this.postdesc = postdesc;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    public String getPostVideo() {
        return postVideo;
    }

    public void setPostVideo(String postVideo) {
        this.postVideo = postVideo;
    }

    public String getPostLike() {
        return postLike;
    }

    public void setPostLike(String postLike) {
        this.postLike = postLike;
    }

    public String getPostComment() {
        return postComment;
    }

    public void setPostComment(String postComment) {
        this.postComment = postComment;
    }

    public String getPostShare() {
        return postShare;
    }

    public void setPostShare(String postShare) {
        this.postShare = postShare;
    }
}
