package com.example.yaojian.newsdemo.presenter.news;

import com.example.yaojian.newsdemo.base.BasePresenterImpl;
import com.example.yaojian.newsdemo.bean.NewsDetail;
import com.example.yaojian.newsdemo.model.news.INewsDetailModel;
import com.example.yaojian.newsdemo.model.news.NewsDetailModelImpl;
import com.example.yaojian.newsdemo.ui.news.INewsDetailView;

/**
 * Created by yaojian on 2018/1/30.
 */

public class NewsDetailPresenterImpl extends BasePresenterImpl<INewsDetailView,NewsDetail> implements INewsDetailPresenter {

    private INewsDetailModel<NewsDetail> model;

    public NewsDetailPresenterImpl(INewsDetailView mView) {
        super(mView);
        model = new NewsDetailModelImpl();
    }

    @Override
    public void onSuccess(NewsDetail data) {
        super.onSuccess(data);
        mView.initNewsDetail(data);
    }

    @Override
    public void onFailure(String msg) {
        super.onFailure(msg);
        mView.showToash(msg);
    }

    @Override
    public void onLoad(String postid) {
        mSubscription = model.requestNewsDetail(this,postid);
    }
}
