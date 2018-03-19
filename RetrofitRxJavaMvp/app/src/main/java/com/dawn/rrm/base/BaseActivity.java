package com.dawn.rrm.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.dawn.rrm.R;
import com.dawn.rrm.mvp.IView;
import com.dawn.rrm.test.MainActivity;
import com.dawn.rrm.util.StatusbarUtil;
import com.dawn.rrm.view.EmptyView;


/**
 * 基类Activity 定义了Activity中的常用方法
 * 封装了公共的Toolbar 的初始化，
 * 布局文件初始化
 */

public abstract class BaseActivity extends AppCompatActivity implements IView {
    private ViewGroup mRootLayout;
    protected TextView mToolbar_Title_Txt;
    protected TextView mToolbar_Right_Txt;
    protected View mToolbar_line_Txt;
    protected Toolbar mToolbar;
    private EmptyView mEmptyView;
    protected BasePresenter presenter;
    private ProgressDialog dialog;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.RED);//
//            window.setNavigationBarColor(Color.BLUE);
//        }else {
//            setTranslucentStatus(true);//
//        }





        if (getLayoutId() > 0) {



            setContentView(getLayoutId());
            initToolbar();
            initEmptyView();
            initView(savedInstanceState);
            presenter = initPresenter();
            setListener();
            initData();
        }
        StatusbarUtil.setContentToStatusbar(this);


    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar == null) {
            return;
        }
        mToolbar_Title_Txt = (TextView) findViewById(R.id.include_toolbar_title_txt);
        mToolbar_Right_Txt = (TextView) findViewById(R.id.include_toolbar_right_txt);
        mToolbar_line_Txt = findViewById(R.id.include_toolbar_line_view);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                onBackPressed();
            }
        });

    }

    protected Intent getBeforeLoginIntent(Class c){
        if(isLogin){
            return new Intent(this,c);
        }else {
            return new Intent(this, MainActivity.class);
        }

    }
    protected abstract int getLayoutId();//加载的布局文件id


    protected abstract void initView(Bundle savedInstanceState);//初始化view

    protected abstract BasePresenter initPresenter();

    protected abstract void setListener();//添加的各种监听

    protected abstract void initData();//获取数据等逻辑操作

    public BasePresenter getPresenter() {
        return presenter;
    }

   private void initEmptyView(){
        mEmptyView=new EmptyView();
        mEmptyView.init(this);
   }
   protected void showNodataLayout(View contentView){
       mEmptyView.showNodataLayout(contentView);
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
