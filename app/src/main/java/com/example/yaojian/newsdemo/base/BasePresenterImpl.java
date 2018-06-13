package com.example.yaojian.newsdemo.base;

import android.support.annotation.CallSuper;

import com.example.yaojian.newsdemo.customInterface.CustomRequestCallback;

import rx.Subscription;

/**
 * Created by yaojian on 2018/1/27.
 */

public class BasePresenterImpl<T extends BaseView,V> implements BasePresenter,CustomRequestCallback<V> {

    protected Subscription mSubscription;
    protected T mView;

    public BasePresenterImpl(T mView){
        this.mView = mView;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    @Override
    public void onSuccess(V data) {

    }

    @CallSuper
    @Override
    public void onFailure(String msg) {
        mView.hideProgress();
    }

    @Override
    public void requestComplete() {
        mView.hideProgress();
    }
}
