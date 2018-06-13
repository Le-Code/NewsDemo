package com.example.yaojian.newsdemo.model.news;

import com.example.yaojian.newsdemo.bean.News;
import com.example.yaojian.newsdemo.customInterface.CustomRequestCallback;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by yaojian on 2018/1/27.
 * 新闻请求列表model接口
 */

public interface INewsListModel {

    /**
     * 请求新闻列表
     * @param callback
     * @return
     */
    Subscription requestNewsList(CustomRequestCallback<List<News>> callback,String type,String id,int startPage);

}
