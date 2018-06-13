package com.example.yaojian.newsdemo.http.service;

import com.example.yaojian.newsdemo.bean.News;
import com.example.yaojian.newsdemo.bean.NewsDetail;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by yaojian on 2018/1/27.
 */

public interface NewsService {

    /**
     * 请求新闻列表 例子：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     *
     * @param type      新闻类别：headline为头条,local为北京本地,fangchan为房产,list为其他
     * @param id        新闻类别id
     * @param startPage 开始的页码
     * @return 被观察对象
     */
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    rx.Observable<Map<String,List<News>>> getNewsList(
            @Path("type") String type,@Path("id") String id,@Path("startPage") int startPage);

    /**
     * 请求新闻详情 例子 http://c.m.163.com/nc/article/BG6CGA9M00264N2N/full.html
     * @param postid 新闻详情的id
     * @return 被观察的对象
     */
    @GET("nc/article/{postid}/full.html")
    rx.Observable<Map<String,NewsDetail>> getNewsDetail(@Path("postid") String postid);
}
