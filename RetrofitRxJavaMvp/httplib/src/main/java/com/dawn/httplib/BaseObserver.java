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

    public BaseObserver(IRequest request,HttpCallBack callBack) {
        this.request = request;
        this.callBack = callBack;

    }

    @Override
    public void onSubscribe(Disposable d) {
        if(callBack!=null){
            callBack.onHttpStart(request.getTag());
        }

    }

    @Override
    public void onNext(OkResponse value) {

        if(callBack!=null){
            OkLogPrinter.logSucess(value);
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
