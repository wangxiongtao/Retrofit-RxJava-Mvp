package com.dawn.httplib;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/10/21.
 */

public interface HttpCallBack {
    void onHttpStart(int tag, Disposable d);

    void onHttpSuccess(int tag, Object response);

    void onHttpFail(int tag, String errorMsg);

    void onProgress(int tag, long total, long current, int percent);

    void onHttpEnd();
}
