package com.dawn.httplib.retrofit;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/2/1 0001.
 */

public interface APIInterface {


    @FormUrlEncoded
    @POST
    Observable<Response<ResponseBody>> doPost(@Url String Url, @FieldMap HashMap<String, String> map);
}
