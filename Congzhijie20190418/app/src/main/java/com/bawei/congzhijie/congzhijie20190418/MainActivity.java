package com.bawei.congzhijie.congzhijie20190418;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bawei.congzhijie.congzhijie20190418.adapter.Showadapter;
import com.bawei.congzhijie.congzhijie20190418.bean.JsonBean;
import com.bawei.congzhijie.congzhijie20190418.presenter.Presenter;
import com.bawei.congzhijie.congzhijie20190418.view.Iview;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements Iview {

    private RecyclerView recy;
    private Presenter presenter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        recy = findViewById(R.id.recy);
        //实例化P层
        presenter = new Presenter();
        presenter.attachView(this);
        presenter.getData(this);


    }

    @Override
    public void getData(String data) {
        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(data, JsonBean.class);
        JsonBean.ResultBean result = jsonBean.getResult();
        //创建适配器
        Showadapter showadapter = new Showadapter(MainActivity.this,result);
        //设置布局管理器
        recy.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //设置适配器
        recy.setAdapter(showadapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deteachView();
    }
}
