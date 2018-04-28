package com.neil.module_lib.net.http;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.neil.module_lib.util.util.GsonUtil;
import com.neil.module_lib.util.util.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 *
 */
public class HttpJson {

    /**
     * json请求
     */
    public static <T> void request(String url, String postJson, HttpJsonCallback<T> callback) {
        try {
            LogUtil.e(url + "\n" + postJson);
            OkHttpUtils.postString()
                    .url(url)
                    .content(postJson)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(getCallback(callback));
        } catch (Exception e) {
            LogUtil.e(e.getLocalizedMessage());
            callback.onback(false, GsonUtil.fromJson(null, callback.getTClass()), "err:" + e.getMessage());
        }
    }

    /**
     * 回调封装处理
     */
    @NonNull
    private static <T> StringCallback getCallback(final HttpJsonCallback<T> callback) {
        return new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.e(e.getLocalizedMessage());

                callback.onback(false, GsonUtil.fromJson(null, callback.getTClass()), "err:" + e.getMessage());
            }

            @Override
            public void onResponse(String str, int id) {
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
