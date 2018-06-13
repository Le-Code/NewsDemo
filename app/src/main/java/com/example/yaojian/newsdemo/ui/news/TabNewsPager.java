package com.example.yaojian.newsdemo.ui.news;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.MenuPopupWindow;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.yaojian.newsdemo.MainActivity;
import com.example.yaojian.newsdemo.R;
import com.example.yaojian.newsdemo.adapter.NewsListAdapter;
import com.example.yaojian.newsdemo.base.BasePager;
import com.example.yaojian.newsdemo.bean.News;
import com.example.yaojian.newsdemo.customInterface.CustomClickListener;
import com.example.yaojian.newsdemo.customInterface.GestureCallBackForPopup;
import com.example.yaojian.newsdemo.customInterface.LoadMoreListener;
import com.example.yaojian.newsdemo.customInterface.PopupWindowInitDate;
import com.example.yaojian.newsdemo.customView.CustomRefreshRecyclerView;
import com.example.yaojian.newsdemo.popup.PopupWindowManager;
import com.example.yaojian.newsdemo.popup.news.InitNewsDetail;
import com.example.yaojian.newsdemo.presenter.news.NewsListPresenterImpl;
import com.example.yaojian.newsdemo.util.DataLoadType;
import com.example.yaojian.newsdemo.util.FastBlurUtility;

import java.util.List;

import javax.crypto.Mac;

/**
 * Created by yaojian on 2018/1/26.
 */

public class TabNewsPager extends BasePager<NewsListPresenterImpl>
        implements INewsListView, CustomClickListener, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, GestureCallBackForPopup {

    private CustomRefreshRecyclerView rv_news;

    private NewsListAdapter mAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private PopupWindowManager<News> popup;

    public TabNewsPager(Activity mActivity, String type, String id) {
        super(mActivity, type, id);
        ((MainActivity) mActivity).setCallBackForPopup(this);
        popup = PopupWindowManager.getInstance(mActivity);
    }

    @Override
    protected NewsListPresenterImpl initPresenter(String type, String id) {
        return new NewsListPresenterImpl(this, type, id);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.tab_news_pager, null);
        rv_news = view.findViewById(R.id.rv_news);
        swipeRefreshLayout = view.findViewById(R.id.swipe_fresh_layout);
        initRecyclerView();
        initSwipe();
        initAdapter();
        return view;
    }

    private void initRecyclerView() {
        rv_news.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_news.setLoadMoreListener(this);
    }

    private void initAdapter() {
        mAdapter = new NewsListAdapter(mActivity);
        mAdapter.setListener(this);
        rv_news.setAdapter(mAdapter);
    }

    private void initSwipe() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.YELLOW, Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void initDate() {
        mPresenter.refresh();
    }

    @Override
    public void updateListView(List<News> data, @NonNull String errorMsg, int type) {
        switch (type) {
            case DataLoadType.TYPE_REFRESH_SUCCESS:
                mAdapter.setNewsList(data);
                swipeRefreshLayout.setRefreshing(false);
                break;
            case DataLoadType.TYPE_REFRESH_ERROR:
                showToash(errorMsg);
                swipeRefreshLayout.setRefreshing(false);
                break;
            case DataLoadType.TYPE_LOADMORE_ERROR:
                showToash(errorMsg);
                break;
            case DataLoadType.TYPE_LOADMORE_SUCCESS:
                mAdapter.addNewList(data);
            default:
                break;
        }
    }

    @Override
    public void showToash(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        showToash("开始加载");
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
        showToash("结束加载");

    }

    @Override
    public void click(View view, News news) {
        if (news.getPostid() == null) {
            showToash("新闻浏览不了");
            return;
        }
        if (!TextUtils.isEmpty(news.getDigest())) {
            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
            intent.putExtra("postid", news.getPostid());
            intent.putExtra("imgsrc", news.getImgsrc());
            mActivity.startActivity(intent);
        }
    }

    @Override
    public void longClick(View view, News news) {
        popup.setData(news);
        popup.setInitMethod(new InitNewsDetail(mActivity, R.layout.popup_news_detail));
        popup.setSize(0.95, 0.7);
        popup.show(mRootView, Gravity.TOP, 0, 100, R.style.popupWindow_anim);
        ((MainActivity) mActivity).setBackground(FastBlurUtility.getBlurBackgroundDrawer(mActivity));
    }

    @Override
    public void loadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    /**
     * 关闭popupWindow
     * 由mainActivity传递进来
     */
    @Override
    public void close() {
        if (popup != null)
            popup.close();
    }

    @Override
    public void open() {
        if (popup!=null)
            popup.open(mRootView);
    }
}
