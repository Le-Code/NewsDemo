package com.example.yaojian.newsdemo.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yaojian on 2018/1/30.
 */

public class DataLoadType {
    public static final int TYPE_REFRESH_SUCCESS = 1;

    public static final int TYPE_REFRESH_ERROR = 2;

    public static final int TYPE_LOADMORE_SUCCESS = 3;

    public static final int TYPE_LOADMORE_ERROR = 4;


    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({TYPE_LOADMORE_ERROR,TYPE_LOADMORE_SUCCESS,TYPE_REFRESH_SUCCESS,TYPE_REFRESH_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface dataLoadTypeChecker{

    }
}
