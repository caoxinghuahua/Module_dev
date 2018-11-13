package com.hua.module.basemodule;


import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author caoxinghua on 2018/7/17
 * @email caoxinghua@gomeplus.com
 */
public class HttpManger {
    private OkHttpClient.Builder okhttpBuilder;
    private Retrofit.Builder retrofitBuilder;
    private final long DEFAULT_TIME_OUT=5000;
    private static Application sContext;
    private static HttpManger instance;
    private OkHttpClient client;
    private Retrofit retrofit;
    private String baseUrl="http://m-awall.stage.ds.gome.com.cn";
    public HttpManger(){
        okhttpBuilder=new OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS);
        client=okhttpBuilder.build();
        retrofitBuilder=new Retrofit.Builder()
        .client(client)
//        .addCallAdapterFactory( RxJavaCallAdapterFactory.create())
//        .addConverterFactory(Str)
        .baseUrl(baseUrl);
        retrofit=retrofitBuilder.build();
    }
    public static HttpManger getInstance(){
        testInitialize();
        if(instance==null){
            synchronized (HttpManger.class){
                if(instance==null){
                    instance=new HttpManger();
                }
            }
        }
        return instance;
    }
    private static void testInitialize() {
        if (sContext == null)
            throw new ExceptionInInitializerError("请先在全局Application中调用 EasyHttp.init() 初始化！");
    }

    public static void init(Application app) {
        sContext = app;
    }
    public HttpManger setReadTimeOut(long timeOut, TimeUnit unit){
        okhttpBuilder.readTimeout(timeOut,unit);
        return this;
    }
    public HttpManger setWriteTimeOut(long timeOut, TimeUnit unit){
        okhttpBuilder.writeTimeout(timeOut,unit);
        return this;
    }
    public HttpManger addInterceptor(Interceptor interceptor){
        okhttpBuilder.addInterceptor(interceptor);
        return this;
    }
    public HttpManger baseUrl(String baseUrl){
        retrofitBuilder.baseUrl(baseUrl);
        return this;
    }
    public HttpManger setClient(){
        retrofitBuilder.client(okhttpBuilder.build());
        return this;
    }
    public HttpManger build(){
        retrofitBuilder.build();
        return this;
    }
    public  <T> T create(Class<T> cls){
        return retrofitBuilder.build().create(cls);
    }
}
