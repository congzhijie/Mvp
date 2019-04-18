package com.bawei.congzhijie.congzhijie20190418.model;

import android.content.Context;

public interface Imodel {
    //定义方法
    void RequestData(Context context,CallBack callBack);
    //定义接口
    interface CallBack{
        //定义成功失败的方法
        void onSuccess(String data);
        void onFail(Exception e);

    }
}
