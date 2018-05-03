package com.neil.module_lib.net.modle;

import com.neil.module_lib.net.core.BaseMap;
import com.neil.module_lib.net.core.Observer;
import com.neil.module_lib.net.core.Watched;
import com.neil.module_lib.net.http.HttpJson;
import com.neil.module_lib.net.http.HttpParam;
import com.neil.module_lib.util.util.GsonUtil;

import java.util.Map;

/**
 * Created by chen on 2018/5/3.
 */

public abstract class ParamModle<O> {

    /**
     * 接口url
     */
    protected abstract String url();

    /**
     * 业务参数转换(子类修改模板)
     */
    protected abstract Map<String, String> transfromParam(BaseMap<Object> basemap);

    /**
     * 返回bean类型定义
     */
    protected abstract Class<O> getOclass();

    /**
     * 核心API
     */
    public void request(BaseMap<Object> params, Observer<O> callback) {
        Map<String, String> map = transfromParam(params); //业务参数封装

        registCallback(callback);       //注册观察者回调

        execute(url(), map, getOclass());
    }

    //---------------------------pri-----------------------

    /**
     * API实现： params>bean
     */
    private void execute(String url, Map<String, String> postMap, final Class<O> clz) {
        HttpParam.request(url, postMap, new HttpJson.HttpJsonCallback<O>() {
            @Override
            public Class<O> getTClass() {
                return clz;
            }

            @Override
            public void onback(boolean isSuccess, O o, String msg) {
                watched.notifyObservers(isSuccess, o, msg);
            }
        });
    }

    /**
     * TODO 请求结果分发(设计支持多个观察者)
     */
    private Watched<O> watched = new Watched<O>();// 返回O：的分发

    private void registCallback(Observer<O> callback) {
        watched.resigtObserver(callback);
    }

    private void unregistCallback(Observer<O> callback) {
        watched.unresigtObserver(callback);
    }
}
