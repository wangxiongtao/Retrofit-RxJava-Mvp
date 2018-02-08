package com.dawn.rrm.test;


import com.dawn.httplib.request.OkRequest;

/**
 * Created by Administrator on 2018/2/5 0005.
 */

public class DownLoadRequest extends OkRequest {
    @Override
    public String getUrl() {
        return "http://kaochong.oss-cn-beijing.aliyuncs.com/app-kaochong.apk";
    }
}
