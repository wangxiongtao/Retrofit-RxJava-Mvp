package com.dawn.rrm.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.dawn.rrm.R;

/**
 * Created by Administrator on 2018/2/9 0009.
 */

public class CommonDialog extends Dialog {
    public CommonDialog(@NonNull Context context) {
        super(context, R.style.translucent_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_layout);

    }
}
