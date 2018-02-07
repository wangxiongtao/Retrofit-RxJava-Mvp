package com.dawn.httplib.request;




import com.dawn.httplib.HttpCallBack;
import com.dawn.httplib.response.BaseResult;
import com.dawn.httplib.response.OkResponse;
import com.dawn.httplib.retrofit.APIInterface;
import com.dawn.httplib.retrofit.function.ResponseFun;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/10/21.
 */

public abstract class OkRequest<T> implements IRequest {
    private final Type mGenericSuperclass;
    private int tag=-1;
    private String downLoadDir;//下载目录
    private String fileName;//下载的文件名



    public OkRequest(int tag) {
        this();
        this.tag = tag;

    }
    public OkRequest() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            mGenericSuperclass = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } else {
            mGenericSuperclass = Object.class;
        }
    }

    public void setDownLoadDir(String downLoadDir) {
        this.downLoadDir = downLoadDir;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownLoadDir() {
        return downLoadDir;
    }

    public String getFileName() {
        return fileName;
    }



    public Type getGenericSuperclass() {
        return mGenericSuperclass;
    }

    @Override
    public int getTag() {
        return tag;
    }


    @Override
    public HashMap<String, String> getParamMap() {
        HashMap<String, String> hashMap = new HashMap<>();
//        try {
//            for (Field field : this.getClass().getDeclaredFields()) {
//                String type = Modifier.toString(field.getModifiers());
//                if ("public".equals(type)) {
//                    String name = field.getName();
//                    Object value = field.get(this);
//                    if (value != null && !TextUtils.isEmpty(value.toString())) {
//                        hashMap.put(name, value.toString());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        hashMap.put("token", "");
        return hashMap;
    }

    @Override
    public  Observable<OkResponse> getObservable(Retrofit retrofit){
        return retrofit.create(APIInterface.class)
                .doPost(getUrl(), getParamMap())
                .map(new ResponseFun(this)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }




}
