package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.GetHeadImgUrl;
import com.example.zzpcomputer.register.Thread.HeadImgHttpThread;
import com.example.zzpcomputer.register.utils.CreateCircular;

/**
 * 主页操作
 */
public class Index extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        String uname=getIntent().getExtras().getString("uname");
        GetHeadImgUrl getHeadImgUrl=new GetHeadImgUrl(uname);
        getHeadImgUrl.start();
        try {
            getHeadImgUrl.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String resultUrl=getHeadImgUrl.getResultUrl();
        HeadImgHttpThread headImgHttpThread=new HeadImgHttpThread(resultUrl);
        headImgHttpThread.start();
        try {
            headImgHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bitmap bitmap=headImgHttpThread.getResultBitmap();
        Bitmap cirBitmap=CreateCircular.createCircleImage(bitmap);
        ImageView imageViewHead=findViewById(R.id.yourHead);
        imageViewHead.setImageBitmap(cirBitmap);


/**
 * 跳转朋友圈页面页面操作，必须传值
 */
        ImageView pyq=findViewById(R.id.pyq);
        pyq.setOnClickListener(v->{
            Intent intentLast=getIntent();
            Bundle bundle=intentLast.getExtras();
            Intent intent4=new Intent(Index.this,Pyq.class);
            intent4.putExtras(bundle);
            startActivity(intent4);
        });
/**
 * 返回登录界面,不需要传值
 */
        TextView textView=findViewById(R.id.backlog);
        textView.setOnClickListener(v->{
            Intent intent=new Intent(Index.this,Login.class);
            startActivity(intent);
        });
/**
 跳转选择群组页面，需要传值
 */
        ImageView groupImg=findViewById(R.id.ql);
        groupImg.setOnClickListener(v->{
            Intent intentLast=getIntent();
            Bundle bundle=intentLast.getExtras();
            Intent intent4=new Intent(Index.this,ChooseGroup.class);
            intent4.putExtras(bundle);
            startActivity(intent4);
        });
        
    }
}
