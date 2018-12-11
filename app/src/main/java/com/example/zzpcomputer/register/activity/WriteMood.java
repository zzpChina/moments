package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zzpcomputer.register.R;

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
    }
}
