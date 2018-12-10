package com.bwie.okhttputil;

import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;

import com.bwie.guoxinyu.constant.Constants;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {
    private static OkHttpUtil instance;
    private final OkHttpClient httpClient;
    private Handler handler = new Handler(Looper.myLooper());

    /**
     * 创建构造方法
     */
    public OkHttpUtil() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * 使用构造者模式
         * 设置连接超时
         * 读取超时
         * 写超时
         * 添加拦截器
         */
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    /**
     * 创建一个单例
     */
    public static OkHttpUtil getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtil.class) {
                instance = new OkHttpUtil();
            }
        }
        return instance;
    }

    /**
     * 网络同步方法
     * 创建一个get请求
     * 设置一个路径
     * <p>
     * 通过mClient.newCall建一个Call对象
     * 调用同步请求方法
     *
     * @param url
     * @param iCallBack * @param clazz
     * @return
     * @throws IOException
     */
    public String getSynchronous(String url, Class clazz, ICallBack iCallBack) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = httpClient.newCall(request);
        Response response = call.execute();
        return stream2String(response.body().bytes());
    }

    //将流转换为字符
    private String stream2String(byte[] bytes) {
        return new String(bytes);
    }

    /**
     * 异步的get方法
     * 创建一个请求
     * 创建一个call
     * 调用异步请求
     *
     * @param url
     * @param iCallBack
     * @param clazz
     */
    public void getAsynchronization(String url, final Class clazz, final ICallBack iCallBack) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            //网络连接失败
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.failed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.success(o);
                    }
                });
            }
        });
    }

    /**
     * 创建异步post方法
     * 创建表单FormBody
     * 把我们取出的key和value对应的放进去
     * 最后，build表单，生成RequestBody
     *
     * @param url       请求地址
     * @param params
     * @param clazz
     * @param iCallBack
     */
    public void postAsynchronization(String url, Map<String, String> params, final Class clazz, final ICallBack iCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.failed(e);
                    }
                });
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.success(o);
                    }
                });
            }
        });
    }
    /**
     * 上传图片
     * 创建MultipartBody.Builder，类型为Form
     * 如果上传的是图片
     * 通过File找到图片
     * 给我们 MultipartBody.Builder添加一个表单数据
     * 第二个参数filename为上传之后，服务器保存的名称
     * 第三个参数创建requestBody,类型为表单数据
     * 第四个参数，文件
     *
     * 如果遍历发现不是文件，则保存普通键值对
     *
     * @param url
     * @param params
     * @param clazz
     * @param callBack
     */
    public void uploadImage(String url, Map<String,String> params, final Class clazz, final ICallBack iCallBack){
        OkHttpClient httpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for (Map.Entry<String,String> entry:params.entrySet()){
            if(entry.getKey().equals(Constants.MAP_KEY_UP_LOAD_IMG)){
                File file = new File(entry.getValue());
                builder.addFormDataPart(entry.getKey(),"a.png",
                        RequestBody.create(MediaType.parse("multipart/form-data"),file));
            }else{
                builder.addFormDataPart(entry.getKey(),entry.getValue());
            }
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.failed(e);
                    }
                });
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.success(o);
                    }
                });
            }
        });
    }
}
