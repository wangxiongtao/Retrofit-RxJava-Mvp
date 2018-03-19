package com.dawn.rrm.test;

import android.os.Bundle;

import com.dawn.rrm.R;
import com.dawn.rrm.base.BaseActivity;
import com.dawn.rrm.base.BasePresenter;

public class Main2Activity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.include_toolbar_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
