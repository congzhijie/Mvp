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

public class Pzshadapter extends RecyclerView.Adapter<Pzshadapter.PzshViewHolder> {
    Context context;
    JsonBean.ResultBean result;

    public Pzshadapter(Context context, JsonBean.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public PzshViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item3, viewGroup, false);
        return new PzshViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PzshViewHolder pzshViewHolder, int i) {
        pzshViewHolder.pzsh_name.setText(result.getPzsh().getCommodityList().get(i).getCommodityName());
        pzshViewHolder.pzsh_price.setText(result.getPzsh().getCommodityList().get(i).getPrice()+"");
        Glide.with(context).load(result.getPzsh().getCommodityList().get(i).getMasterPic()).into(pzshViewHolder.pzzh_img);

    }

    @Override
    public int getItemCount() {
        return result.getPzsh().getCommodityList().size();
    }

    public class PzshViewHolder extends RecyclerView.ViewHolder {

        private final TextView pzsh_name;
        private final TextView pzsh_price;
        private final ImageView pzzh_img;

        public PzshViewHolder(@NonNull View itemView) {
            super(itemView);
            pzsh_name = itemView.findViewById(R.id.pzsh_name);
            pzsh_price = itemView.findViewById(R.id.pzsh_price);
            pzzh_img = itemView.findViewById(R.id.pzzh_img);
        }
    }
}
