package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.ForwardHttpThread;
import com.example.zzpcomputer.register.Thread.GetMoodHttpThread;
import com.example.zzpcomputer.register.adapter.FormAdapter;
import com.example.zzpcomputer.register.model.PyqItem;

import java.util.List;
@SuppressWarnings("all")
public class Pyq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyq);

/**
 *
 */
        TextView back2=findViewById(R.id.back2);
        back2.setOnClickListener(v->{
            Intent intentLast=getIntent();
            Bundle bundle=intentLast.getExtras();
            Intent intent=new Intent(Pyq.this,Index.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        Intent intentLast=getIntent();
        Bundle bundle=intentLast.getExtras();
        String uname=bundle.getString("uname");
        GetMoodHttpThread getMoodHttpThread=new GetMoodHttpThread(uname);
        getMoodHttpThread.start();
        try {
            getMoodHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<PyqItem> list= JSON.parseArray(getMoodHttpThread.getResult(),PyqItem.class);
        FormAdapter adapter=new FormAdapter(this,R.layout.layout,list);
        ListView listView=findViewById(R.id.dt);
        listView.setAdapter(adapter);

        ImageView add=findViewById(R.id.addMood);
        //进入写说说
        add.setOnClickListener(v->{
            Intent intent=new Intent(Pyq.this,WriteMood.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView imageView=view.findViewById(R.id.zf);
                imageView.setOnClickListener(v->{
                    Bundle bundle1=getIntent().getExtras();
                    TextView srcNameView=view.findViewById(R.id.unamePyq);
                    String srcName=srcNameView.getText().toString();
                    TextView srcMoodView=view.findViewById(R.id.mood);
                    String srcMood=srcMoodView.getText().toString();
                    ImageView imageView1=view.findViewById(R.id.moodImage);
                    String forwardMoodFirst="(转发自'"+srcName+"')"+srcMood;
                    String forwardMood=srcMood.startsWith("(转发自")?srcMood:forwardMoodFirst;

                    ForwardHttpThread forwardHttpThread=new ForwardHttpThread(bundle1.getString("uname"),forwardMood);
                    forwardHttpThread.start();
                    try {
                        forwardHttpThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Pyq.this, forwardHttpThread.isOk()?"转发成功":"转发失败", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Pyq.this,Pyq.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                });
            }
        });

    }
}
