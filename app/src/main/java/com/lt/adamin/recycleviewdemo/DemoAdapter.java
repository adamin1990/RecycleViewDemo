package com.lt.adamin.recycleviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by adamin on 2014/11/26.
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

    public DemoAdapter(String[] picUrls) {
        this.picUrls = picUrls;
    }

    String[] picUrls;
    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test,parent,false);

        return new DemoViewHolder(view) ;
    }

    /**
     * 点击事件接口
     */
public interface  OnItemClickListener{
    void OnItemClick(View view,int position);
}
    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickListener mOnItemClickLitener){
        this.mOnItemClickLitener=mOnItemClickLitener;
    }
    @Override
    public void onBindViewHolder(final DemoViewHolder holder, final int position) {
        //数据绑定
        ImageLoader.getInstance().displayImage(picUrls[position],holder.imageView);
        holder.textView.setText(picUrls[position]);
        if(mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.OnItemClick(holder.itemView,position);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        // 返回数据有多少条
        if (null == picUrls) {
            return 0;
        }
        return picUrls.length;
    }

    public static class DemoViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;


        public DemoViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imavPic);
            textView=(TextView)itemView.findViewById(R.id.tvUrl);
        }
    }
}
