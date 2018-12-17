package com.example.zzpcomputer.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.UpdateHttpThread;

/**
 * 修改密码
 */
public class UpdatePwdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);

    /**
     * 返回到登录界面
     */
        TextView textView = findViewById(R.id.backToLogin);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(UpdatePwdActivity.this, LoginActivity.class);
            startActivity(intent);

        });
        Button button = findViewById(R.id.updateOk);
        EditText e1 = findViewById(R.id.yourNname);
        EditText e2 = findViewById(R.id.OldPwd);
        EditText e3 = findViewById(R.id.newPwd);
        EditText e4 = findViewById(R.id.newPwdSure);
        button.setOnClickListener(v -> {
            if (!(e1.getText().toString().equals("") && e2.getText().toString().equals("")
                    && e3.getText().toString().equals("") && e4.getText().toString().equals(""))) {
                if (!(e3.getText().toString().equals(e4.getText().toString()))) {
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                UpdateHttpThread updatePwdActivity = new UpdateHttpThread(e1.getText().toString(), e2.getText().toString(), e3.getText().toString());
                updatePwdActivity.start();
                try {
                    updatePwdActivity.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (updatePwdActivity.isOk()) {
                    Toast.makeText(UpdatePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdatePwdActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdatePwdActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
