package com.tuespotsolutions.ravinder077.friendsapp.model;

import android.graphics.Bitmap;

/**
 * Created by SandahSaab on 8/17/2017.
 */

public class ChatData {


    private String profilename;
    private String groupname;
    private String pagename;
    private String profilepic;
    private Bitmap bitprofilepic;
    private String grouppic;
    private String pagepic;
    private Bitmap bitpagepic;
    private String onlinestatus;
    private String msgcount;


    public Bitmap getBitpagepic() {
        return bitpagepic;
    }

    public void setBitpagepic(Bitmap bitpagepic) {
        this.bitpagepic = bitpagepic;
    }



    public Bitmap getBitprofilepic() {
        return bitprofilepic;
    }

    public void setBitprofilepic(Bitmap bitprofilepic) {
        this.bitprofilepic = bitprofilepic;
    }


    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getGrouppic() {
        return grouppic;
    }

    public void setGrouppic(String grouppic) {
        this.grouppic = grouppic;
    }

    public String getPagepic() {
        return pagepic;
    }

    public void setPagepic(String pagepic) {
        this.pagepic = pagepic;
    }

    public String getOnlinestatus() {
        return onlinestatus;
    }

    public void setOnlinestatus(String onlinestatus) {
        this.onlinestatus = onlinestatus;
    }

    public String getMsgcount() {
        return msgcount;
    }

    public void setMsgcount(String msgcount) {
        this.msgcount = msgcount;
    }

}
