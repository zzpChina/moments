package com.example.zzpcomputer.register.Thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.zzpcomputer.register.utils.MyProperties;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * 头像获取
 */
@SuppressWarnings("all")
public class HeadImgHttpThread extends Thread{
    private String imageUrl;
    private Bitmap resultBitmap;

    public HeadImgHttpThread(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void run() {
        try {
            URL url=new URL(MyProperties.URL+imageUrl);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            setResultBitmap(BitmapFactory.decodeStream(inputStream));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getResultBitmap() {
        return resultBitmap;
    }

    public void setResultBitmap(Bitmap resultBitmap) {
        this.resultBitmap = resultBitmap;
    }
}
