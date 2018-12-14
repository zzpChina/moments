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
import com.example.zzpcomputer.register.Thread.RegisterHttpThread;

@SuppressWarnings("all")
/**
 * 注册页面操作
 */
public class Register extends AppCompatActivity {
    //获取名字
    EditText unameView=null;
    //密码
    EditText pwdView=null;
    //获取性别
    RadioGroup sexs=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载验证码
        createCode();
        Button sub=(Button)findViewById(R.id.register);
/**
 * 点击注册按钮，通过注册HTTP线程返回注册结果
 */
        sub.setOnClickListener(v->{
            //如果信息填写完整
           if(checkAll()){
               String sex_reg=null;
               for (int i=0;i< sexs.getChildCount();i++){
                   RadioButton radioButton=(RadioButton) sexs.getChildAt(i);
                   if(radioButton.isChecked()){
                       sex_reg=radioButton.getText().toString();
                   }
               }
               //注册线程，传参（uname,password,sex）
               RegisterHttpThread registerHttpThread=new RegisterHttpThread(unameView.getText().toString(),pwdView.getText().toString(),sex_reg);
               registerHttpThread.start();
               try {
                   registerHttpThread.join();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               Toast.makeText(this, registerHttpThread.isOk()?"注册成功":"注册失败", Toast.LENGTH_SHORT).show();

           }

        });

/**
 * 返回按钮,点击跳转回登录页面
 */
        TextView back=(TextView)findViewById(R.id.back);
        back.setOnClickListener(v->{
            Intent intent=new Intent(Register.this,Login.class);
            startActivity(intent);
        });

    }

/**
 * 将所有判断条件一起判断
 * @return
 */
    public boolean checkAll(){
        return checkName()&&checkPwd()&&checkSex()&&checkCode()?true:false;
    }

    public boolean checkName(){
            unameView=(EditText)findViewById(R.id.uname);
            String uname=unameView.getText().toString();
            if(uname.equals("")){
                Toast.makeText(Register.this,"用户名不能为空!",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
    }

    public String getSex(){
        for(int i=0;i<sexs.getChildCount();i++){
            RadioButton sex=(RadioButton) sexs.getChildAt(i);
            if(sex.isChecked()){
                return sex.getText().toString();
            }
        }
        return "";
    }
    public boolean checkPwd(){
        pwdView=(EditText)findViewById(R.id.pwd);
        String  pwd=pwdView.getText().toString();
        EditText pwdSureView=(EditText)findViewById(R.id.pwdsure);
        String pwdsure=pwdSureView.getText().toString();
        if(pwd.equals("")){
            Toast.makeText(Register.this,"密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pwd.equals(pwdsure)){
            Toast.makeText(Register.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkSex(){
        sexs=(RadioGroup)findViewById(R.id.sex);
        for(int i=0;i<sexs.getChildCount();i++){
            RadioButton sex=(RadioButton) sexs.getChildAt(i);
            if(sex.isChecked()){
                return true;
            }
        }
        Toast.makeText(Register.this,"请选择性别",Toast.LENGTH_SHORT).show();
        return false;
    }

    public boolean checkCode(){
        //获取验证码
        TextView code=(TextView)findViewById(R.id.codespan);
        EditText mycode=(EditText)findViewById(R.id.code);
        if(!mycode.getText().toString().equals("")){
           if(!code.getText().toString().equals(mycode.getText().toString())){
               Toast.makeText(Register.this,"验证码错误",Toast.LENGTH_SHORT).show();
               return false;
           }
         }else{
            Toast.makeText(Register.this,"请输入验证码!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void createCode(){
        int randomNum=(int)(Math.random()*9000)+1000;
        TextView code=(TextView)findViewById(R.id.codespan);
        code.setText(String.valueOf(randomNum));
    }
}
