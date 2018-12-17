package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.zzpcomputer.register.adapter.PyqItemAdapter;
import com.example.zzpcomputer.register.model.PyqItem;

import java.util.List;

@SuppressWarnings("all")
public class PyqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyq);

    /**
     *  返回按钮
     */
        TextView back2 = findViewById(R.id.back2);
        back2.setOnClickListener(v -> {
            Intent intentLast = getIntent();
            Bundle bundle = intentLast.getExtras();
            Intent intent = new Intent(PyqActivity.this, IndexActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        Intent intentLast = getIntent();
        Bundle bundle = intentLast.getExtras();
        String uname = bundle.getString("uname");
        GetMoodHttpThread getMoodHttpThread = new GetMoodHttpThread(uname);
        getMoodHttpThread.start();
        try {
            getMoodHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<PyqItem> list = JSON.parseArray(getMoodHttpThread.getResult(), PyqItem.class);
        PyqItemAdapter adapter = new PyqItemAdapter(this, R.layout.layout, list);
        ListView listView = findViewById(R.id.dt);
        listView.setAdapter(adapter);

    /**
     * 写说说点击事件
     */
        ImageView add = findViewById(R.id.addMood);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(PyqActivity.this, WriteMoodActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    /**
     * 转发按钮，双击转发
     */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView imageView = view.findViewById(R.id.zf);
                imageView.setOnClickListener(v -> {
                    Bundle bundle1 = getIntent().getExtras();
                    TextView srcNameView = view.findViewById(R.id.unamePyq);
                    String srcName = srcNameView.getText().toString();
                    TextView srcMoodView = view.findViewById(R.id.mood);
                    String srcMood = srcMoodView.getText().toString();
                    ImageView imageView1 = view.findViewById(R.id.moodImage);
                    String forwardMoodFirst = "(转发自'" + srcName + "')" + srcMood;
                    String forwardMood = srcMood.startsWith("(转发自") ? srcMood : forwardMoodFirst;

                    String moodImgurl = (String) imageView1.getTag();
                    ForwardHttpThread forwardHttpThread = new ForwardHttpThread(bundle1.getString("uname"), forwardMood, moodImgurl);
                    forwardHttpThread.start();
                    try {
                        forwardHttpThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(PyqActivity.this, forwardHttpThread.isOk() ? "转发成功" : "转发失败", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PyqActivity.this, PyqActivity.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                });
            }
        });

    }
}
