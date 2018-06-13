package com.example.yaojian.newsdemo.ui.news;

import com.example.yaojian.newsdemo.base.BaseView;
import com.example.yaojian.newsdemo.bean.NewsDetail;

/**
 * Created by yaojian on 2018/1/30.
 */

public interface INewsDetailView extends BaseView {
    /**
     * 请求新闻详情
     * @param newsDetail 返回的新闻详情类
     */
    void initNewsDetail(NewsDetail newsDetail);
}
