package com.example.yaojian.newsdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.RippleDrawable;
import android.sax.EndElementListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yaojian.newsdemo.base.BaseFragment;
import com.example.yaojian.newsdemo.customInterface.GestureCallBackForPopup;
import com.example.yaojian.newsdemo.fragment.FilmFragment;
import com.example.yaojian.newsdemo.fragment.IndexFragment;
import com.example.yaojian.newsdemo.fragment.MineFragment;
import com.example.yaojian.newsdemo.popup.PopupWindowManager;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup radio_group;

    private List<BaseFragment> fragment_list;

    private FragmentManager mFragmentManager;

    private RelativeLayout layout_showOther;

    private LinearLayout layout_container;

    private int startY;
    private int startX;

    /**
     * 按钮组的高度
     */
    private int height;

    /**
     * 判断popupWindow是否还在显示
     */
    private boolean isShowPopup;

    /**
     * 判断是否显示按钮组
     */
    private boolean isShowButton;
    private boolean tmpShowBbutton;

    /**
     * 判断popupWindow是否全屏显示
     */
    private boolean isOpen;

    /**
     * 手势返回接口
     */
    private GestureCallBackForPopup callBackForPopup;
    /**
     * 按钮组的布局属性
     */
    private RelativeLayout.LayoutParams params;

    private Button btn_share;
    private Button btn_close;
    private Button btn_open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initFragment();
        changeFragment(0);

    }

    private void initView() {
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        layout_showOther = (RelativeLayout) findViewById(R.id.layout_showOther);
        layout_container = (LinearLayout) findViewById(R.id.layout_container);
        btn_close = (Button) findViewById(R.id.btn_close);
        btn_open = (Button) findViewById(R.id.btn_open);
        btn_share = (Button) findViewById(R.id.btn_share);
    }

    private void initEvent() {
        radio_group.setOnCheckedChangeListener(this);
        btn_open.setOnClickListener(this);
        btn_share.setOnClickListener(this);
        btn_close.setOnClickListener(this);
    }

    private void initFragment() {
        fragment_list = new ArrayList<>();
        fragment_list.add(new IndexFragment());
        fragment_list.add(new FilmFragment());
        fragment_list.add(new MineFragment());
        mFragmentManager = getSupportFragmentManager();
    }

    public void changeFragment(int position) {
        BaseFragment fragment = fragment_list.get(position);
        mFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        fragment.inirDate();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radio_index:
                changeFragment(0);
                break;
            case R.id.radio_film:
                changeFragment(1);
                break;
            case R.id.radio_mine:
                changeFragment(2);
                break;
            default:
                break;
        }
    }

    public void setBackground(Bitmap bitmap) {
        if (bitmap == null) {
            layout_showOther.setVisibility(View.GONE);
            isShowPopup = false;
        } else {
            layout_showOther.setVisibility(View.VISIBLE);
            hideContainer();
            layout_showOther.setBackgroundDrawable(new BitmapDrawable(bitmap));
            isShowPopup = true;
        }
    }

    private void hideContainer() {
        layout_container.measure(0, 0);
        height = layout_container.getMeasuredHeight();
        params = (RelativeLayout.LayoutParams) layout_container.getLayoutParams();
        params.bottomMargin = -height;
        layout_container.setLayoutParams(params);
    }

    private void showButtonByStep(int pixel) {
        if (params.bottomMargin >= 0) {
            tmpShowBbutton = true;
            return;
        } else if (params.bottomMargin + pixel >= 0) {
            params.bottomMargin = 0;
            tmpShowBbutton = true;
        } else {
            params.bottomMargin += pixel;
            tmpShowBbutton = false;
        }
        layout_container.setLayoutParams(params);
    }


    private void closeButtonByStep(int pixel) {
        if (params.bottomMargin <= -height) {
            return;
        } else if (params.bottomMargin + pixel <= -height) {
            params.bottomMargin = -height;
        } else {
            params.bottomMargin += pixel;
        }
        tmpShowBbutton = false;
        layout_container.setLayoutParams(params);
    }

    public void setCallBackForPopup(GestureCallBackForPopup callBack) {
        callBackForPopup = callBack;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getY();
                startX = (int) ev.getX();
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                if (isShowPopup && !isShowButton) {//窗口在按钮组不在
                    int endY = (int) ev.getY();
                    if (startY - endY > 0) {
                        showButtonByStep(startY - endY);
                    } else {
                        closeButtonByStep(startY - endY);
                    }
                    startY = endY;
                    return true;
                }
                if (isOpen) {
                    int endX = (int) ev.getX();
                    if (endX > startX) {
                        if (callBackForPopup != null) {
                            callBackForPopup.close();
                            setBackground(null);
                            isOpen = false;
                        }
                    }
                    return true;
                }
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_UP:
                isShowButton = tmpShowBbutton ? true : false;
                if (isShowPopup && !isShowButton) {
                    if (callBackForPopup != null) {
                        callBackForPopup.close();
                        setBackground(null);
                    }
                    return true;
                }
                return super.dispatchTouchEvent(ev);
            default:
                return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                setBackground(null);
                isShowButton = false;
                tmpShowBbutton = false;
                if (callBackForPopup != null)
                    callBackForPopup.close();
                break;
            case R.id.btn_open:
                if (callBackForPopup != null) {
                    isOpen = true;
                    setBackground(null);
                    callBackForPopup.open();
                }
                break;
            case R.id.btn_share:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (isShowPopup)
                exit();
            else
                readyMove();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isExit = false;

    private void readyMove() {
        if (!isExit){
            Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
            isExit = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        isExit = false;
                    }
                }
            }).start();
        }else{
            System.exit(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        exit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        exit();
    }

    private void exit() {
        if (callBackForPopup != null)
            callBackForPopup.close();
        setBackground(null);
        isShowButton = false;
        tmpShowBbutton = false;
    }
}
