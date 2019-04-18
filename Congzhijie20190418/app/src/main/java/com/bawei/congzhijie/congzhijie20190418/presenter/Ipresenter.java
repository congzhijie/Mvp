package com.bawei.congzhijie.congzhijie20190418.presenter;

import android.content.Context;

import com.bawei.congzhijie.congzhijie20190418.view.Iview;

public interface Ipresenter {
    //创建三个方法
    void attachView(Iview iview);
    void deteachView();
    void getData(Context context);

}
