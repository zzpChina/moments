package com.example.zzpcomputer.register.model;

/**
 * 朋友圈条目的布局类
 */
public class PyqItem {
    private String headImg;
    private String uname;
    private String mood;
    private String moodImg;

    public PyqItem() {
    }

    public PyqItem(String headImg, String uname, String mood, String moodImg) {
        this.headImg = headImg;
        this.uname = uname;
        this.mood = mood;
        this.moodImg = moodImg;
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

    public String getMoodImg() {
        return moodImg;
    }

    public void setMoodImg(String moodImg) {
        this.moodImg = moodImg;
    }
}
