package com.example.yaojian.newsdemo.base;

import android.app.Activity;
import android.view.View;

import com.example.yaojian.newsdemo.MainActivity;
import com.example.yaojian.newsdemo.customInterface.GestureCallBackForPopup;

/**
 * Created by yaojian on 2018/1/31.
 * 用来显示再popupWindow里面的界面
 */

public abstract class BasePopupPager{

    protected Activity mActivity;
    public View rootView;

    public BasePopupPager(Activity mActivity){
        this.mActivity = mActivity;
        rootView = initView();
    }

    protected abstract View initView();

    public void initDate(){

    }
}
