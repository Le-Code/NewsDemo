package com.example.yaojian.newsdemo.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by yaojian on 2018/1/26.
 */

public abstract class BasePager<T extends BasePresenter> {
    public Activity mActivity;
    public View mRootView;
    protected T mPresenter;

    public BasePager(Activity mActivity,@NonNull String type,@NonNull String id){
        this.mActivity = mActivity;
        mRootView = initView();
        mPresenter = initPresenter(type,id);
    }

    protected abstract T initPresenter(@NonNull String type,String id);

    protected abstract View initView();

    public void initDate(){

    }
}
