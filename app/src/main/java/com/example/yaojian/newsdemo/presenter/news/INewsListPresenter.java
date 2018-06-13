package com.example.yaojian.newsdemo.presenter.news;

import com.example.yaojian.newsdemo.base.BasePresenter;

/**
 * Created by yaojian on 2018/1/27.
 */

public interface INewsListPresenter{

    /**
     * 刷新
     */
    void refresh();

    /**
     * 加载更多
     */
    void loadMore();
}
