package com.example.yaojian.newsdemo.model.news;

import com.example.yaojian.newsdemo.base.BaseSubscriber;
import com.example.yaojian.newsdemo.bean.News;
import com.example.yaojian.newsdemo.customInterface.CustomRequestCallback;
import com.example.yaojian.newsdemo.http.HostType;
import com.example.yaojian.newsdemo.http.manager.RetrofitManager;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by yaojian on 2018/1/27.
 */

public class NewsListModelImpl implements INewsListModel {

    @Override
    public Subscription requestNewsList(CustomRequestCallback<List<News>> callback,String type,final String id,int startPage) {
        return RetrofitManager.getInstance(HostType.NETEASE_NEWS_VIDEO).getNewsListObservable(type,id,startPage)
                .flatMap(new Func1<Map<String,List<News>>, Observable<News>>() {
                    @Override
                    public Observable<News> call(Map<String, List<News>> stringListMap) {
                        return Observable.from(stringListMap.get(id));
                    }
                }).toSortedList(new Func2<News, News, Integer>() {
                    //按照时间先后顺序
                    @Override
                    public Integer call(News news, News news2) {
                        return news2.getPtTime().compareTo(news.getPtTime());
                    }
                }).subscribe(new BaseSubscriber(callback));
    }
}
