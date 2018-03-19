package com.dawn.rrm.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.githang.statusbar.StatusBarCompat;

/**
 * Created by Administrator on 2018/2/26 0026.
 */

public class StatusbarUtil {

    /**
     * 设置状态栏颜色
     * setContentView方法之后调用
     * @param activity
     * @param colorResId 颜色可以是资源 也可以是Color.parseColor()的转换结果
     */
    public static void setStatusbarColor(Activity activity, int colorResId) {

        StatusBarCompat.setStatusBarColor(activity, colorResId);
    }

    /**
     * 布局的内容延伸到状态栏并且状态栏透明
     * @param activity
     */
    public static void setContentToStatusbar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//
        } else {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
    }


}
