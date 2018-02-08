package com.dawn.httplib.response;


import com.dawn.httplib.request.OkRequest;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class OkResponse {
    private OkRequest request;
    private Response<ResponseBody> response;
    private String resultJson;
    private BaseResult baseResult;
    private long totalSize;//下载文件总大小
    private long currentSize;//当前文件下载大小
    private int percent;//下载的百分比(0%-100%)

    public OkResponse(OkRequest request, Response response, String resultJson) {
        this.response = response;
        this.resultJson = resultJson;
        this.request = request;
    }
    public OkResponse(OkRequest request, String resultJson) {
        this.resultJson = resultJson;
        this.request = request;
    }
    public OkResponse() {
    }

    public Response getResponse() {
        return response;
    }

    public String getResultJson() {
        return resultJson;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public void setCurrentSize(long currentSize) {
        this.currentSize = currentSize;
    }

    public long getCurrentSize() {
        return currentSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public void setBaseResult(BaseResult baseResult) {
        this.baseResult = baseResult;
    }

    public BaseResult getBaseResult() {
        return baseResult;
    }

    @Override
    public String toString() {
        return  "\n请求Url==>" + this.response.raw().request().url()
                + "\n请求参数==>" + this.request.getParamMap()
                + getHeadString()
                + "\n请求Tag==>" + this.request.getTag()
                + "\n响应Code==>" + this.response.code()
                + "\n响应结果==>" + this.resultJson
                + "\n   ";

    }


    private String getHeadString() {
        Headers headers = this.response.raw().request().headers();
        StringBuilder builder = new StringBuilder();
        for (int i = 0, count = headers.size(); i < count; i++) {
            builder.append("\n").append("请求头信息：").append(headers.name(i)).append("==>").append(headers.value(i));

        }
        return builder.toString();

    }
}
