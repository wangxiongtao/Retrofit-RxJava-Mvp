package com.dawn.httplib.exception;


import com.dawn.httplib.response.BaseResult;
import com.dawn.httplib.response.OkResponse;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class HttpException extends Exception {
    public static final String SERVER_ERROR_MSG = "服务器连接失败";
    public static final String JSON_ERROR_MSG = "数据解析错误";
    public static final String DATA_NULL_ERROR_MSG = "返回数据为空";
    public static final String DOWNLOAD_ERROR_MSG = "下载异常";
    public static final int RESULT_NO_ERROR_CODE = 0;//返回BaseResult.code=0000(结果无错误)
    public static final int SERVER_CONNECT_ERROR_CODE = -1;//服务器连接失败
    public static final int SERVER_RESPONSE_NOT200_ERROR_CODE = -2;//服务器响应非200错误
    public static final int JSON_ERROR_CODE = -3;//json数据解析失败
    public static final int SELF_ERROR_CODE = -4;//返回BaseResult.code!=0000异常
    public static final int DATA_NULL_ERROR_CODE = -5;//返回BaseResult.code!=0000异常

    public static final int DOWNLOAD_ERROR_CODE = -6;//返回BaseResult.code=0000(结果无错误)
    private int code;
    private int httpCode;//服务器状态码
    private String url;//请求url
    private String json;//返回的服务器结果
    private OkResponse okResponse;
    private BaseResult result;


    public HttpException() {

    }

    public HttpException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public HttpException setHttpCode(int httpCode) {
        this.httpCode = httpCode;
        return this;
    }

    public HttpException setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpException setJson(String json) {
        this.json = json;
        return this;
    }

    public String getUrl() {
        return okResponse == null ? url : okResponse.getResponse().raw().request().url().toString();
    }

    public int getHttpCode() {
        return okResponse == null ? httpCode : okResponse.getResponse().code();
    }

    public String getJson() {
        return okResponse == null ? json : okResponse.getResultJson();
    }

    public HttpException setOkResponse(OkResponse okResponse) {
        this.okResponse = okResponse;
        return this;
    }

    public OkResponse getOkResponse() {
        return okResponse;
    }

    public HttpException setResult(BaseResult result) {
        this.result = result;
        return this;
    }

    public BaseResult getResult() {
        return result;
    }

    @Override
    public String getLocalizedMessage() {
        return getErrorInfo();
    }

    public String getErrorInfo() {
        switch (code) {
            case SERVER_CONNECT_ERROR_CODE:
                return getString("SERVER_CONNECT_ERROR_CODE");
            case JSON_ERROR_CODE:
                return getString("JSON_ERROR_CODE");
            case SERVER_RESPONSE_NOT200_ERROR_CODE:
                return getString("SERVER_RESPONSE_NOT200_ERROR_CODE");
            case SELF_ERROR_CODE:
                return getString("SELF_ERROR_CODE");
            case DATA_NULL_ERROR_CODE:
                return getString("DATA_NULL_ERROR_CODE");
            case DOWNLOAD_ERROR_CODE:
                return getString("DOWNLOAD_ERROR_CODE");
        }
        return "没有判断此ERROR_CODE";
    }

    private String getString(String str) {
        return "请求的url==>" + this.getUrl()
                + "\n异常Code==>" + str
                + "\n异常信息==>" + getMessage()
                + "\n响应Code==>" + this.getHttpCode()
                + "\n响应结果==>" + this.getJson()
                + "\n    ";
    }
}
