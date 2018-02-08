package com.dawn.httplib.log;

import android.util.Log;

import com.dawn.httplib.BuildConfig;
import com.dawn.httplib.response.OkResponse;


/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class OkLogPrinter {
    private static String TAG="okhttp";
    public static void logSucess(boolean isDownload,OkResponse response){
        if(BuildConfig.DEBUG){
            if(isDownload){
                Log.i(TAG,"=下载信息===文件总大小=>"+response.getTotalSize()+"==当前下载大小==>"+response.getCurrentSize()+"==下载进度==>"+response.getPercent()+"%");
                return;
            }
            Log.e(TAG,"\n--------------------------------------请求成功---------------------------------------------      ");
            Log.e(TAG,"\n请求成功"+(response==null?"\n响应结果==>OkResponse response==null":response.toString()));
        }
    }
    public static void logFail(Throwable e){
        if(BuildConfig.DEBUG){
            Log.e(TAG,"\n--------------------------------------请求异常---------------------------------------------      ");
            Log.e(TAG, "\n请求出现异常" +"\n异常信息==>"+(e==null?"Throwable e==null":e.getLocalizedMessage()));
        }
    }


}
