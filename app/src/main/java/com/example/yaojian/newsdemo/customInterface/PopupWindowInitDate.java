package com.example.yaojian.newsdemo.customInterface;

import android.view.View;

/**
 * Created by yaojian on 2018/1/30.
 */

public interface PopupWindowInitDate<T> {

    View getView();

    void initData(T data);
}
