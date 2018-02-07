package com.dawn.rrm.mvp;


import com.dawn.httplib.HttpCallBack;
import com.dawn.httplib.request.IRequest;
import com.dawn.httplib.retrofit.RetrofitManager;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class IModel {

    private static IModel iModel;

    public static IModel getInstance() {
        if (iModel == null) {
            iModel = new IModel();
        }
        return iModel;
    }

    public void post(IRequest request, HttpCallBack callBack) {
        RetrofitManager.getInstance().setCallBack(callBack).sendPostRequest(request);
    }

    public void downLoad(IRequest request, HttpCallBack callBack) {

    }


}
