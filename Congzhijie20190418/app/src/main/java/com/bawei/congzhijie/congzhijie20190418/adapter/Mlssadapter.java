package com.bawei.congzhijie.congzhijie20190418.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.congzhijie.congzhijie20190418.R;
import com.bawei.congzhijie.congzhijie20190418.bean.JsonBean;
import com.bumptech.glide.Glide;

public class Mlssadapter extends RecyclerView.Adapter<Mlssadapter.MlssViewHolder> {
    Context context;
    JsonBean.ResultBean result;

    public Mlssadapter(Context context, JsonBean.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MlssViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item2, viewGroup, false);
        return new MlssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MlssViewHolder mlssViewHolder, int i) {
        mlssViewHolder.mlss_name.setText(result.getMlss().getCommodityList().get(i).getCommodityName());
        mlssViewHolder.mlss_price.setText(result.getMlss().getCommodityList().get(i).getPrice()+"");
        Glide.with(context).load(result.getMlss().getCommodityList().get(i).getMasterPic()).into(mlssViewHolder.mlss_img);

    }

    @Override
    public int getItemCount() {
        return result.getMlss().getCommodityList().size();
    }

    public class MlssViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mlss_img;
        private final TextView mlss_name;
        private final TextView mlss_price;

        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            mlss_img = itemView.findViewById(R.id.mlss_img);
            mlss_name = itemView.findViewById(R.id.mlss_name);
            mlss_price = itemView.findViewById(R.id.mlss_price);
        }
    }
}
