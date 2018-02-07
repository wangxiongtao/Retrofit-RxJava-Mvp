package com.dawn.httplib;

/**
 * Created by Administrator on 2017/10/21.
 */

public interface HttpCallBack {
    void onHttpStart(int tag);

    void onHttpSuccess(int tag, Object response);

    void onHttpFail(int tag, String errorMsg);

    void onProgress(int tag, long total, long current, int percent);

    void onHttpEnd();
}
