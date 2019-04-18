package com.bawei.congzhijie.congzhijie20190418.presenter;

import android.content.Context;

import com.bawei.congzhijie.congzhijie20190418.model.Imodel;
import com.bawei.congzhijie.congzhijie20190418.model.Model;
import com.bawei.congzhijie.congzhijie20190418.view.Iview;

public class Presenter implements Ipresenter {

    private Model model;
    Iview iview;

    @Override
    public void attachView(Iview iview) {
        model = new Model();
        //实例化Model
        this.iview = iview;

    }

    @Override
    public void deteachView() {
        if(iview!=null){

            iview=null;
        }
       if(model!=null){
            model=null;
       }

       System.gc();
    }

    @Override
    public void getData(Context context) {
     model.RequestData(context, new Imodel.CallBack() {
         @Override
         public void onSuccess(String data) {
             iview.getData(data);
         }

         @Override
         public void onFail(Exception e) {

         }
     });

    }
}
