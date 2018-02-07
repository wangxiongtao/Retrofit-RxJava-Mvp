package com.dawn.rrm.mvp;

import android.content.Context;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public interface IView {
     Context getContext();

     void showLoading();

     void closeLoading();


     void handlerView(int tag, Object data);

     void handlerdownload(int tag, long total, long current, int percent);

     void handlerErrorView(int tag, String errorMsg);





}
