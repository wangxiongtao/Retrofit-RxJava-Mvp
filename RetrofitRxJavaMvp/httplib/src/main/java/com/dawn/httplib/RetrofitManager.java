package com.dawn.httplib;


import com.dawn.httplib.handler.DownloadHandler;
import com.dawn.httplib.interceptor.HeadInterceptor;
import com.dawn.httplib.request.IRequest;
import com.dawn.httplib.request.OkRequest;
import com.dawn.httplib.retrofit.APIInterface;
import com.dawn.httplib.retrofit.function.PostResponseFun;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Administrator on 2018/2/6 0006.
 */

public class RetrofitManager {
    private Retrofit retrofit;
    private HttpCallBack callBack;


    private static class SingletonHolder {
        private static RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return SingletonHolder.instance;
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(30, TimeUnit.SECONDS)//设置连接超时时间
                .addInterceptor(new HeadInterceptor())
                .build();
    }

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    public RetrofitManager sendPostRequest(IRequest request) {
        request.getObservable(retrofit, new PostResponseFun((OkRequest) request))
                .subscribe(new BaseObserver(request, callBack));
        return this;

    }

    public RetrofitManager sendDownloadRequest(IRequest request) {
        Call<ResponseBody> call = retrofit.create(APIInterface.class).doDownload(request.getUrl());
        DownloadHandler.downloadFile(call, (OkRequest) request).subscribe(new BaseObserver(request, callBack).setDownload(true));
        return this;

    }


    public RetrofitManager setCallBack(HttpCallBack callBack) {
        this.callBack = callBack;
        return this;
    }
}
