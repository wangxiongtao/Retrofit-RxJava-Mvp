package com.dawn.rrm.mvp;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public interface IPresenter {
    IView getView();

    void onDestroy();

}
