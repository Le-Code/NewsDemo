package com.example.yaojian.newsdemo.ui.news;

import android.support.annotation.NonNull;

import com.example.yaojian.newsdemo.base.BaseView;
import com.example.yaojian.newsdemo.bean.News;
import com.example.yaojian.newsdemo.util.DataLoadType;

import java.util.List;

/**
 * Created by yaojian on 2018/1/27.
 * 新闻列表视图实现接口
 */

public interface INewsListView extends BaseView {

    void updateListView(List<News>data, @NonNull String errorMsg, @DataLoadType.dataLoadTypeChecker int ytpe);
}
