package com.dawn.httplib.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/2/7 0007.
 */

public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return addHeader(chain);
    }
    private Response addHeader(Interceptor.Chain chain) throws IOException {

        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .addHeader("storeId", "20001000001")
                .header("vendorId","20001" );
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}
