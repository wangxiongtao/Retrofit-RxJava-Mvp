package com.dawn.rrm.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.dawn.rrm.R;

/**
 * Created by Administrator on 2018/2/11 0011.
 */

public class EmptyView {
    ViewStub mStub;
    private TextView mEmptyTxt;
    private Button mTipsBtn;
    private boolean isInflate;
    Activity activity;

    public void init(Activity activity) {
        this.activity = activity;
        mStub = (ViewStub) activity.findViewById(R.id.viewstub_layout);

    }


    public void showContentLayout(View contentView) {
        mStub.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }

    public void showNodataLayout(View contentView) {
        mEmptyTxt= (TextView) inflate(mEmptyTxt,R.id.include_empty_text);
        mStub.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
        mEmptyTxt.setText("no data");
    }

    public void setOnButtonClickListener(String text, View.OnClickListener listener) {
        mTipsBtn= (Button) inflate(mTipsBtn,R.id.include_tips_btn);
        mTipsBtn.setText(text);
        mTipsBtn.setOnClickListener(listener);

    }

    private View inflate(View view, int viewId) {
        if (!isInflate) {
            mStub.inflate();
        }
        isInflate = true;
        if (view == null) {
            view = activity.findViewById(viewId);
        }
        return view;
    }
}
