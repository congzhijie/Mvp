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

public class Rxxpadapter extends RecyclerView.Adapter<Rxxpadapter.RxxpViewHolder> {
    Context context;
    JsonBean.ResultBean result;

    public Rxxpadapter(Context context, JsonBean.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RxxpViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item1, viewGroup, false);
        return new RxxpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RxxpViewHolder rxxpViewHolder, int i) {
        rxxpViewHolder.rxxp_name.setText(result.getRxxp().getCommodityList().get(i).getCommodityName());
        rxxpViewHolder.rxxp_price.setText(result.getRxxp().getCommodityList().get(i).getPrice()+"");
        Glide.with(context).load(result.getRxxp().getCommodityList().get(i).getMasterPic()).into(rxxpViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return result.getRxxp().getCommodityList().size();
    }

    public class RxxpViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView rxxp_name;
        private final TextView rxxp_price;

        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rxxp_img);
            rxxp_name = itemView.findViewById(R.id.rxxp_name);
            rxxp_price = itemView.findViewById(R.id.rxxp_price);
        }
    }
}
