package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zzpcomputer.register.R;

public class Index extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ImageView pyq=findViewById(R.id.pyq);
        pyq.setOnClickListener(v->{
            Intent intentLast=getIntent();
            Bundle bundle=intentLast.getExtras();
            Intent intent4=new Intent(Index.this,Pyq.class);
            intent4.putExtras(bundle);
            startActivity(intent4);
        });
        
    }
}
