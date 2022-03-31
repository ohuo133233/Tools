package com.wang.javatools.net.request;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseNetManager {
    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;
    private HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor(message -> Log.e("OKHttp-----", message));

    private BaseNetManager() {

    }

    public static BaseNetManager getInstance() {
        return BaseNetInstance.instance;
    }

    private static class BaseNetInstance {
        private static final BaseNetManager instance = new BaseNetManager();
    }


    public void initNet() {
        initOkHttpClient();
        initRetrofit();
    }

    private void initOkHttpClient() {
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                // 连接超时
                .connectTimeout(20, TimeUnit.SECONDS)
                // 写入时间
                .writeTimeout(10, TimeUnit.MINUTES)
                // 读取超时
                .readTimeout(30, TimeUnit.MINUTES)
                // 增加log拦截器
                .addInterceptor(mHttpLoggingInterceptor)
                .build();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void onDestroy() {
        mOkHttpClient = null;
        mRetrofit = null;
        mHttpLoggingInterceptor = null;
    }
}
