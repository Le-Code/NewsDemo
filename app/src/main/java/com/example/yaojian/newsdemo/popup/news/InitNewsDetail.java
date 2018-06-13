package com.example.yaojian.newsdemo.popup.news;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yaojian.newsdemo.R;
import com.example.yaojian.newsdemo.adapter.PopupWindowNewsPagerAdapter;
import com.example.yaojian.newsdemo.base.BasePopupPager;
import com.example.yaojian.newsdemo.bean.News;
import com.example.yaojian.newsdemo.bean.NewsDetail;
import com.example.yaojian.newsdemo.customInterface.PopupWindowInitDate;
import com.example.yaojian.newsdemo.customView.CustomViewGroupForPopup;
import com.example.yaojian.newsdemo.presenter.news.NewsDetailPresenterImpl;
import com.example.yaojian.newsdemo.ui.news.INewsDetailView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaojian on 2018/1/30.
 */

public class InitNewsDetail implements PopupWindowInitDate<News>,INewsDetailView, CustomViewGroupForPopup.BackListener {

    private Activity mActivity;
    private View rootView;
    private ViewPager vp_news_detail_popup;

    private List<BasePopupPager>pagers;

    private PopupWindowNewsPagerAdapter mAdapter;

    private CustomViewGroupForPopup popup_vp_container;

    private CustomCallBack callBack;

    public InitNewsDetail(Activity mActivity,int layoutId){
        this.mActivity = mActivity;
        rootView = initView(layoutId);
    }

    private View initView(int layoutId) {
        View view = View.inflate(mActivity,layoutId,null);
        vp_news_detail_popup = view.findViewById(R.id.vp_news_detail_popup);
        popup_vp_container = view.findViewById(R.id.popup_vp_container);
        popup_vp_container.setListener(this);
        return view;
    }

    @Override
    public View getView() {
        return rootView;
    }

    public void setCallBack(CustomCallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public void initData(News news) {
        NewsDetailPresenterImpl presenter = new NewsDetailPresenterImpl(this);
        presenter.onLoad(news.getPostid());
    }

    @Override
    public void initNewsDetail(NewsDetail newsDetail) {
        pagers = new ArrayList<>();
        pagers.add(new NewsDetailPage(mActivity,newsDetail));
        pagers.add(new NewsCommentPage(mActivity));
        mAdapter = new PopupWindowNewsPagerAdapter(mActivity,pagers);
        vp_news_detail_popup.setAdapter(mAdapter);
    }

    @Override
    public void showToash(String msg) {
        Toast.makeText(mActivity,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        Toast.makeText(mActivity,"显示进度条",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        Toast.makeText(mActivity,"关闭进度条",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void back() {
        if (callBack!=null)
            callBack.back();
    }

    public interface CustomCallBack{
        void back();
    }
}
