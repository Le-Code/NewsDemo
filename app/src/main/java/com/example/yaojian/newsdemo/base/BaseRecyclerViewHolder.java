package com.example.yaojian.newsdemo.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by yaojian on 2018/1/29.
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    protected SparseArray<View>views;

    public BaseRecyclerViewHolder(Context mContext,View itemView) {
        super(itemView);
        this.mContext = mContext;
        views = new SparseArray<>();
    }

    private <T extends View> T findViewById(int id){
        View view = views.get(id);
        if (view==null){
            view = itemView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }

    public TextView getTextView(int id){
        return findViewById(id);
    }

    public ImageView getImageView(int id){
        return findViewById(id);
    }

    //
    public BaseRecyclerViewHolder setText(int id,String text){
        TextView textView = getTextView(id);
        textView.setText(text);
        return this;
    }

    public BaseRecyclerViewHolder setImage(int id,String url){
        ImageView imageView = getImageView(id);
        Glide.with(mContext).load(url).centerCrop().into(imageView);
        return this;
    }
}
