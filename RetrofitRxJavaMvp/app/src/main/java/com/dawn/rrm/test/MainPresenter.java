package com.dawn.rrm.test;


import com.dawn.httplib.request.OkRequest;
import com.dawn.rrm.base.BasePresenter;
import com.dawn.rrm.mvp.IModel;
import com.dawn.rrm.mvp.IView;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class MainPresenter extends BasePresenter {
    public MainPresenter(IView view) {
        super(view);
    }

    public void getData(OkRequest request){
        IModel.getInstance().post(request,this);

    }
    public void downLoadData(OkRequest request){
        IModel.getInstance().downLoad(request,this);

    }
//    public void getData2(OkRequest request){
//        IModel.getInstance().post(request,this);
//
//    }
//    public void getData3(OkRequest request){
//        IModel.getInstance().post(request,this);
//
//    }。。。等等等一系列的网络请求或者其他的逻辑

}
