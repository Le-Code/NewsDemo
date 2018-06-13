package com.example.yaojian.newsdemo.customInterface;

import android.view.View;

import com.example.yaojian.newsdemo.bean.News;

/**
 * Created by yaojian on 2018/1/28.
 */

public interface CustomClickListener {

    void click(View view,News news);

    void longClick(View view,News news);
}
