package com.example.yaojian.newsdemo.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.yaojian.newsdemo.R;
import com.example.yaojian.newsdemo.adapter.TabNewsAdapter;
import com.example.yaojian.newsdemo.base.BaseFragment;
import com.example.yaojian.newsdemo.base.BasePager;
import com.example.yaojian.newsdemo.bean.NewsChannel;
import com.example.yaojian.newsdemo.ui.news.TabNewsPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaojian on 2018/1/26.
 */

public class IndexFragment extends BaseFragment {

    private ImageView img_webBrowser_title;
    private TabLayout index_tab;
    private ViewPager vp_index_news;
    private List<String> tabIndicators;
    private List<BasePager>tabPagers;
    private TabNewsAdapter mAdapter;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_inde,null);
        img_webBrowser_title = view.findViewById(R.id.img_webBrowser_title);
        index_tab = view.findViewById(R.id.index_tab);
        vp_index_news = view.findViewById(R.id.vp_index_news);
        initContent();
        initTab();
        return view;
    }

    private void initTab() {
        //将TabLayout和viewPager绑定在一起
        index_tab.setupWithViewPager(vp_index_news);
    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        NewsChannel channel = new NewsChannel("头条","headline","T1348647909107");
        NewsChannel channel2 = new NewsChannel("科技","list","T1348649580692");
        NewsChannel channel3 = new NewsChannel("娱乐","list","T1348648517839");
        tabIndicators.add(channel.getNc_name());tabIndicators.add(channel2.getNc_name());tabIndicators.add(channel3.getNc_name());
        tabPagers = new ArrayList<>();
        tabPagers.add(new TabNewsPager(mActivity,channel.getNc_type(),channel.getNc_id()));
        tabPagers.add(new TabNewsPager(mActivity,channel2.getNc_type(),channel2.getNc_id()));
        tabPagers.add(new TabNewsPager(mActivity,channel3.getNc_type(),channel3.getNc_id()));
        mAdapter = new TabNewsAdapter(mActivity,tabPagers,tabIndicators);
        vp_index_news.setAdapter(mAdapter);
    }
}
