package com.dawn.httplib.request;


import java.util.HashMap;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public interface IRequest {


    int getTag();

    String getUrl();

    HashMap<String, String> getParamMap();

}
