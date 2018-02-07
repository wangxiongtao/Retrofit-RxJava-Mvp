package com.dawn.httplib.retrofit.function;



import com.dawn.httplib.exception.HttpException;
import com.dawn.httplib.handler.ResponseHandler;
import com.dawn.httplib.log.OkLogPrinter;
import com.dawn.httplib.request.OkRequest;
import com.dawn.httplib.response.BaseResult;
import com.dawn.httplib.response.OkResponse;
import com.google.gson.Gson;

import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.dawn.httplib.exception.HttpException.RESULT_NO_ERROR_CODE;


/**
 * Created by Administrator on 2018/2/6 0006.
 */

public class ResponseFun implements Function<Response<ResponseBody>,OkResponse> {
    private OkRequest request;

    public ResponseFun(OkRequest request) {
        this.request = request;
    }

    @Override
    public OkResponse apply(Response<ResponseBody> o) throws Exception {


        OkResponse response=new OkResponse(request,o,o.body().string());
        HttpException exception=ResponseHandler.checkResult(response,request.getGenericSuperclass());
        if(exception.getCode() != RESULT_NO_ERROR_CODE){
            throw exception;
        }

        BaseResult result = exception.getResult();
        result.tag=request.getTag();
        response.setBaseResult(result);

        return response;
    }
}
