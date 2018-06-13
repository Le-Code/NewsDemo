package com.example.yaojian.newsdemo.http.manager;

import android.util.SparseArray;

import com.example.yaojian.newsdemo.base.BaseSchedulerTransformer;
import com.example.yaojian.newsdemo.bean.News;
import com.example.yaojian.newsdemo.bean.NewsDetail;
import com.example.yaojian.newsdemo.http.Api;
import com.example.yaojian.newsdemo.http.HostType;
import com.example.yaojian.newsdemo.http.service.NewsService;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

/**
 * Created by yaojian on 2018/1/27.
 */

public class RetrofitManager {
    private static volatile OkHttpClient mOkHttpClient;

    // 管理不同HostType的单例
    private static SparseArray<RetrofitManager> mInstanceManager = new SparseArray<>(3);

    private NewsService mNewService;

    private RetrofitManager(int hostType) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.getHost(hostType)).client(getOkHttpClient()).addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        mNewService = retrofit.create(NewsService.class);
    }

    /**
     * 获取单例
     *
     * @param hostType host类型
     * @return 实例
     */
    public static RetrofitManager getInstance(@HostType.HostTypeChecker int hostType) {
        RetrofitManager instance = mInstanceManager.get(hostType);
        if (instance == null) {
            instance = new RetrofitManager(hostType);
            mInstanceManager.put(hostType, instance);
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * 配置okHttpClient实例
     * @return okHttpClient实例
     */
    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    // OkHttpClient配置是一样的,静态创建一次即可
                    // 指定缓存路径,缓存大小100Mb
//                    Cache cache = new Cache(new File(App.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                    mOkHttpClient = new OkHttpClient.Builder().retryOnConnectionFailure(true).connectTimeout(30, TimeUnit.SECONDS).build();
                }
            }
        }
        return mOkHttpClient;
    }

    /**
     * 网易新闻列表 例子：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     * <p>
     * 对API调用了observeOn(MainThread)之后，线程会跑在主线程上，包括onComplete也是，
     * unsubscribe也在主线程，然后如果这时候调用call.cancel会导致NetworkOnMainThreadException
     * 加一句unsubscribeOn(io)
     *
     * @param type      新闻类别：headline为头条,list为其他
     * @param id        新闻类别id
     * @param startPage 开始的页码
     * @return 被观察对象
     */
    public Observable<Map<String,List<News>>> getNewsListObservable(String type,String id,int startPage){
        return mNewService.getNewsList(type,id,startPage).compose(new BaseSchedulerTransformer<Map<String, List<News>>>());
    }

    /**
     * 网易新闻详情：例子：http://c.m.163.com/nc/article/BG6CGA9M00264N2N/full.html
     *
     * @param postid 新闻详情的id
     * @return 被观察对象
     */
    public Observable<Map<String,NewsDetail>> getNewsDetailObservable(String postid){
        return mNewService.getNewsDetail(postid).compose(new BaseSchedulerTransformer<Map<String,NewsDetail>>());
    }
}
