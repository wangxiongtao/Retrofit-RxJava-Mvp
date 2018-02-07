package com.dawn.rrm.test;



import com.dawn.httplib.request.OkRequest;
import com.dawn.httplib.response.BaseResult;
import com.dawn.rrm.test.response.GoodsListBean;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class MainRequest2 extends OkRequest<BaseResult<GoodsListBean>> {
    public String productId;

    public MainRequest2(int tag) {
        super(tag);
    }

    @Override
    public String getUrl() {
        return "getProductDetail";
    }

    @Override
    public HashMap<String, String> getParamMap() {
        HashMap<String,String>map=super.getParamMap();
        map.put("productId",productId);
        return map;
    }
}
