package com.example.yaojian.newsdemo.customView;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yaojian on 2018/1/31.
 */

public class CustomViewGroupForPopup extends ViewGroup {

    private int startX;

    private BackListener listener;

    public CustomViewGroupForPopup(Context context) {
        this(context,null);
    }

    public CustomViewGroupForPopup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListener(BackListener listener){
        this.listener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        MarginLayoutParams params = null;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        //开始处理wrap_content,如果一个子元素都没有，就设置为0
        if (getChildCount() == 0) {
            setMeasuredDimension(0,0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //ViewGroup，宽，高都是wrap_content，根据我们的需求，宽度是子控件的宽度，高度则是所有子控件的总和
            View childOne = getChildAt(0);
            params = (MarginLayoutParams) childOne.getLayoutParams();
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(childWidth + params.leftMargin + params.rightMargin,
                    childHeight * getChildCount() + params.topMargin + params.bottomMargin);

        } else if (widthMode == MeasureSpec.AT_MOST) {
            //ViewGroup的宽度为wrap_content,则高度不需要管，宽度还是自控件的宽度
            View childOne = getChildAt(0);
            params = (MarginLayoutParams) childOne.getLayoutParams();
            int childWidth = childOne.getMeasuredWidth();
            setMeasuredDimension(childWidth + params.leftMargin + params.rightMargin,heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //ViewGroup的高度为wrap_content,则宽度不需要管，高度为子View的高度和
            View childOne = getChildAt(0);
            params = (MarginLayoutParams) childOne.getLayoutParams();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(widthSize, childHeight * getChildCount() + params.topMargin + params.bottomMargin);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int height = 0;
        int count = getChildCount();
        View child;
        child = getChildAt(0);
        MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
        int c1 = params.leftMargin;
        int c2 = params.topMargin;
        int c3 = c1 + child.getMeasuredWidth();
        int c4 = c2 + child.getMeasuredHeight();
        child.layout(c1,c2, c3,c4);
        height = c4 + params.bottomMargin;
        for(int j = 1 ;j < count;j++) {
            child = getChildAt(j);
            child.layout(c1, height, c3, height + child.getMeasuredHeight());
            height += child.getMeasuredHeight();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                if (startX<=10){
                    return true;
                }
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                if (startX<=10) {
                    int endX = (int) ev.getX();
                    if (endX - startX >= 50) {
                        if (listener != null)
                            listener.back();
                    }
                }
                return super.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public interface BackListener{
        void back();
    }
}
