package com.example.yaojian.newsdemo.customView;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.yaojian.newsdemo.R;
import com.example.yaojian.newsdemo.customInterface.LoadMoreListener;

/**
 * Created by yaojian on 2018/1/30.
 */

public class CustomRefreshRecyclerView extends RecyclerView {

    private LoadMoreListener loadMoreListener;

    private Context mContext;

    public CustomRefreshRecyclerView(Context context) {
        super(context,null);
    }

    public CustomRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        //判断是当前layoutManager是否为LinearLayoutManager
        // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            //获取最后一个可见view的位置
            int lastItemPosition = linearManager.findLastVisibleItemPosition();
            if (state==SCROLL_STATE_IDLE){
                int n = getAdapter().getItemCount();
                if (lastItemPosition==n-1){
                    if (loadMoreListener!=null)
                        loadMoreListener.loadMore();
                }
            }
        }
    }
}
