package com.example.yaojian.newsdemo.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by yaojian on 2018/1/27.
 */

public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
