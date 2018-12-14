package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.LoginHttpThread;

@SuppressWarnings("all")
/**
 * 登录页面操作
 */
public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

/**
 * 点击注册跳转注册页面
 */
        TextView re=(TextView)findViewById(R.id.reg);
        re.setOnClickListener(v->{
            Intent intent2=new Intent(Login.this,Register.class);
            startActivity(intent2);
        });
/**
 * 点击登录跳转到主页,跳转主页必须将用户名传入
 */
        Button login=(Button)findViewById(R.id.log);
        EditText uname=findViewById(R.id.uname2);
        EditText pwd=findViewById(R.id.pwd2);
        login.setOnClickListener(v->{
            //如果登录信息填写缺失,则不再向下执行
            if(!checkLogin()){return;}
            //信息填写完全，开启登录Http线程
            LoginHttpThread loginHttpThread=new LoginHttpThread(uname.getText().toString(),pwd.getText().toString());
            loginHttpThread.start();
            try {
                loginHttpThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断登录信息是否正确
            if( loginHttpThread.isOk()){
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

/**
 *
 * @return
 */
    public boolean checkLogin(){
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
    }
}
