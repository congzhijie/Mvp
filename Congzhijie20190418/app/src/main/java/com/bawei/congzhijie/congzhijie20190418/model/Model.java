package com.bawei.congzhijie.congzhijie20190418.model;


import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bawei.congzhijie.congzhijie20190418.Api;
import com.bawei.congzhijie.congzhijie20190418.Http.Volley;

public class Model implements Imodel{

    private boolean connectionManager;
    private Handler handler = new Handler();


    @Override
    public void RequestData(final Context context , final CallBack callBack) {

        connectionManager = Volley.getInstance().isNet(context);
        if(connectionManager){
            Volley.getInstance().doget(Api.url, new Volley.CallBack() {
                @Override
                public void onSuccess(String data) {
                    callBack.onSuccess(data);
                }

                @Override
                public void onFail(VolleyError error) {

                    callBack.onFail(error);
                }
            });

        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context,"网络开小差了",Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }).start();

        }

    }
}
