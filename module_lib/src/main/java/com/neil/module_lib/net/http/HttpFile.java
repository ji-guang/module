package com.neil.module_lib.net.http;

import android.support.annotation.NonNull;

import com.google.gson.JsonObject;
import com.neil.module_lib.util.name.UUIDUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by chen on 2018/4/3.
 * 网络文件：上传与下载
 */
public class HttpFile {

    /**
     * 文件下载get
     *
     * @param url      url
     * @param parent   父目录
     * @param child    存储文件名
     * @param callback 回调
     */
    public static void download(String url, String parent, String child, DownloadCallback callback) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(getFileCallback(parent, child, callback));
    }

    /**
     * 文件下载post
     *
     * @param url      url
     * @param postMap  请求键值对
     * @param parent   父目录
     * @param child    存储文件名
     * @param callback 回调
     */
    public static void download(String url, Map<String, String> postMap, String parent, String child, DownloadCallback callback) {
        OkHttpUtils.post()
                .url(url)
                .params(postMap)
                .build()
                .execute(getFileCallback(parent, child, callback));
    }

    /**
     * 文件下载post
     *
     * @param url      url
     * @param postJson post请求json
     * @param parent   父目录
     * @param child    存储文件名
     * @param callback 回调
     */
    public static void download(String url, String postJson, String parent, String child, DownloadCallback callback) {
        OkHttpUtils.postString()
                .url(url)
                .content(postJson)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(getFileCallback(parent, child, callback));
    }

    @NonNull
    private static FileCallBack getFileCallback(final String parent, final String child, final DownloadCallback callback) {
        return new FileCallBack(parent, child) {
            @Override
            public void onResponse(File response, int id) {
                if (callback != null) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (callback != null) {
                    callback.onError("" + e.getMessage());
                }
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                if (callback != null) {
                    callback.inProgress(progress, total);
                }
            }
        };
    }

    /**
     * 文件下载回调
     */
    public interface DownloadCallback {
        void onResponse(File file);

        void onError(String msg);

        void inProgress(float progress, long total);
    }

    /**
     *
     */
    public static void upload(String url, String filename, File file, UploadCallback callback) {
        upload(url, null, filename, file, callback);
    }

    /**
     * 上传文件
     *
     * @param url      文件地址
     * @param params   可选参数
     * @param filename 文件名
     * @param file     文件
     * @param callback 回调
     */
    public static void upload(String url, Map<String, String> params, String filename, File file, final UploadCallback callback) {
        OkHttpUtils.post()
                .url(url)
                .params(params)     //参数及文件
                .addFile(filename, UUIDUtil.uuid(), file)
                .build()
                .execute(getCallback(callback));
    }

    @NonNull
    private static StringCallback getCallback(final UploadCallback callback) {
        return new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                try{
                    if (callback != null) {
                        callback.onResponse(new JSONObject(response));
                    }
                }catch (Exception e){
                    if (callback != null) {
                        callback.onError("" + e.getMessage());
                    }
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (callback != null) {
                    callback.onError("" + e.getMessage());
                }
            }
        };
    }

    public interface UploadCallback {
        void onResponse(JSONObject response);
        void onError(String msg);
    }
}
