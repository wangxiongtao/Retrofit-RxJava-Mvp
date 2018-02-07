package com.dawn.rrm.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.dawn.rrm.mvp.IView;


/**
 * 基类Activity 定义了Activity中的常用方法
 * 封装了公共的Toolbar 的初始化，
 * 布局文件初始化
 */

public abstract class BaseActivity extends AppCompatActivity implements IView {
    protected BasePresenter presenter;
    private ProgressDialog dialog;

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        Log.i("aaa", "=========onPostCreate==============>");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
//            initToolbar();
            initView(savedInstanceState);
            presenter = initPresenter();
            setListener();
            initData();
        }
    }

    protected abstract int getLayoutId();//加载的布局文件id


    protected abstract void initView(Bundle savedInstanceState);//初始化view

    protected abstract BasePresenter initPresenter();

    protected abstract void setListener();//添加的各种监听

    protected abstract void initData();//获取数据等逻辑操作


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {
        if(dialog==null){
            dialog = new ProgressDialog(this);
        }
        dialog.setTitle("数据加载中...");
        dialog.show();
    }

    @Override
    public void closeLoading() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void handlerView(int tag, Object data) {

    }

    @Override
    public void handlerdownload(int tag, long total, long current,int percent) {

    }

    @Override
    public void handlerErrorView(int tag, String errorMsg) {
        Toast.makeText(this,"tag=="+tag+"==msg==="+errorMsg,0).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
        }
    }
}
