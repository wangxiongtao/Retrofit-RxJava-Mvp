package com.dawn.rrm.test;



import com.dawn.httplib.API;
import com.dawn.httplib.request.OkRequest;
import com.dawn.httplib.response.BaseResult;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class MainRequest3 extends MainRequest {

    public MainRequest3(int tag) {
        super(tag);
    }

    @Override
    public String getUrl() {
        return "listProductByCategory";
    }

}
