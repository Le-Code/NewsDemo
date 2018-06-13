package com.example.yaojian.newsdemo.model.news;

import com.example.yaojian.newsdemo.base.BaseSubscriber;
import com.example.yaojian.newsdemo.bean.NewsDetail;
import com.example.yaojian.newsdemo.customInterface.CustomRequestCallback;
import com.example.yaojian.newsdemo.http.HostType;
import com.example.yaojian.newsdemo.http.manager.RetrofitManager;

import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by yaojian on 2018/1/30.
 */

public class NewsDetailModelImpl implements INewsDetailModel<NewsDetail> {

    @Override
    public Subscription requestNewsDetail(CustomRequestCallback<NewsDetail> callback, final String postid) {
        return RetrofitManager.getInstance(HostType.NETEASE_NEWS_VIDEO).getNewsDetailObservable(postid)
                .map(new Func1<Map<String,NewsDetail>, NewsDetail>() {
                    @Override
                    public NewsDetail call(Map<String, NewsDetail> stringNewsDetailMap) {
                        return stringNewsDetailMap.get(postid);
                    }
                }).subscribe(new BaseSubscriber<>(callback));
    }
}
