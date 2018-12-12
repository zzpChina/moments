package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.LoginHttpThread;
import com.example.zzpcomputer.register.utils.DBHelper;

@SuppressWarnings("all")
public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView re=(TextView)findViewById(R.id.reg);
        re.setOnClickListener(v->{
            Intent intent2=new Intent(Login.this,Register.class);
            startActivity(intent2);
        });
        Button login=(Button)findViewById(R.id.log);
        EditText uname=findViewById(R.id.uname2);
        EditText pwd=findViewById(R.id.pwd2);
        login.setOnClickListener(v->{
            if(!checkLogin()){return;}
            LoginHttpThread loginHttpThread=new LoginHttpThread(uname.getText().toString(),pwd.getText().toString());
            loginHttpThread.start();
            try {
                loginHttpThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean success=loginHttpThread.isOk();
            if(success){
                    Bundle bundle=new Bundle();
                    Intent intent3=new Intent(Login.this,Index.class);
                    bundle.putString("uname",uname.getText().toString());
                    intent3.putExtras(bundle);
                    startActivity(intent3);
            }else{
                Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            }
            
        });
    }

    public boolean checkLogin(){
        DBHelper dbHelper=new DBHelper(this,"yue.db",null,1);
        Cursor cursor=dbHelper.getReadableDatabase()
                .query("user",null,null,null,null,null,null);
        EditText e1=(EditText)findViewById(R.id.uname2);
        EditText e2=(EditText)findViewById(R.id.pwd2);
        if(e1.getText().toString().equals("")){
            Toast.makeText(Login.this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(e2.getText().toString().equals("")){
                Toast.makeText(Login.this,"请输入密码",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
//        String nn=e1.getText().toString();
//        String pp=e2.getText().toString();
//        while(cursor.moveToNext()){
//            String name=cursor.getString(0);
//            String pwd=cursor.getString(1);
//            if(name.equals(nn)&&pwd.equals(pp)) {
//                Toast.makeText(Login.this, "登录成功!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
//        Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
    }
}
