package com.bwie.guoxinyu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.guoxinyu.api.Apis;
import com.bwie.guoxinyu.R;
import com.bwie.guoxinyu.bean.LoginBean;
import com.bwie.guoxinyu.presenter.PresenderImpl;
import com.bwie.guoxinyu.util.IsNotNull;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements Iview{

    private PresenderImpl presender;
    private EditText name;
    private EditText password;
    private Button register;
    //private String url = "http://www.zhaoapi.cn/user/reg?mobile=%s&password=%s";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presender = new PresenderImpl(this);
        //获取资源id
        name = findViewById(R.id.ed_name);
        password = findViewById(R.id.ed_password);
        register = findViewById(R.id.register);
        //注册按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                String names = name.getText().toString();
                String passwoords = password.getText().toString();
                if(IsNotNull.isNot(names,passwoords)){
                    //get请求的方法
                    //presender.startRequest(String.format(Apis.URL_REGISTER,names,passwoords),null,LoginBean.class);
                    //post请求的方法
                    Map<String,String> pamase = new HashMap<>();
                    pamase.put("mobile",names);
                    pamase.put("password",passwoords);
                    presender.startRequestPost(Apis.URL_REGISTER_POST,pamase,LoginBean.class);
                }else{
                    Toast.makeText(RegisterActivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void getRequest(Object o) {
        if(o instanceof LoginBean){
            LoginBean bean = (LoginBean) o;
            if(bean==null || bean.isSuccess()){
                Toast.makeText(RegisterActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(RegisterActivity.this,"天呢！用户已注册",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
