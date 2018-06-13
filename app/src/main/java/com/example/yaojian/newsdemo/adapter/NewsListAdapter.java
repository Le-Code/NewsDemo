package com.example.yaojian.newsdemo.adapter;

import android.app.Activity;
import android.gesture.Prediction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yaojian.newsdemo.R;
import com.example.yaojian.newsdemo.base.BaseRecyclerViewHolder;
import com.example.yaojian.newsdemo.bean.News;
import com.example.yaojian.newsdemo.customInterface.CustomClickListener;
import com.example.yaojian.newsdemo.customInterface.LoadMoreListener;

import java.util.List;

/**
 * Created by yaojian on 2018/1/27.
 * 每一个tab下的新闻列表适配器
 */

public class NewsListAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private Activity mActivity;

    private List<News>newsList;

    private CustomClickListener itemListener;

    private LoadMoreListener loadMoreListener;

    public void setLoadMoreListener(LoadMoreListener listener){
        loadMoreListener = listener;
    }

    private boolean isLoading;//是否正在加载更多标志

    private final static int LOAD_MORE = 1;
    private final static int LOAD_MORE_ERROR = 2;

    private LayoutInflater inflater;

    public NewsListAdapter(Activity mActivity){
        this.mActivity = mActivity;
        inflater = LayoutInflater.from(mActivity);
    }

    public void setListener(CustomClickListener listener){
        this.itemListener = listener;
    }

    public void setNewsList(List<News>newsList){
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    public void addNewList(List<News>list){
        newsList.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==LOAD_MORE){
            BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mActivity,
                    inflater.inflate(R.layout.load_more,parent,false));
            return holder;
        }else{
            BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mActivity,
                    inflater.inflate(R.layout.news_list_item,parent,false));
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, final int position) {
        if (getItemViewType(position) == LOAD_MORE) {

        } else{
            final News news = newsList.get(position);
            holder.setText(R.id.tv_news_title, news.getTitleName())
                    .setImage(R.id.img_news_title, news.getImgsrc())
                    .setText(R.id.tv_pTime, news.getPtTime());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemListener!=null)
                        itemListener.click(view,newsList.get(position));
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (itemListener!=null)
                        itemListener.longClick(view,newsList.get(position));
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==getItemCount()-1){
            return LOAD_MORE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return newsList==null?0:newsList.size()+1;
    }
}
