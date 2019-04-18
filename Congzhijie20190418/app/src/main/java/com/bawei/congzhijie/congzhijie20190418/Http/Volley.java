package com.bawei.congzhijie.congzhijie20190418.Http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bawei.congzhijie.congzhijie20190418.MyApplication;

public class Volley {
    //单例模式
    private static Volley volley;
    private Volley(){};
    public static Volley getInstance(){
        if(volley==null){
            synchronized (Volley.class){
                if(volley==null){

                    volley = new Volley();
                }
            }

        }
         return volley;
    }
    //定义接口
    public interface CallBack{
        //成功失败的方法
        void onSuccess(String data);
        void onFail(VolleyError error);

    }
    //判断网络
    public boolean isNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
            return networkInfo.isAvailable();
        }

       return false;
    }
    public void doget(String url,final CallBack callBack ){
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFail(error);

            }
        });
        request.setTag("TestGet");
        MyApplication.requestQueue().add(request);

    }
}
