package com.dawn.httplib;

import com.dawn.httplib.handler.ResponseHandler;
import com.dawn.httplib.log.OkLogPrinter;
import com.dawn.httplib.request.IRequest;
import com.dawn.httplib.response.OkResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/2/7 0007.
 */

public  class BaseObserver implements Observer<OkResponse> {
    private HttpCallBack callBack;
    private IRequest request;
    private boolean isDownload;

    public BaseObserver(IRequest request,HttpCallBack callBack) {
        this.request = request;
        this.callBack = callBack;

    }

    public Observer<OkResponse> setDownload(boolean download) {
        isDownload = download;
        return this;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(callBack!=null){
            callBack.onHttpStart(request.getTag(),d);
        }

    }

    @Override
    public void onNext(OkResponse value) {
        OkLogPrinter.logSucess(isDownload,value);
        if(callBack!=null){
            if(isDownload){
                callBack.onProgress(request.getTag(),value.getTotalSize(),value.getCurrentSize(),value.getPercent());
                return;
            }
            callBack.onHttpSuccess(request.getTag(),value.getBaseResult().data);
        }


    }

    @Override
    public void onError(Throwable e) {
        e= ResponseHandler.checkException(e,request);
        OkLogPrinter.logFail(e);
        if(callBack!=null){
            callBack.onHttpFail(request.getTag(),e.getMessage() );
        }

    }

    @Override
    public void onComplete() {

    }
}
