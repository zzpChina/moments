package com.example.zzpcomputer.register.Thread;


import android.util.Log;

import com.example.zzpcomputer.register.utils.HttpMethod;
import com.example.zzpcomputer.register.utils.MyHost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 发表线程请求
 */
@SuppressWarnings("all")
public class PublishHttpThread extends Thread {
    private String uname;
    private String content;
    private String chourl;
    private String moodImgurl;
    private boolean isOk;

    private PublishHttpThread() {
    }

    public PublishHttpThread(String uname, String content, String chourl, String moodImgurl) {
        this.uname = uname;
        this.content = content;
        this.chourl = chourl;
        this.moodImgurl = moodImgurl;
    }

    @Override
    public void run() {
        try {

            URL url = new URL(MyHost.URL + chourl + "?uname=" + URLEncoder.encode(uname, "utf-8") + "&content=" + URLEncoder.encode(content, "utf-8") + "&moodImgUrl=" + moodImgurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(String.valueOf(HttpMethod.GET));
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp);
                }
                isOk = stringBuilder.toString().trim().equals("yes");

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
