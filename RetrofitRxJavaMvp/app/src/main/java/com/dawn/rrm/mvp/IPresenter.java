package com.dawn.rrm.mvp;

import com.dawn.httplib.request.IRequest;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public interface IPresenter {
    IView getView();

    void sendPostRequest(IRequest request);

    void onDestroy();

}
