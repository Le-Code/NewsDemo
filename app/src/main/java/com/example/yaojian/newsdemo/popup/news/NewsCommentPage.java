package com.example.yaojian.newsdemo.popup.news;

import android.app.Activity;
import android.view.View;

import com.example.yaojian.newsdemo.R;
import com.example.yaojian.newsdemo.base.BasePopupPager;

/**
 * Created by yaojian on 2018/1/31.
 * 新闻评价页面
 */

public class NewsCommentPage extends BasePopupPager {

    public NewsCommentPage(Activity mActivity) {
        super(mActivity);
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.popup_news_detail2_vp,null);
    }
}
