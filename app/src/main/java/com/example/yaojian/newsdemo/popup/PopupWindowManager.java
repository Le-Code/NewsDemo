package com.example.yaojian.newsdemo.popup;

import android.app.Activity;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.yaojian.newsdemo.customInterface.PopupWindowInitDate;
import com.example.yaojian.newsdemo.fragment.IndexFragment;
import com.example.yaojian.newsdemo.popup.news.InitNewsDetail;
import com.example.yaojian.newsdemo.util.MeasureUtil;

import javax.crypto.Mac;

/**
 * Created by yaojian on 2018/1/30.
 */

public class PopupWindowManager<T> implements InitNewsDetail.CustomCallBack {

    private Activity mActivity;

    private T data;

    private PopupWindow mWindow;

    private PopupWindowInitDate<T> mInitDate;

    private static PopupWindowManager instance;

    public static PopupWindowManager getInstance(Activity mActivity){
        if (instance==null)
            instance = new PopupWindowManager(mActivity);
        return instance;
    }

    private PopupWindowManager(Activity mActivity){
        this.mActivity = mActivity;
        mWindow = new PopupWindow(mActivity);
    }

    public void setData(T data){
        this.data = data;
    }



    public void setInitMethod(PopupWindowInitDate<T> mInitDate){
        this.mInitDate = mInitDate;
        if (mInitDate instanceof InitNewsDetail){
            ((InitNewsDetail)mInitDate).setCallBack(this);
        }
    }

   public void setSize(double ratioX,double ratioY){
        Point point = MeasureUtil.getScreenSize(mActivity);
        mWindow.setWidth((int) (point.x*ratioX));
        mWindow.setHeight((int) (point.y*ratioY));
    }

    public void show(View parent,int gravity,int offX,int offY,int animStyle){
        mWindow.setContentView(mInitDate.getView());
        mInitDate.initData(data);
        if (animStyle!=0)
            mWindow.setAnimationStyle(animStyle);
        mWindow.showAtLocation(parent,gravity,offX,offY);
    }

    public void open(View parent){
        mWindow.dismiss();
        setSize(1,1);
        mWindow.showAtLocation(parent,Gravity.CENTER,0,0);
    }

    public void close(){
        mWindow.dismiss();
    }

    @Override
    public void back() {
        if (mWindow.isShowing()){
            mWindow.dismiss();
        }
    }
}
