package com.example.yaojian.newsdemo.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by yaojian on 2018/1/28.
 */

public class CustomWebView extends WebView {

    public CustomWebView(Context context) {
        this(context,null);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
