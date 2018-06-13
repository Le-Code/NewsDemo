package com.example.yaojian.newsdemo.base;

import android.support.annotation.CallSuper;

import com.example.yaojian.newsdemo.customInterface.CustomRequestCallback;
import com.example.yaojian.newsdemo.fragment.IndexFragment;
import com.example.yaojian.newsdemo.util.NetUtil;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by yaojian on 2018/1/27.
 */

public class BaseSubscriber<T> extends Subscriber<T> {

    private CustomRequestCallback<T> callback;

    public BaseSubscriber(CustomRequestCallback<T> callback){
        this.callback = callback;
    }

    //CallSuper 如果你提供了 api 给别人用，但是这个 api 必须要先调用父类方法才能正确执行
    @CallSuper
    @Override
    public void onCompleted() {
        if (callback!=null)
            callback.requestComplete();
    }

    @CallSuper
    @Override
    public void onError(Throwable e) {
        if (callback != null) {
            String errorMsg = "";
            if (e instanceof HttpException) {
                switch (((HttpException) e).code()) {
                    case 403:
                        errorMsg = "没有权限访问此链接！";
                        break;
                    case 504:
                        if (!NetUtil.isConnected(BaseApplication.getContext())) {
                            errorMsg = "没有联网哦！";
                        } else {
                            errorMsg = "网络连接超时！";
                        }
                        break;
                    default:
                        errorMsg = ((HttpException) e).message();
                        break;
                }
            } else if (e instanceof UnknownHostException) {
                errorMsg = "不知名主机！";
            } else if (e instanceof SocketTimeoutException) {
                errorMsg = "网络连接超时！";
            }else if (e instanceof JsonMappingException){
                errorMsg = "未知异常！";
            }
            callback.onFailure(errorMsg);
        }
    }

    @CallSuper
    @Override
    public void onNext(T t) {
        if (callback!=null)
            callback.onSuccess(t);
    }

    @Override
    public void onStart() {
        if (callback!=null)
            callback.beforeRequest();
    }
}
