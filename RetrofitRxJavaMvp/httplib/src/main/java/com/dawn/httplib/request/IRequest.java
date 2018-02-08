package com.dawn.httplib.request;


import com.dawn.httplib.HttpCallBack;
import com.dawn.httplib.response.OkResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public interface IRequest {


    int getTag();

    String getUrl();

    Observable<OkResponse> getObservable(Retrofit retrofit, Function<Response<ResponseBody>,OkResponse> fun);

    HashMap<String, String> getParamMap();

}
