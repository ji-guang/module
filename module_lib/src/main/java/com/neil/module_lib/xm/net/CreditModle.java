package com.neil.module_lib.xm.net;

import com.neil.module_lib.net.core.BaseMap;
import com.neil.module_lib.net.modle.ParamModle;
import com.neil.module_lib.util.util.GsonUtil;

import java.util.Map;

/**
 * Created by chen on 2018/5/3.
 * <p>
 * params:
 * {"name":"","cardType":0,"osVers":"5.1.1","mcNo":"","cardNo":""
 * ,"data":{"creditStatusFlag":"1","all":"1"}}
 */

public abstract class CreditModle<O> extends ParamModle<O> {

    Map<String, Object> param;

    /**
     * 身份鉴权 与 公共参数
     */
    protected CreditModle(Map<String, Object> param) {
        this.param = param;
    }

    /**
     * 封装与格式转换
     */
    @Override
    protected Map<String, String> transfromParam(final BaseMap<Object> basemap) {
        if (basemap != null)
            param.put("data", basemap.getMap());
        return new BaseMap<String>() {
            @Override
            protected void initMap(Map<String, String> map) {
                map.put("params", GsonUtil.toJson(param));
            }
        }.getMap();
    }
}
