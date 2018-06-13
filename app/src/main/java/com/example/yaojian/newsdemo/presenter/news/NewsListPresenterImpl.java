package com.example.yaojian.newsdemo.presenter.news;

import com.example.yaojian.newsdemo.base.BasePresenterImpl;
import com.example.yaojian.newsdemo.bean.News;
import com.example.yaojian.newsdemo.model.news.INewsListModel;
import com.example.yaojian.newsdemo.model.news.NewsListModelImpl;
import com.example.yaojian.newsdemo.ui.news.INewsListView;
import com.example.yaojian.newsdemo.util.DataLoadType;

import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by yaojian on 2018/1/27.
 */

public class NewsListPresenterImpl extends BasePresenterImpl<INewsListView,List<News>> implements INewsListPresenter{

    private INewsListModel mNewsModel;

    private boolean hasInit;
    private boolean isFresh = true;
    private String id;

    private String type;

    private int startPage;

    public NewsListPresenterImpl(INewsListView mView, String type, String id) {
        super(mView);
        mNewsModel = new NewsListModelImpl();
        this.id = id;
        this.type = type;
    }

    @Override
    public void beforeRequest() {
        if(!hasInit){
            hasInit = true;
            mView.showProgress();
        }
    }

    @Override
    public void onFailure(String msg) {
        super.onFailure(msg);
        mView.updateListView(null,msg,isFresh?DataLoadType.TYPE_REFRESH_ERROR:DataLoadType.TYPE_LOADMORE_ERROR);
    }

    @Override
    public void onSuccess(List<News> data) {
        if (data!=null) {
            startPage+=20;
            mView.updateListView(data, null,isFresh? DataLoadType.TYPE_REFRESH_SUCCESS:DataLoadType.TYPE_LOADMORE_SUCCESS);
        }
    }

    @Override
    public void refresh() {
        startPage = 0;
        isFresh = true;
        mSubscription = mNewsModel.requestNewsList(this,type,id,startPage);
    }

    @Override
    public void loadMore() {
        isFresh = false;
        mSubscription = mNewsModel.requestNewsList(this,type,id,startPage);
    }




}
