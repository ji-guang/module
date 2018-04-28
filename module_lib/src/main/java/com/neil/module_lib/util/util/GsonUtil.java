package com.neil.module_lib.util.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chen on 2018/4/17.
 * <p>
 * bean:json
 */
public class GsonUtil {

    /**
     * 解析Json
     *
     * @return 不会返回null，只会返回未赋值的bean
     */
    public static <T> T fromJson(String json, Class<T> clz) {
        if (TextUtils.isEmpty(json)) {    //确保以json，生成bean
            json = "{}";
        }
        return getGson().fromJson(json, clz);
    }


    /**
     * 转换json去实体
     *
     * @return
     */
    public static <T> String toJson(T t) {
        return getGson().toJson(t);
    }


    /**
     * 获取指定配置的gson
     */
    private static Gson getGson() {
        return new GsonBuilder().serializeNulls().create();
    }
}
