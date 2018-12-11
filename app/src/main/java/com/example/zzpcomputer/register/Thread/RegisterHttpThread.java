package com.example.zzpcomputer.register.Thread;

import com.example.zzpcomputer.register.utils.MyProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 注册
 */
public class RegisterHttpThread extends Thread{
    private String uname;
    private String password;
    private String sex;
    private boolean isOk;
    public RegisterHttpThread() {
    }
    public RegisterHttpThread(String uname,String password,String sex){
        this.uname=uname;
        this.password=password;
        this.sex=sex;
    }

    @Override
    public void run() {
        try {
            URL url=new URL(MyProperties.URL+"/reg");
            //获取连接
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            //设置请求方法
            httpURLConnection.setRequestMethod("POST");
            String body="uname="+URLEncoder.encode(uname,"utf-8")
                    +"&password="+URLEncoder.encode(password,"utf-8")
                    +"&sex="+URLEncoder.encode(sex,"utf-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.getOutputStream().write(body.getBytes());
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode()==200){
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader  inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String temp;
                StringBuffer stringBuffer=new StringBuffer();
                while((temp=bufferedReader.readLine())!=null){
                    stringBuffer.append(temp);
                }
                isOk=stringBuffer.toString().equals("yes");
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
