package com.neil.module_lib.util.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chen on 2018/4/17.
 *
 * bean:json
 */
public class GsonUtil {

    /**
     * 解析Json
     * @return
     */
    public static<T> T fromJson(String json,Class<T> clz) {
        return getGson().fromJson(json, clz);
    }


    /**
     * 转换json去实体
     * @return
     */
    public static<T> String toJson(T t) {
        return getGson().toJson(t);
    }


    /**
     * 获取指定配置的gson
     */
    private static Gson getGson() {
        return new GsonBuilder().serializeNulls().create();
    }
}
