package com.dawn.httplib.handler;

import com.dawn.httplib.API;
import com.dawn.httplib.exception.HttpException;
import com.dawn.httplib.request.IRequest;
import com.dawn.httplib.request.OkRequest;
import com.dawn.httplib.response.BaseResult;
import com.dawn.httplib.response.OkResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.exceptions.CompositeException;
import okhttp3.Call;
import okhttp3.Response;

import static com.dawn.httplib.exception.HttpException.DATA_NULL_ERROR_CODE;
import static com.dawn.httplib.exception.HttpException.DATA_NULL_ERROR_MSG;
import static com.dawn.httplib.exception.HttpException.JSON_ERROR_CODE;
import static com.dawn.httplib.exception.HttpException.JSON_ERROR_MSG;
import static com.dawn.httplib.exception.HttpException.RESULT_NO_ERROR_CODE;
import static com.dawn.httplib.exception.HttpException.SELF_ERROR_CODE;
import static com.dawn.httplib.exception.HttpException.SERVER_CONNECT_ERROR_CODE;
import static com.dawn.httplib.exception.HttpException.SERVER_ERROR_MSG;
import static com.dawn.httplib.exception.HttpException.SERVER_RESPONSE_NOT200_ERROR_CODE;
import static com.dawn.httplib.response.BaseResult.OKCODE;


/**
 * post get请求对结果的处理
 */

public class ResponseHandler {
    public static Throwable checkException(Throwable e,IRequest request) {
        if(e instanceof IOException){
            return new HttpException(SERVER_CONNECT_ERROR_CODE,SERVER_ERROR_MSG).setUrl(API.BASE_URL+request.getUrl());
        }else if(e instanceof HttpException){
            return e;
        }
        return e;
    }

    public static HttpException checkResult(OkResponse okResponse, Type type) {

        try {
            Response raw = okResponse.getResponse().raw();
            if (!raw.isSuccessful()) {
                return new HttpException(SERVER_RESPONSE_NOT200_ERROR_CODE, SERVER_ERROR_MSG).setOkResponse(okResponse);
            }
            String json = okResponse.getResultJson();
            JSONObject jsonObject = new JSONObject(json);
            String code = jsonObject.getString("code");
            String msg = jsonObject.getString("msg");
            if (!OKCODE.equals(code)) {
                return new HttpException(SELF_ERROR_CODE, msg).setOkResponse(okResponse);
            }
            Gson gson = new Gson();
            BaseResult result = gson.fromJson(okResponse.getResultJson(), type);
            if (result.data == null) {
                return new HttpException(DATA_NULL_ERROR_CODE, DATA_NULL_ERROR_MSG).setOkResponse(okResponse);
            }
            return new HttpException(RESULT_NO_ERROR_CODE, "").setResult(result);

        } catch (Exception e) {
            e.printStackTrace();
            return new HttpException(JSON_ERROR_CODE, JSON_ERROR_MSG).setOkResponse(okResponse);
        }


    }

}
