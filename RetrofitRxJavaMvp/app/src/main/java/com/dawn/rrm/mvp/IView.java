package com.dawn.rrm.mvp;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public interface IView {

     void showLoading();

     void closeLoading();


     void handlerView(int tag, Object data);

     void handlerdownload(int tag, long total, long current, int percent);

     void handlerError(int tag, String errorMsg);





}
