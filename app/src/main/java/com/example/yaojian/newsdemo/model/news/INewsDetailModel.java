package com.example.yaojian.newsdemo.model.news;

import com.example.yaojian.newsdemo.customInterface.CustomRequestCallback;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by yaojian on 2018/1/30.
 */

public interface INewsDetailModel<T> {
    /**
     * 请求新闻详细信息
     * @param callback 监听接口
     * @param postid 新闻详情id
     * @return
     */
    Subscription requestNewsDetail(CustomRequestCallback<T> callback, String postid);
}
