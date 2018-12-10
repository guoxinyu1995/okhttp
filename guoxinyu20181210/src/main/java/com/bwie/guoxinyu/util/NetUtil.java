package com.bwie.guoxinyu.util;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.telecom.Call;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {
    private static NetUtil intance;
    private Gson gson;
    public NetUtil() {
        gson = new Gson();
    }

    public static NetUtil getIntance() {
        if (intance == null){
            intance = new NetUtil();
        }
        return intance;
    }
    //执行网络请求返回String
    public String getRequest(String strUrl){
        String result = "";
        try {
            //定义URL地址
            URL url = new URL(strUrl);
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            urlConnection.setRequestMethod("GET");
            //设置超时
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            //获取请求吗
            int responseCode = urlConnection.getResponseCode();
            if(responseCode == 200){
                result = stream2String(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    //将字节流转换为字符流
    private String stream2String(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        for(String tmp = br.readLine();tmp!=null;tmp = br.readLine()){
            builder.append(tmp);
        }
        return builder.toString();
    }
    //执行网络请求返回bean
    public <E> E getRequest(String strUrl,Class clazz){
        return (E) gson.fromJson(getRequest(strUrl),clazz);
    }
    //定义接口
    public interface CallBack<E>{
        void onSuccess(E e);
    }
    //异步请求
    @SuppressLint("StaticFieldLeak")
    public void getRequest(String strUrl, final Class clazz, final CallBack callBack){
        new AsyncTask<String,Void,Object>(){
            @Override
            protected Object doInBackground(String... strings) {
                return getRequest(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callBack.onSuccess(o);
            }
        }.execute(strUrl);
    }
}
