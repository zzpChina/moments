package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.zzpcomputer.register.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏以及状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        //延时三秒
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    /**
     * 处理
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            goLogin();
            super.handleMessage(msg);
        }
    };

    /**
     * 跳转登录页面
     */
    private void goLogin() {
        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
