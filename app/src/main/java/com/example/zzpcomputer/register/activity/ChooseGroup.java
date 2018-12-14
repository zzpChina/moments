package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.ChooseGroupHttpThread;

public class ChooseGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_group);

        TextView backView=findViewById(R.id.groupBack);
        backView.setOnClickListener(v->{
            Intent intentLast=getIntent();
            Bundle bundle=intentLast.getExtras();
            Intent intentBack=new Intent(ChooseGroup.this,Index.class);
            intentBack.putExtras(bundle);
            startActivity(intentBack);
        });

        Button button=findViewById(R.id.regGroup);
        EditText editText=findViewById(R.id.groupNum);
        button.setOnClickListener(v->{
            Intent intentLast=getIntent();
            Bundle bundle=intentLast.getExtras();
            String uname=bundle.getString("uname");
            ChooseGroupHttpThread chooseGroupHttpThread=new ChooseGroupHttpThread(uname,editText.getText().toString());
            chooseGroupHttpThread.start();
            try {
                chooseGroupHttpThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result=chooseGroupHttpThread.isOk();
            if(result){
                Intent chooseOk=getIntent();
                Bundle bundle3=intentLast.getExtras();
                Intent intent=new Intent(ChooseGroup.this,Index.class);
                intent.putExtras(bundle3);
                startActivity(intent);
            }

        });
    }
}
