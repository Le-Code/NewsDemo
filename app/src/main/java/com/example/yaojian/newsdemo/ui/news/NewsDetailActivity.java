package com.example.yaojian.newsdemo.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yaojian.newsdemo.R;
import com.example.yaojian.newsdemo.bean.NewsDetail;
import com.example.yaojian.newsdemo.customView.richtext.RichText;
import com.example.yaojian.newsdemo.presenter.news.NewsDetailPresenterImpl;

/**
 * Created by yaojian on 2018/1/28.
 */

public class NewsDetailActivity extends AppCompatActivity implements INewsDetailView {

    private TextView tv_news_detail_title;
    private TextView tv_news_detail_source;
    private RichText tv_news_detail_body;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();

        NewsDetailPresenterImpl presenter = new NewsDetailPresenterImpl(this);
        presenter.onLoad(getIntent().getStringExtra("postid"));
    }

    private void initView() {
        tv_news_detail_source = (TextView) findViewById(R.id.tv_news_detail_source);
        tv_news_detail_body = (RichText) findViewById(R.id.tv_news_detail_body);
        tv_news_detail_title = (TextView) findViewById(R.id.tv_news_detail_title);
    }

    @Override
    public void initNewsDetail(NewsDetail newsDetail) {
        tv_news_detail_title.setText(newsDetail.getTitle());
        tv_news_detail_source.setText(newsDetail.getSource());
        String body = newsDetail.getBody();
        String text;
        for (NewsDetail.ImageEntity imageEntity:newsDetail.getImageList()){
            String[] pixels = imageEntity.getPixel().split("\\*");
            text = "<img src="+imageEntity.getSrc()+" width= '"+pixels[0]+"' height='"+pixels[1]+"' alt='"+imageEntity.getAlt()+"'/>";
            body = body.replace(imageEntity.getRef(),text);
        }
        Log.e("tag",body);
        tv_news_detail_body.setRichText(body);
    }

    @Override
    public void showToash(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
