package com.example.zzpcomputer.register.Thread;


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
 * 用于判断登录的线程
 */
@SuppressWarnings("all")
public class LoginHttpThread extends Thread {
    private String uname;
    private String pwd;
    private boolean isOk;

    public LoginHttpThread() {
        this.isOk = false;
    }

    public LoginHttpThread(String uname, String pwd) {
        this.isOk = false;
        this.uname = uname;
        this.pwd = pwd;
    }

    @Override
    public void run() {
        try {
            //请求url
            URL url = new URL(MyHost.URL + "/login?uname=" + URLEncoder.encode(uname, "utf-8") + "&pwd=" + URLEncoder.encode(pwd, "utf-8"));
            //获取http连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(String.valueOf(HttpMethod.GET));
            httpURLConnection.connect();//连接
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String temp;
                StringBuffer stringBuffer = new StringBuffer();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuffer.append(temp);
                }
                isOk = stringBuffer.toString().trim().equals("yes");
            } else {
                isOk = false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断账号密码是否正确
     *
     * @return true:正确 false:错误
     */
    public boolean isOk() {
        return isOk;
    }
}
