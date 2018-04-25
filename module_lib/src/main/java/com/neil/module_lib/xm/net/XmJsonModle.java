package com.neil.module_lib.xm.net;

import android.text.TextUtils;

import com.neil.module_lib.net.core.BaseMap;
import com.neil.module_lib.net.modle.JsonModle;
import com.neil.module_lib.util.local.BeanCacheUtil;

import java.util.Map;

/**
 * Created by chen on 2018/4/25.
 * <p>
 * xm参数定义以及封装
 * "{"token":"ssss","content":{}}
 */
public abstract class XmJsonModle<O> extends JsonModle<O> {
    private String token;

    protected XmJsonModle() {
        this(null);
    }

    protected XmJsonModle(String token) {
        this.token = token;
    }

    @Override
    protected String transfromParam(final BaseMap<Object> basemap) {
        return super.transfromParam(new BaseMap<Object>() {
            @Override
            protected void initMap(Map<String, Object> map) {
                if (!TextUtils.isEmpty(token))
                    map.put("token", token);
                if (basemap != null) {
                    map.put("content", basemap.getMap());
                }
            }
        });
    }
}
