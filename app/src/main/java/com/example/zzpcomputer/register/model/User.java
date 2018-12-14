package com.example.zzpcomputer.register.model;

/**
 * 用户类
 */
@SuppressWarnings("all")
public class User {
    private String headImg;
    private String uname;
    private String pwd;
    private String sex;

    public User(){

    }
    public User(String headImg,String uname,String pwd,String sex){
        this.headImg=headImg;
        this.uname=uname;
        this.pwd=pwd;
        this.sex=sex;
    }

    public String  getUname(){
        return uname;
    }
    public void setName(String uname){
        this.uname=uname;
    }
    public void setPwd(String pwd){
        this.pwd=pwd;
    }
    public String getPwd(){
        return pwd;
    }
    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public String getInfo(){
        return uname+pwd+sex;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
