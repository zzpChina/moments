package com.example.zzpcomputer.register.Thread;

import android.util.Log;

import com.example.zzpcomputer.register.utils.HttpMethod;
import com.example.zzpcomputer.register.utils.MyProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 发表线程请求
 */
@SuppressWarnings("all")
public class PublishHttpThread extends Thread{
    private String uname;
    private String content;
    private String chourl;
    private boolean isOk;

    private PublishHttpThread(){}
    public PublishHttpThread(String uname,String content,String chourl) {
        this.uname = uname;
        this.content=content;
        this.chourl=chourl;
    }

    @Override
    public void run() {
        try {
            Log.i("哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇",chourl);
            URL url=new URL(MyProperties.URL +chourl+"?uname="+uname+"&content="+content);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(String.valueOf(HttpMethod.GET));
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode()==200){
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String temp;
                StringBuilder stringBuilder=new StringBuilder();
                while((temp=bufferedReader.readLine())!=null){
                    stringBuilder.append(temp);
                }
                isOk=stringBuilder.toString().trim().equals("yes");

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOk() {
        return isOk;
    }
}
