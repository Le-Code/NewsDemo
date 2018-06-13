package com.example.yaojian.newsdemo.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.yaojian.newsdemo.R;

/**
 * Created by yaojian on 2018/1/28.
 */

public class CustomLoadWebView extends LinearLayout {

    private View mView;
    private Context mContext;

    private CustomWebView mWebView;
    private ProgressBar pb_load;

    public CustomLoadWebView(Context context) {
        this(context,null);
    }

    public CustomLoadWebView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CustomLoadWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mView = initView();
    }

    public View getView(){
        return mView;
    }

    private View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_load_web,this,true);
        mWebView = view.findViewById(R.id.web_load_view);
        pb_load = view.findViewById(R.id.pb_loadView);

        initWebView();
        return view;
    }

    private void initWebView() {
        WebSettings setting = mWebView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setPluginState(WebSettings.PluginState.ON);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pb_load.setVisibility(View.VISIBLE);
                pb_load.setMax(100);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pb_load.setVisibility(View.GONE);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb_load.setProgress(newProgress);
            }
        });
    }

    public void setLoad(String url){
        mWebView.loadUrl(url);
    }

    public void destroy(){
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }
}
