package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.ChooseGroupHttpThread;

/**
 * 选择朋友圈界面
 */
public class ChooseGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_group);
    /**
     * 返回主页跳转，需要带回数据
     */
        TextView backView = findViewById(R.id.groupBack);
        backView.setOnClickListener(v -> {
            Intent intentLast = getIntent();
            Bundle bundle = intentLast.getExtras();
            Intent intentBack = new Intent(ChooseGroupActivity.this, IndexActivity.class);
            intentBack.putExtras(bundle);
            startActivity(intentBack);
        });

        Button button = findViewById(R.id.regGroup);
        EditText editText = findViewById(R.id.groupNum);
    /**
     * 选择完毕按钮点击跳转
     */
        button.setOnClickListener(v -> {
            Intent intentLast = getIntent();
            Bundle bundle = intentLast.getExtras();
            String uname = bundle.getString("uname");
            //加入群聊HTTP线程
            ChooseGroupHttpThread chooseGroupHttpThread = new ChooseGroupHttpThread(uname, editText.getText().toString());
            chooseGroupHttpThread.start();
            try {
                chooseGroupHttpThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (chooseGroupHttpThread.isOk()) {
                Intent chooseOk = getIntent();
                Bundle bundle3 = intentLast.getExtras();
                Intent intent = new Intent(ChooseGroupActivity.this, IndexActivity.class);
                intent.putExtras(bundle3);
                startActivity(intent);
            }

        });
    }
}
