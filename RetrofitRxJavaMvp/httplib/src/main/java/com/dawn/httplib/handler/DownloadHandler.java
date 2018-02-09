package com.dawn.httplib.handler;



import com.dawn.httplib.exception.HttpException;
import com.dawn.httplib.request.OkRequest;
import com.dawn.httplib.response.OkResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.dawn.httplib.exception.HttpException.DOWNLOAD_ERROR_CODE;
import static com.dawn.httplib.exception.HttpException.DOWNLOAD_ERROR_MSG;


/**
 * 处理下载
 */

public class DownloadHandler {
    public static Observable<OkResponse> downloadFile(final Response<ResponseBody> response, final OkRequest request) {
       return  Observable.create(new ObservableOnSubscribe<OkResponse>() {
            @Override
            public void subscribe(ObservableEmitter<OkResponse> ee) throws Exception {
                InputStream is = null;
                FileOutputStream fos = null;
                OkResponse okResponse = null;
                try {

                    File parenFile = new File(request.getDownLoadDir());
                    if (!parenFile.exists()) {
                        parenFile.mkdirs();
                    }
                    final File file = new File(parenFile, request.getFileName());
                    if (file.exists()) {
                        file.delete();
                    }
                    okResponse = new OkResponse(request,response, "这是一个下载的响应结果");
                    byte[] buf = new byte[2048];
                    int len;
                    long total = response.body().contentLength();
                    long current = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        okResponse.setTotalSize(total);
                        okResponse.setCurrentSize(current);
                        int progress = (int) (current * 1.0f / total * 100);
                        okResponse.setPercent(progress);
                        ee.onNext(okResponse);
                    }
                    fos.flush();
                } catch (Exception e) {
                    HttpException exception = new HttpException(DOWNLOAD_ERROR_CODE, DOWNLOAD_ERROR_MSG);
                    exception.setOkResponse(okResponse);
                    ee.onError(exception);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());



    }
}
