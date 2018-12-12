package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.PublishHttpThread;

import org.w3c.dom.Text;

public class WriteMood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mood);
        TextView back4=findViewById(R.id.back4);
        //返回朋友圈
        back4.setOnClickListener(v->{
            Intent intent=getIntent();
            Bundle bundle=intent.getExtras();
            Intent intent2=new Intent(WriteMood.this,Pyq.class);
            intent2.putExtras(bundle);
            startActivity(intent2);
        });
        Button button=findViewById(R.id.publishMood);
        EditText editText=findViewById(R.id.moodText);
        //输入框不为空则能够发表
        if (editText.getText().toString()!=""&&editText.getText().toString()!=null) {
            button.setOnClickListener(v -> {
                Intent intent=getIntent();
                Bundle bundle=intent.getExtras();
                PublishHttpThread publishHttpThread=new PublishHttpThread(bundle.getString("uname"),editText.getText().toString());
                publishHttpThread.start();
                try {
                    publishHttpThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String result=publishHttpThread.isOk()?"写说说成功":"写说说失败";
                Toast.makeText(WriteMood.this,result,Toast.LENGTH_SHORT).show();
            });
        }
    }
}
