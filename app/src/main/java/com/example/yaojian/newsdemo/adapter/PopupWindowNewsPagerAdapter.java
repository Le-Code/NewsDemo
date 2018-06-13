package com.example.yaojian.newsdemo.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.yaojian.newsdemo.base.BasePopupPager;

import java.util.List;

/**
 * Created by yaojian on 2018/1/31.
 */

public class PopupWindowNewsPagerAdapter extends PagerAdapter {

    private List<BasePopupPager> pagers;
    private Activity mActivity;

    public PopupWindowNewsPagerAdapter(Activity mActivity,List<BasePopupPager> pagers){
        this.mActivity = mActivity;
        this.pagers = pagers;
    }

    @Override
    public int getCount() {
        return pagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePopupPager pager = pagers.get(position);
        container.addView(pager.rootView);
        pager.initDate();
        return pager.rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
