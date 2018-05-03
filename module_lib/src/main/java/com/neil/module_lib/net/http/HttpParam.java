package com.neil.module_lib.net.http;

import android.support.annotation.NonNull;

import com.neil.module_lib.util.util.GsonUtil;
import com.neil.module_lib.util.util.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;

/**
 * Created by chen on 2018/4/17.
 */

public class HttpParam {

    /**
     * http：params请求
     */
    public static <T> void request(String url, Map<String, String> postMap, HttpJson.HttpJsonCallback<T> callback) {
        String urlParams = url + "\n" + GsonUtil.toJson(postMap);
        try {
            OkHttpUtils.post()
                    .url(url)
                    .params(postMap)
                    .build()
                    .execute(getCallback(callback, urlParams));
        } catch (Exception e) {
            LogUtil.e(urlParams);
            LogUtil.e(e.getLocalizedMessage());
            callback.onback(false, GsonUtil.fromJson(null, callback.getTClass()), "err:" + e.getMessage());
        }
    }

    /**
     * 回调封装处理
     */
    @NonNull
    private static <T> StringCallback getCallback(final HttpJson.HttpJsonCallback<T> callback, final String urlParams) {
        return new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.e(urlParams);
                LogUtil.e(e.getLocalizedMessage());

                callback.onback(false, GsonUtil.fromJson(null, callback.getTClass()), "err:" + e.getMessage());
            }

            @Override
            public void onResponse(String str, int id) {
                LogUtil.e(urlParams);
                LogUtil.e(str);

                if (String.class.isAssignableFrom(callback.getTClass())) {
                    callback.onback(true, (T) str, "success");
                } else {
                    callback.onback(true, GsonUtil.fromJson(str, callback.getTClass()), "success");
                }
            }
        };
    }

    /**
     * 请求回调接口
     */
    public interface HttpJsonCallback<T> {
        Class<T> getTClass();       //解析类型传递

        void onback(boolean isSuccess, T t, String msg); //回调bean
    }
}
