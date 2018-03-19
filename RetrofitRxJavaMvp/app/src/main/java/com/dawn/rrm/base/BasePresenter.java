package com.dawn.rrm.base;


import com.dawn.httplib.HttpCallBack;
import com.dawn.httplib.request.IRequest;
import com.dawn.rrm.mvp.IModel;
import com.dawn.rrm.mvp.IPresenter;
import com.dawn.rrm.mvp.IView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public  class BasePresenter implements IPresenter,HttpCallBack {
    private WeakReference<IView> viewRef;
    private Disposable d;

    public BasePresenter(IView view){
        viewRef=new WeakReference<>(view);
    }

    @Override
    public IView getView() {
        if(viewRef==null){
            return null;
        }
        return viewRef.get();
    }

    @Override
    public void sendPostRequest(IRequest request) {
        IModel.getInstance().post(request,this);
    }

    @Override
    public void onHttpStart(int tag,Disposable d) {
        this.d=d;
        if(getView()!=null){
            getView().showLoading();
        }
    }

    @Override
    public void onHttpSuccess( int tag,Object response) {
        if(getView()!=null){
            getView().closeLoading();
            getView().handlerView(tag,response);
        }
    }

    @Override
    public void onHttpFail(int tag,String errorMsg) {
        if(getView()!=null){
            getView().closeLoading();
            getView().handlerErrorView(tag,errorMsg);
        }

    }

    @Override
    public void onProgress(int tag,long total, long current,int percent) {
        if(getView()!=null){
            getView().closeLoading();
            getView().handlerdownload(tag,total,current,percent);
        }

    }

    @Override
    public void onHttpEnd() {

    }

    @Override
    public void onDestroy() {
       if(viewRef!=null){
           if(d!=null){
               d.dispose();
           }
           viewRef.clear();
           viewRef=null;
       }
    }
}
