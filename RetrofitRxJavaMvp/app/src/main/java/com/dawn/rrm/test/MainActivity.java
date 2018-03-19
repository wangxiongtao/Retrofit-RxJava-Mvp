package com.dawn.rrm.test;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dawn.rrm.R;
import com.dawn.rrm.base.BaseActivity;
import com.dawn.rrm.base.BasePresenter;
import com.dawn.rrm.test.response.GoodsListBean;
import com.dawn.rrm.view.dialog.CommonDialog;
import com.dawn.rrm.view.shadow.ShadowDrawableWrapper;

import java.io.File;


public class MainActivity extends BaseActivity {
    public static final int REQUEST_TAG1 = 1;
    public static final int REQUEST_TAG2 = 2;

    MainPresenter presenter;
    private TextView t1, t2;
    ProgressBar progressBar;
    NotificationManager mNotificationManager;
    private NotificationCompat.Builder builder;
    private ImageView iv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        iv = findViewById(R.id.iv);
        progressBar = findViewById(R.id.progress);

        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Log.i("aaa","==progressBar==>"+progressBar.getHeight());
                return false;
            }
        });
        int a=0;
        ShadowDrawableWrapper  wrapper=new ShadowDrawableWrapper(this,getResources().getDrawable(R.drawable.ic_launcher),1,20,20);
        progressBar.setBackground(wrapper);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },5000);


//        TTest<Integer> t=new TTest<>();
//        Log.i("aaa","====>"+t.getData().toString());
//        t1.setText(t.getData().toString());
//       P<Integer>p=new P<>();
//
//        Log.i("aaa","====>"+p.getData());
//        t1.setText(p.getData().toString());

    }

    @Override
    protected BasePresenter initPresenter() {
        int a=0;
        presenter = new MainPresenter(this);
        return presenter;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("正在下载");

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }


    @Override
    public void handlerView(int tag, Object data) {
        switch (tag) {
            case REQUEST_TAG1:
//                int  bean=Integer.parseInt(data.toString());
                t1.setText(data.toString());
                break;
            case REQUEST_TAG2:
//                Gson gson=new Gson();
//                BaseResult<GoodsListBean>result= gson.fromJson(data.toString(),BaseResult.class);
                GoodsListBean goodsListBean = (GoodsListBean) data;
                t2.setText(((GoodsListBean) data).productName);
                break;

        }


    }

    @Override
    public void handlerdownload(int tag, long total, long current, final int percent) {
        super.handlerdownload(tag, total, current, percent);
        progressBar.setProgress(percent);
        builder.setProgress(100, percent, false);
        mNotificationManager.notify(1, builder.build());




//


//        if (percent == 100) {
//            Toast.makeText(this, "下载成功", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void handlerErrorView(int tag, String errorMsg) {
        super.handlerErrorView(tag, errorMsg);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post:
                final MainRequest request = new MainRequest(REQUEST_TAG1);
                request.proTypeCode = "101";
                request.currentPageNum = "1";
                request.pageCount = "10";

                final MainRequest request3 = new MainRequest(45);
                request3.proTypeCode = "101";
                request3.currentPageNum = "1";
                request3.pageCount = "10";

                MainRequest2 request2 = new MainRequest2(REQUEST_TAG2);
                request2.productId = "15010021771";

                //同时请求三个request
//                presenter.getData(request);
//                presenter.getData(request2);
//                presenter.getData(request3);

                CommonDialog dialog = new CommonDialog(this);
                dialog.show();


                break;

            case R.id.download:


                DownLoadRequest request1 = new DownLoadRequest();
                request1.setDownLoadDir(Environment.getExternalStorageDirectory() + File.separator + "test");
                request1.setFileName("kaochao.apk");
                presenter.downLoadData(request1);
                break;
            case R.id.toactivity:


                startActivity(new Intent(v.getContext(),DataActivity.class));
                break;

        }

    }

}
