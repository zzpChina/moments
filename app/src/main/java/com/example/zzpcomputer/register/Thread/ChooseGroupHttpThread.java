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
 * 选择朋友圈线程
 */
@SuppressWarnings("all")
public class ChooseGroupHttpThread extends Thread {
    private String uname;
    private String groupNum;
    private boolean isOk;

    public ChooseGroupHttpThread(String uname, String groupNum) {
        this.uname = uname;
        this.groupNum = groupNum;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(MyHost.URL + "getGroup?uname=" + URLEncoder.encode(uname, "utf-8") + "&groupNum=" + URLEncoder.encode(groupNum, "utf-8"));
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
