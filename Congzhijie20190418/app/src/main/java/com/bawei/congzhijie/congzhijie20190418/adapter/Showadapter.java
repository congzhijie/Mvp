package com.bawei.congzhijie.congzhijie20190418.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bawei.congzhijie.congzhijie20190418.Api;
import com.bawei.congzhijie.congzhijie20190418.Http.Volley;
import com.bawei.congzhijie.congzhijie20190418.R;
import com.bawei.congzhijie.congzhijie20190418.bean.BjsonBean;
import com.bawei.congzhijie.congzhijie20190418.bean.JsonBean;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class Showadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull

    Context context;
    JsonBean.ResultBean result;
    private Handler handler = new Handler();

    public Showadapter(@NonNull Context context, JsonBean.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    private int TYPE1 = 0;
    private int TYPE2 = 1;
    private int TYPE3 = 2;
    private int TYPE4 = 3;
    private View view;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE1;
        } else if (position == 1) {
            return TYPE2;
        } else if (position == 2) {
            return TYPE3;
        } else {
            return TYPE4;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        if (type == TYPE1) {
            view = LayoutInflater.from(context).inflate(R.layout.banner, viewGroup, false);
            return new XbannerViewHolder(view);
        } else if (type == TYPE2) {
            view = LayoutInflater.from(context).inflate(R.layout.rxxp, viewGroup, false);
            return new RxxpViewHolder(view);

        } else if (type == TYPE3) {
            view = LayoutInflater.from(context).inflate(R.layout.mlss, viewGroup, false);
            return new MlssViewHolder(view);

        }else {

            view = LayoutInflater.from(context).inflate(R.layout.pzsh, viewGroup, false);
            return new PzshViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if(type==TYPE1){
            Volley.getInstance().doget(Api.bannerurl, new Volley.CallBack() {
                @Override
                public void onSuccess(final String data) {
                    Gson gson = new Gson();
                    BjsonBean bjsonBean = gson.fromJson(data, BjsonBean.class);
                    final List<BjsonBean.ResultBean> result = bjsonBean.getResult();
                    ArrayList<String> list = new ArrayList<>();
                    for (int i=0;i<bjsonBean.getResult().size();i++){
                        list.add(bjsonBean.getResult().get(i).getImageUrl());

                    }
                    XbannerViewHolder xbannerViewHolder = (XbannerViewHolder) viewHolder;
                    xbannerViewHolder.banner.setData(list,null);
                    xbannerViewHolder.banner.loadImage(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {
                            Glide.with(context).load(result.get(position).getImageUrl()).into((ImageView) view);
                        }
                    });
                    xbannerViewHolder.banner.setPageTransformer(Transformer.Accordion);
                    xbannerViewHolder.banner.setPageChangeDuration(1000);

                }

                @Override
                public void onFail(VolleyError error) {

                }


            });

        }else if(type==TYPE2){
            RxxpViewHolder rxxpViewHolder = (RxxpViewHolder) viewHolder;
            rxxpViewHolder.text_title.setText(result.getRxxp().getName());
            //创建适配器
            Rxxpadapter rxxpadapter = new Rxxpadapter(context,result);
            //设置布局管理器
            rxxpViewHolder.rxxp_recy.setLayoutManager(new GridLayoutManager(context,3));
            //设置适配器
            rxxpViewHolder.rxxp_recy.setAdapter(rxxpadapter);


        }else if(type==TYPE3){

            MlssViewHolder mlssViewHolder = (MlssViewHolder) viewHolder;
            mlssViewHolder.mlss_text.setText(result.getMlss().getName());
            //创建适配器
            Mlssadapter mlssadapter = new Mlssadapter(context,result);
            //设置布局管理器
            mlssViewHolder.mlss_recy.setLayoutManager(new LinearLayoutManager(context));
            //设置适配器
            mlssViewHolder.mlss_recy.setAdapter(mlssadapter);



        }else {

            PzshViewHolder pzshViewHolder = (PzshViewHolder) viewHolder;
            pzshViewHolder.pzsh_text.setText(result.getPzsh().getName());
            //创建适配器
            Pzshadapter pzshadapter = new Pzshadapter(context,result);
            //设置布局管理器
            pzshViewHolder.pzsh_recy.setLayoutManager(new GridLayoutManager(context,2));
            //设置适配器
            pzshViewHolder.pzsh_recy.setAdapter(pzshadapter);



        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
    public class XbannerViewHolder extends RecyclerView.ViewHolder {

        private final XBanner banner;

        public XbannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
    public class RxxpViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView rxxp_recy;
        private final TextView text_title;

        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            rxxp_recy = itemView.findViewById(R.id.rxxp_recy);
            text_title = itemView.findViewById(R.id.text_title);

        }
    }
    public class MlssViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView mlss_recy;
        private final TextView mlss_text;


        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            mlss_recy = itemView.findViewById(R.id.mlss_recy);
            mlss_text = itemView.findViewById(R.id.mlss_text);
        }
    }
    public class PzshViewHolder extends RecyclerView.ViewHolder {

        private final TextView pzsh_text;
        private final RecyclerView pzsh_recy;

        public PzshViewHolder(@NonNull View itemView) {
            super(itemView);
            pzsh_text = itemView.findViewById(R.id.pzsh_text);
            pzsh_recy = itemView.findViewById(R.id.pzsh_recy);
        }
    }
}
