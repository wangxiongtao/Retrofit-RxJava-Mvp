package com.dawn.httplib.retrofit.function;

import com.dawn.httplib.handler.DownloadHandler;
import com.dawn.httplib.request.IRequest;
import com.dawn.httplib.request.OkRequest;
import com.dawn.httplib.response.OkResponse;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/2/9 0009.
 */

public class DownloadFun implements Function<Response<ResponseBody>, ObservableSource<OkResponse>> {
    private IRequest iRequest;

    public DownloadFun(IRequest iRequest) {
        this.iRequest = iRequest;
    }

    @Override
    public ObservableSource<OkResponse> apply(Response<ResponseBody> response) throws Exception {
        return DownloadHandler.downloadFile(response, (OkRequest) iRequest);
    }
}
