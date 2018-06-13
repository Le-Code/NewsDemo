package com.example.yaojian.newsdemo.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.yaojian.newsdemo.base.BasePager;
import com.example.yaojian.newsdemo.customInterface.LoadMoreListener;

import java.util.List;

/**
 * Created by yaojian on 2018/1/26.
 */

public class TabNewsAdapter extends PagerAdapter {

    private Activity mActivity;
    private List<BasePager> pagerList;
    private List<String>pageTitles;


    public TabNewsAdapter(Activity mActivity, List<BasePager> pagerList, List<String>pageTitles){
        this.mActivity = mActivity;
        this.pagerList = pagerList;
        this.pageTitles = pageTitles;
    }

    @Override
    public int getCount() {
        return pagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        BasePager pager = pagerList.get(position);
        container.addView(pager.mRootView);
        pager.initDate();
        return pager.mRootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 返回page的title
     * 设置标签指示器的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles.get(position);
    }
}
