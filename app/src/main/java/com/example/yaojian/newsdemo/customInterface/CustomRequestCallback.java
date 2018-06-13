package com.example.yaojian.newsdemo.customInterface;

/**
 * Created by yaojian on 2018/1/27.
 * 网络请求监听的接口
 */

public interface CustomRequestCallback<T> {

    /**
     * 请求网络之前调用
     */
    void beforeRequest();

    /**
     * 网络请求成功调用
     * @param data 返回数据
     */
    void onSuccess(T data);

    /**
     * 网络请求失败调用
     * @param msg 失败信息
     */
    void onFailure(String msg);

    /**
     * 网络请求结束以后调用
     */
    void requestComplete();
}
