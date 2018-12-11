package com.example.zzpcomputer.register.Thread;


import com.example.zzpcomputer.register.utils.HttpMethod;
import com.example.zzpcomputer.register.utils.MyProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * 获取朋友圈动态数据
 */
@SuppressWarnings("all")
public class GetMoodHttpThread extends Thread {
    private String uname;
    private String result;
    public GetMoodHttpThread() {
    }

    public GetMoodHttpThread(String uname) {
        this.uname = uname;
    }

    @Override
    public void run() {
        try {
            URL url=new URL(MyProperties.URL+"mood?uname="+uname);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(String.valueOf(HttpMethod.GET));
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String temp;
                StringBuffer stringBuffer=new StringBuffer();
                while((temp=bufferedReader.readLine())!=null){
                    stringBuffer.append(temp);
                }
                result = Arrays.toString(stringBuffer.toString().trim().split("--"));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getResult() {
        return result;
    }
}
