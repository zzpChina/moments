package com.example.zzpcomputer.register.model;

/**
 * 朋友圈的布局类
 */
public class PyqItem {
    private String headImg;
    private String uname;
    private String mood;

    public PyqItem(){}

    public PyqItem(String headImg, String uname, String mood) {
        this.headImg = headImg;
        this.uname = uname;
        this.mood = mood;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
