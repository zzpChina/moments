package com.example.zzpcomputer.register.Thread;

import com.example.zzpcomputer.register.utils.HttpMethod;
import com.example.zzpcomputer.register.utils.MyHost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class UpdateHttpThread extends Thread {
    private String uname;
    private String oldPwd;
    private String newPwd;
    private boolean isOk;

    public UpdateHttpThread(String uname, String oldPwd, String newPwd) {
        this.uname = uname;
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
    }

    public UpdateHttpThread() {
    }

    @Override
    public void run() {
        try {
            URL url = new URL(MyHost.URL + "update?uname=" + URLEncoder.encode(uname, "utf-8") + "&oldPwd="
                    + URLEncoder.encode(oldPwd, "utf-8") + "&newPwd=" + URLEncoder.encode(newPwd, "utf-8"));
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOk() {
        return isOk;
    }
}
