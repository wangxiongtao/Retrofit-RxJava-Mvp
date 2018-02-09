package com.dawn.rrm.test;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dawn.rrm.R;
import com.dawn.rrm.base.BaseActivity;
import com.dawn.rrm.base.BasePresenter;
import com.dawn.rrm.test.response.GoodsListBean;

import java.io.File;


public class MainActivity extends BaseActivity {
    public static final int REQUEST_TAG1=1;
    public static final int REQUEST_TAG2=2;

    MainPresenter presenter;
    private TextView t1,t2;
    ProgressBar progressBar;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        progressBar=findViewById(R.id.progress);



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
        presenter=new MainPresenter(this);
        return presenter;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }



    @Override
    public void handlerView(int tag, Object data) {
        switch (tag){
            case REQUEST_TAG1:
//                int  bean=Integer.parseInt(data.toString());
                t1.setText(data.toString());
                break;
            case REQUEST_TAG2:
//                Gson gson=new Gson();
//                BaseResult<GoodsListBean>result= gson.fromJson(data.toString(),BaseResult.class);
                GoodsListBean goodsListBean= (GoodsListBean) data;
                t2.setText(((GoodsListBean) data).productName);
                break;

        }


    }

    @Override
    public void handlerdownload(int tag, long total, long current, int percent) {
        super.handlerdownload(tag, total, current, percent);
        progressBar.setProgress(percent);
        if(percent==100){
            Toast.makeText(this,"下载成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handlerErrorView(int tag, String errorMsg) {
        super.handlerErrorView(tag, errorMsg);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.post:
                final MainRequest request=new MainRequest(REQUEST_TAG1);
                request.proTypeCode="101";
                request.currentPageNum="1";
                request.pageCount="10";

                final MainRequest request3=new MainRequest(45);
                request3.proTypeCode="101";
                request3.currentPageNum="1";
                request3.pageCount="10";

                MainRequest2 request2=new MainRequest2(REQUEST_TAG2);
                request2.productId="15010021771";

                //同时请求三个request
                presenter.getData(request);
                presenter.getData(request2);
                presenter.getData(request3);




                break;

            case R.id.download:


                DownLoadRequest request1=new DownLoadRequest();
                request1.setDownLoadDir(Environment.getExternalStorageDirectory()+ File.separator+"test");
                request1.setFileName("kaochao.apk");
                presenter.downLoadData(request1);
                break;

        }

    }

}
