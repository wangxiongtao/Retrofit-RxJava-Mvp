package com.dawn.httplib.request;




import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

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
        return hashMap;
    }






}
