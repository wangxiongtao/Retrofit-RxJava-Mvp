package com.dawn.rrm.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dawn.rrm.R;
import com.dawn.rrm.base.BaseActivity;
import com.dawn.rrm.base.BasePresenter;
import com.dawn.rrm.view.EmptyView;

public class DataActivity extends BaseActivity {
    private TextView textView;
    private EmptyView emptyView;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i("aaa", "=========onPostCreate==============>"+textView.getWidth());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        emptyView=new EmptyView();
        emptyView.init(this);
        textView=findViewById(R.id.result);

    }

    @Override
    protected BasePresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

        getPresenter().sendPostRequest(new MainRequest(1));

    }

    @Override
    public void handlerView(int tag, Object data) {
        super.handlerView(tag, data);
        textView.setText(data.toString());
        emptyView.showContentLayout(textView);

    }

    @Override
    public void handlerError(int tag, String errorMsg) {
        super.handlerError(tag, errorMsg);
        emptyView.showNodataLayout(textView);
        emptyView.setOnButtonClickListener("重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });


    }

    public void onClick1(View view){

    }
}
