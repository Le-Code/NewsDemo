package com.example.yaojian.newsdemo.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.example.yaojian.newsdemo.R;
import com.example.yaojian.newsdemo.base.BaseFragment;

/**
 * Created by yaojian on 2018/1/26.
 */

public class MineFragment extends BaseFragment {

    @Override
    protected View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_mine,null);
        return view;
    }
}
