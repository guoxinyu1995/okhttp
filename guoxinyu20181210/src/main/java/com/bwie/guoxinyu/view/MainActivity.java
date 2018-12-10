package com.bwie.guoxinyu.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.guoxinyu.api.Apis;
import com.bwie.guoxinyu.R;
import com.bwie.guoxinyu.bean.LoginBean;
import com.bwie.guoxinyu.constant.Constants;
import com.bwie.guoxinyu.presenter.PresenderImpl;
import com.bwie.guoxinyu.util.IsNotNull;
import com.bwie.guoxinyu.util.SpUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Iview,View.OnClickListener {
    //private String url = "http://www.zhaoapi.cn/user/login?mobile=%s&password=%s";
    private PresenderImpl presender;
    private EditText name;
    private EditText password;
    private Button login;
    private TextView register;
    private ImageView qq;
    private String names;
    private String passwoords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presender = new PresenderImpl(this);
        initView();
    }

    private void initView() {
        //获取资源id
        name = findViewById(R.id.ed_name);
        password = findViewById(R.id.ed_password);
        login = findViewById(R.id.logion);
        register = findViewById(R.id.register);
        qq = findViewById(R.id.image_qq);
        //登录按钮
        login.setOnClickListener(this);
        //注册按钮
        register.setOnClickListener(this);
        //qq登录
        qq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //登录
            case R.id.logion:
                //获取输入框的值
                names = name.getText().toString();
                passwoords = password.getText().toString();
                if(IsNotNull.isNot(names, passwoords)){
                    //get请求调的方法
                    //presender.startRequest(String.format(Apis.URL_LOGIN, names, passwoords),null,LoginBean.class);
                    //post请求调的方法
                    Map<String,String> params = new HashMap<>();
                    params.put("mobile",names);
                    params.put("password",passwoords);
                    presender.startRequestPost(Apis.URL_LOGIN_POST,params,LoginBean.class);
                }else{
                    Toast.makeText(MainActivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
                //注册
            case R.id.register:
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
                //第三方登录
            case R.id.image_qq:
                UMShareAPI umShareAPI = UMShareAPI.get(MainActivity.this);
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Intent intent1 = new Intent(MainActivity.this,ShowActivity.class);
                        startActivity(intent1);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                break;
                default:
                    break;
        }
    }

    @Override
    public void getRequest(Object o) {
        if(o instanceof LoginBean){
            LoginBean bean = (LoginBean) o;
            if(bean==null || bean.isSuccess()){
                SpUtil.save(this,Constants.SP_KEY_UID,bean.getData().getUid());
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                startActivity(intent);
            }
        }
    }
    //第三方登录的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
