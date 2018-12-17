package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.PublishHttpThread;

@SuppressWarnings("all")
/**
 * 写说说
 */
public class WriteMoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mood);
        TextView back4 = findViewById(R.id.back4);
    /**
    * 返回朋友圈数据，由于需要再次加载朋友圈内容，所以需要交互数据
    */
        //返回朋友圈
        back4.setOnClickListener(v -> {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            Intent intent2 = new Intent(WriteMoodActivity.this, PyqActivity.class);
            intent2.putExtras(bundle);
            startActivity(intent2);
        });
        Button button = findViewById(R.id.publishMood);
        EditText editText = findViewById(R.id.moodText);
        RadioGroup radioGroup = findViewById(R.id.quanxian);
        RadioGroup radioGroup1 = findViewById(R.id.moodImg);
        //输入框不为空则能够发表
    /**
     * 发布朋友圈，需要传输数据
     */
        button.setOnClickListener(v -> {
            if (!editText.getText().toString().trim().equals("") && editText.getText().toString() != null &&
                    (editText.getText().toString().indexOf("--") == -1) && (editText.getText().toString().indexOf("\\") == -1)) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                String whocansee = "";
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        whocansee = radioButton.getText().toString();
                    }

                }

            /**
             * 设置谁可看我发的动态
             */
                String chourl = "";
                if (whocansee.equals("仅自己可见")) {
                    chourl = "getMood";
                } else if (whocansee.equals("群组可见")) {
                    chourl = "getMood2";
                } else if (whocansee.equals("所有人可见")) {
                    chourl = "getMood3";
                }
                String moodimgUrl = "";
                String needImg = "";
                for (int i = 0; i < radioGroup1.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup1.getChildAt(i);
                    if (radioButton.isChecked()) {
                        needImg = radioButton.getText().toString();
                    }
                }
                if (needImg.equals("开心")) {
                    moodimgUrl = "img/happy.jpg";
                } else if (needImg.equals("难过")) {
                    moodimgUrl = "img/upset.jpg";
                } else if (needImg.equals("愤怒")) {
                    moodimgUrl = "img/angry.jpg";
                }
                //开启线程
                PublishHttpThread publishHttpThread = new PublishHttpThread(bundle.getString("uname"), editText.getText().toString(), chourl, moodimgUrl);
                publishHttpThread.start();
                try {
                    publishHttpThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String result = publishHttpThread.isOk() ? "写说说成功" : "写说说失败";
                Intent intent3 = getIntent();
                Bundle bundle2 = intent.getExtras();
                Intent intent2 = new Intent(WriteMoodActivity.this, PyqActivity.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
            } else {
                Toast.makeText(WriteMoodActivity.this, "输入内容为空或者包含 --或包含\\", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
