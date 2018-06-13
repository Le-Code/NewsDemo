package com.example.yaojian.newsdemo.popup.news;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.yaojian.newsdemo.R;
import com.example.yaojian.newsdemo.base.BasePopupPager;
import com.example.yaojian.newsdemo.bean.NewsDetail;
import com.example.yaojian.newsdemo.customView.richtext.RichText;

/**
 * Created by yaojian on 2018/1/31.
 */

public class NewsDetailPage extends BasePopupPager {

    private NewsDetail newsDetail;

    private TextView tv_news_detail_title_popup;
    private TextView tv_news_detail_source_popup;
    private RichText tv_news_detail_body_popup;

    public NewsDetailPage(Activity mActivity, NewsDetail newsDetail){
        super(mActivity);
        this.newsDetail = newsDetail;
    }


    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.popup_news_detail1_vp,null);
        tv_news_detail_body_popup = view.findViewById(R.id.tv_news_detail_body_popup);
        tv_news_detail_source_popup = view.findViewById(R.id.tv_news_detail_source_popup);
        tv_news_detail_title_popup = view.findViewById(R.id.tv_news_detail_title_popup);
        return view;
    }

    @Override
    public void initDate() {
        tv_news_detail_title_popup.setText(newsDetail.getTitle());
        tv_news_detail_source_popup.setText(newsDetail.getSource());
        String body = newsDetail.getBody();
        String text;
        for (NewsDetail.ImageEntity imageEntity:newsDetail.getImageList()){
            String[] pixels = imageEntity.getPixel().split("\\*");
            text = "<img src="+imageEntity.getSrc()+" width= '"+pixels[0]+"' height='"+pixels[1]+"' alt='"+imageEntity.getAlt()+"'/>";
            body = body.replace(imageEntity.getRef(),text);
        }
        tv_news_detail_body_popup.setRichText(body);
    }
}
