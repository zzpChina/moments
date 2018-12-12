package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.PublishHttpThread;

import org.w3c.dom.Text;
@SuppressWarnings("all")
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
        CheckBox checkBox=findViewById(R.id.privatePub);
        //输入框不为空则能够发表

        button.setOnClickListener(v -> {
            if (!editText.getText().toString().trim().equals("")&&editText.getText().toString()!=null&&(editText.getText().toString().indexOf("--")==-1)) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                String chourl=checkBox.isChecked()?"getMood":"getMood2";
                PublishHttpThread publishHttpThread = new PublishHttpThread(bundle.getString("uname"), editText.getText().toString(),chourl);
                publishHttpThread.start();
                try {
                    publishHttpThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String result = publishHttpThread.isOk() ? "写说说成功" : "写说说失败";
                Intent intent3=getIntent();
                Bundle bundle2=intent.getExtras();
                Intent intent2=new Intent(WriteMood.this,Pyq.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }else{
                Toast.makeText(WriteMood.this, "输入内容为空或者包含 --", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
